package com.epam.auto.homework_API03;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpecificationsService {
   private final Properties properties;

   public SpecificationsService(Properties properties){
      this.properties = properties;
   }

   public RequestSpecification requestSpec() {
      Map<String, String> parameters = new HashMap<>();
      parameters.put("key", properties.get("key").toString());
      parameters.put("token", properties.get("token").toString());

      PrintStream logStream = new PrintStream(System.out) {
         @Override
         public void println(String x) {
            if (x != null && !x.contains("key") && !x.contains("token")) {
               super.println(x);
            }
         }
      };

      LogConfig logConfig = new LogConfig().defaultStream(logStream).enablePrettyPrinting(true);

      return new RequestSpecBuilder()
              .setBaseUri(properties.get("uri").toString())
              .setContentType(ContentType.JSON)
              .addQueryParams(parameters)
              .setConfig(RestAssured.config().logConfig(logConfig))
              .log(LogDetail.ALL)
              .build();
   }
}
