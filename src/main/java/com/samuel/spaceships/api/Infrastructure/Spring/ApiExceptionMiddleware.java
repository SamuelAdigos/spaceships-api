package com.samuel.spaceships.api.Infrastructure.Spring;

import com.google.common.base.CaseFormat;
import com.samuel.spaceships.api.Domain.Bus.Command.CommandHandlerExecutionError;
import com.samuel.spaceships.api.Domain.Bus.Query.QueryHandlerExecutionError;
import com.samuel.spaceships.api.Domain.DomainError;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RequiredArgsConstructor
public final class ApiExceptionMiddleware implements Filter {

  private final RequestMappingHandlerMapping mapping;

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain
  ) throws ServletException {
    HttpServletRequest httpRequest = ((HttpServletRequest) request);
    HttpServletResponse httpResponse = ((HttpServletResponse) response);

    try {
      Object possibleController = (
          (HandlerMethod) Objects.requireNonNull(
              mapping.getHandler(httpRequest)).getHandler()
      ).getBean();

      try {
        chain.doFilter(request, response);
      } catch (Exception exception) {
        if (possibleController instanceof ApiController) {
          handleCustomError(response, httpResponse, (ApiController) possibleController, exception);
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  private void handleCustomError(
      ServletResponse response,
      HttpServletResponse httpResponse,
      ApiController possibleController,
      Exception exception
  ) throws IOException {
    HashMap<Class<? extends DomainError>, HttpStatus> errorMapping = possibleController
        .errorMapping();
    Throwable error = (
        exception.getCause() instanceof CommandHandlerExecutionError ||
            exception.getCause() instanceof QueryHandlerExecutionError
    )
        ? exception.getCause().getCause() : exception.getCause();

    int statusCode = statusFor(errorMapping, error);
    String errorCode = errorCodeFor(error);
    String errorMessage = error.getMessage();

    ErrorUtils.writeFormattedError(httpResponse, errorCode, errorMessage, statusCode);
  }

  private String errorCodeFor(Throwable error) {
    if (error instanceof DomainError) {
      return ((DomainError) error).errorCode();
    }

    return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, error.getClass().toString());
  }

  private int statusFor(HashMap<Class<? extends DomainError>, HttpStatus> errorMapping,
      Throwable error) {
    return errorMapping.getOrDefault(error.getClass(), HttpStatus.INTERNAL_SERVER_ERROR).value();
  }
}
