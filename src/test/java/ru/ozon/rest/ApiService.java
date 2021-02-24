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

    public void changeLocalization(Map<String, String> cookies) {
        given()
                .baseUri("https://www.ozon.ru/api/location/v2/user/location")
                .contentType(ContentType.JSON)
                .body("{\"AreaId\":27862,\"DefaultAreaId\":27862,\"UserAgent\":\"Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Mobile Safari/537.36\"}")
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    public void addItemToCart(Map<String, String> cookies) {
        // @formatter:off
        requestSpec
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .body("[{\"id\":147442362,\"quantity\":1}]")
                .when()
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
                .post("favoriteBatchAddItems")
                .then()
                .statusCode(200);
        // @formatter:on
    }


}
