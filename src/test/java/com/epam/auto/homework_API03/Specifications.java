package com.epam.auto.homework_API03;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Specifications {

   @SneakyThrows
   private Properties getProperties() {
      Properties props = new Properties();
      String propFileName = "test.properties";
      props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
      return props;
   }

   public RequestSpecification requestSpec() {
      Map<String, String> parameters = new HashMap<>();
      parameters.put("key", getProperties().get("key").toString());
      parameters.put("token", getProperties().get("token").toString());
      return new RequestSpecBuilder()
              .setBaseUri(getProperties().get("uri").toString())
              .setContentType(ContentType.JSON)
              .addQueryParams(parameters)
              .build();
   }
}
