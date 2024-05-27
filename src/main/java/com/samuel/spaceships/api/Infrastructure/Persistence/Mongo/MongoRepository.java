package com.samuel.spaceships.api.Infrastructure.Persistence.Mongo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.samuel.spaceships.api.Domain.Criteria.Criteria;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.bson.Document;

public abstract class MongoRepository<T> {

  private static final String ID_FIELD = "_id";
  private final MongoClient client;
  private final MongoCriteriaConverter criteriaConverter;

  public MongoRepository(MongoClient client) {
    this.client = client;
    this.criteriaConverter = new MongoCriteriaConverter();
  }

  abstract protected String moduleName();

  protected MongoDatabase getDatabase() {
    return client.getDatabase(moduleName());
  }

  protected List<T> searchByCriteria(Criteria criteria,
      Function<Map<String, Object>, T> unserializer) {
    MongoCollection<Document> collection = getDatabase().getCollection(moduleName());
    MongoCriteria mongoCriteria = criteriaConverter.convert(criteria);

    int limit = criteria.limit().orElse(0);
    int offset = criteria.offset().orElse(0);

    return getDocuments(collection, mongoCriteria, limit, offset)
        .map(unserializer)
        .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private Stream<Map<String, Object>> getDocuments(MongoCollection<Document> collection,
      MongoCriteria mongoCriteria, int limit, int offset) {
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    return StreamSupport.stream(
            collection.find(mongoCriteria.getFilter()).sort(mongoCriteria.getSort()).limit(limit)
                .skip(offset * limit).spliterator(), false)
        .map(document -> gson.fromJson(document.toJson(), type))
        .filter(obj -> obj instanceof Map)
        .map(obj -> (Map<String, Object>) obj);
  }

  protected void persist(String id, HashMap<String, Serializable> plainBody) {
    MongoCollection<Document> collection = getDatabase().getCollection(moduleName());
    Document document = new Document(ID_FIELD, id);
    document.putAll(plainBody);
    collection.insertOne(document);
  }

  protected void deleteById(String id) {
    MongoCollection<Document> collection = getDatabase().getCollection(moduleName());
    collection.deleteOne(new Document(ID_FIELD, id));
  }

  protected boolean existsById(String id) {
    MongoCollection<Document> collection = getDatabase().getCollection(moduleName());
    return collection.countDocuments(new Document(ID_FIELD, id)) > 0;
  }
}