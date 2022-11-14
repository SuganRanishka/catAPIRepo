package com.cat.api.service.service;


import com.cat.api.service.model.CatAPIRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cat.api.APIBase;

@Service
public class CatAPIFavouritesService extends APIBase {

    private static final String RESOURCE_PATH_FAVOURITES = "favourites";
    @Value("${catservice.url}")
    private String url;

    @Value("${catservice.basepath}")
    private String basePath;


    public Response createProject(String token, CatAPIRequest payload, int statusCode) {
        Map<String, String> headerWithAuth = getHeaderWithAuth(token);
        return super.postWithBody(url, basePath, RESOURCE_PATH_FAVOURITES, headerWithAuth, ContentType.JSON.toString(), payload, statusCode);
    }

    public Response getFavourites(String token, int statusCode) {
        Map<String, String> headerWithAuth = getHeaderWithAuth(token);
        return super.get(url, basePath, RESOURCE_PATH_FAVOURITES , headerWithAuth, Collections.emptyMap(), ContentType.JSON.toString(), statusCode);
    }

    public Response getFavouriteSpecificImage(String token,String favourite_id, int statusCode) {
        Map<String, String> headerWithAuth = getHeaderWithAuth(token);
        return super.get(url, basePath, RESOURCE_PATH_FAVOURITES + "/" + favourite_id , headerWithAuth, Collections.emptyMap(), ContentType.JSON.toString(), statusCode);
    }

    private Map<String, String> getHeaderWithAuth(String token) {

        return Map.of("x-api-key", token);
    }
//
    private Map<String, String> getQueryParams(String name, String value) {
        return (value == null || name == null) ?
                Collections.emptyMap() :
                Map.of(name, value);
    }

    public Response deleteSpecificFavourite(String token, String favouriteImage, int statusCode) {
        Map<String, String> headerWithAuth = getHeaderWithAuth(token);
        return super.delete(url, basePath, RESOURCE_PATH_FAVOURITES + "/" + favouriteImage  , headerWithAuth, null, null, statusCode);
    }

}
