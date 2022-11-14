package com.cat.api;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.Map;


public class APIBase {

    protected Response postWithBody(String uri, String basePath, String resourcePath, Map<String, String> headers, String contentType, Object payload, int statusCode) {
        return postWithBody(uri, basePath, resourcePath, headers, Collections.emptyMap(), contentType, payload, statusCode);
    }
    protected Response postWithBody(String uri, String basePath, String resourcePath, Map<String, String> headers, Map<String, String> cookies, String contentType, Object payload, int statusCode) {
        var req = RestAssured.given()
            .config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .baseUri(uri)
            .basePath(basePath)
            .headers(headers)
            .cookies(cookies);
        if(contentType != null)
            req = req.contentType(contentType);
        return req
            .body(payload == null ? "" : payload)
            .post(resourcePath)
            .then()
            .statusCode(statusCode)
            .extract()
            .response();
    }

    protected Response delete(String uri, String basePath, String resourcePath, Map<String, String> headers, String contentType, Object payload, int statusCode) {
        return delete(uri, basePath, resourcePath, headers, Collections.emptyMap(), contentType, payload, statusCode);
    }

    protected Response delete(String uri, String basePath, String resourcePath, Map<String, String> headers, Map<String, String> cookies, String contentType, Object payload, int statusCode) {
        var request =  RestAssured.given()
            .config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .baseUri(uri)
            .basePath(basePath)
            .headers(headers)
            .cookies(cookies);
        if(contentType != null)
            request = request.contentType(contentType);
        return request
            .body(payload == null ? "" : payload)
            .delete(resourcePath)
            .then()
            .statusCode(statusCode)
            .extract()
            .response();
    }

    protected Response get(String uri, String basePath, String resourcePath, Map<String, String> headers, Map<String, String> queryParams, String acceptType, int statusCode) {
        return get(uri, basePath, resourcePath, headers, Collections.emptyMap(), queryParams, acceptType, statusCode);
    }

    protected Response get(String uri, String basePath, String resourcePath, Map<String, String> headers, Map<String, ?> cookies, Map<String, String> queryParams, String acceptType, int statusCode) {
        return RestAssured.given()
            .config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
            .baseUri(uri)
            .basePath(basePath)
            .headers(headers)
            .cookies(cookies)
            .accept(acceptType)
            .queryParams(queryParams == null ? Collections.emptyMap() : queryParams)
            .get(resourcePath)
            .then()
            .statusCode(statusCode)
            .extract()
            .response();
    }


}
