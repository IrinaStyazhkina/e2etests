package ru.ozon.rest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiService {

    private RequestSpecification requestSpec = given()
            .baseUri("https://www.ozon.ru/api/composer-api.bx/_action/")
            .filter(new AllureRestAssured()
                    .setRequestTemplate("request.ftl")
                    .setResponseTemplate("response.ftl"));

    public void addItemToCart(Map<String, String> cookies) {
        // @formatter:off
        requestSpec
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body("[{\"id\":147442362,\"quantity\":1}]")
                .when()
                .log()
                .everything()
                .post("addToCart")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    public void addItemToFavourites(Map<String, String> cookies) {
        // @formatter:off
        requestSpec
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body("{\"skus\":[150630305]}")
                .when()
                .log()
                .everything()
                .post("favoriteBatchAddItems")
                .then()
                .statusCode(200);
        // @formatter:on
    }


}
