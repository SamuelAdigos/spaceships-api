package com.samuel.spaceships.api.Infrastructure.Spring;

import com.google.common.base.CaseFormat;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorUtils {

  public static void writeFormattedError(HttpServletResponse response, String errorCode,
      String errorMessage, int statusCode) throws IOException {
    response.reset();
    response.setHeader("Content-Type", "application/json");
    response.setStatus(statusCode);
    PrintWriter writer = response.getWriter();
    String snakeCaseErrorCode = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, errorCode);
    writer.write(String.format(
        "{\"error_code\": \"%s\", \"message\": \"%s\"}",
        snakeCaseErrorCode,
        errorMessage
    ));
    writer.close();
  }
}