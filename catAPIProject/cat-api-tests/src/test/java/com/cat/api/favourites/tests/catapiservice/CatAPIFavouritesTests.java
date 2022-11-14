package com.cat.api.favourites.tests.catapiservice;


import com.cat.api.favourites.tests.base.APIBaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cat.api.favourites.data.servicedata.CatServiceTestData;
import com.cat.api.service.model.CatAPIRequest;
import com.cat.api.service.model.CatAPIResponse;
import com.cat.api.service.service.CatAPIFavouritesService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class CatAPIFavouritesTests extends APIBaseTest {

    @Autowired
    private CatAPIFavouritesService catService;

    @Autowired
    private CatServiceTestData testData;


    @BeforeMethod(alwaysRun = true)
    public void setupTestMethod() {

        softAssert =  new SoftAssert();

    }


    @Test
    public void saveAnImageAsAFavourite() {


        CatAPIRequest edPubValues = testData.getImageForFavouritesSave("Zi4jfH3c6","test_1");
        CatAPIResponse gtRes = catService.createProject("DEMO-API-KEY", edPubValues, HttpStatus.SC_OK).body().as(CatAPIResponse.class);
        softAssert.assertEquals(gtRes.getMessage(), String.format("SUCCESS"), "Success Message mismatch!");
        softAssert.assertNotNull(gtRes.getId(), "ID is empty!");
        softAssert.assertAll();
    }

    @Test
    public void testUserGetsFavouritesImages() throws JsonProcessingException {
        Response gtRes = catService.getFavourites("DEMO-API-KEY", HttpStatus.SC_OK);
        ObjectMapper mapper = new ObjectMapper();
        String resString = gtRes.asString();
        JsonPath js = new JsonPath(resString);
        CatAPIResponse[] jsonObj = new CatAPIResponse[0];
        try {
            jsonObj = mapper.readValue(gtRes.body().asString(), CatAPIResponse[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (CatAPIResponse favImage : jsonObj) {

            softAssert.assertNotNull(favImage.getId(),  "ID is empty!");
            System.out.println(favImage.getImage().getId() +" IDs ");
            softAssert.assertNotNull(favImage.getUser_id(),  "User Id is empty!");
            softAssert.assertNotNull(favImage.getImage_id(),  "Image Id is empty!");
            softAssert.assertNotNull(favImage.getSub_id(),  "Sub Id is empty!");
            softAssert.assertNotNull(favImage.getCreated_at(),  "Created date is empty!");
            softAssert.assertNotNull(favImage.getImage().getId(),  "Image Id is empty!");
            softAssert.assertNotNull(favImage.getImage().getUrl(), "Image URL is empty!");
            softAssert.assertAll();
        }

    }

    @Test
    public void testUserGetsSpecificFavouritesImage() {


        CatAPIResponse gtRes = catService.getFavouriteSpecificImage("DEMO-API-KEY","MTk4Mzk0Ng", HttpStatus.SC_OK).body().as(CatAPIResponse.class);

        System.out.println(gtRes.getId() + "  ID");
        softAssert.assertNotNull(gtRes.getUser_id(), "User ID is empty!");
        softAssert.assertNotNull(gtRes.getId(), "ID is empty");
        softAssert.assertNotNull(gtRes.getImage_id(), "Image ID is empty");
        softAssert.assertNotNull(gtRes.getSub_id(),"Sub ID is empty");
        softAssert.assertNotNull(gtRes.getCreated_at(), "Created Date is empty");
        softAssert.assertNotNull(gtRes.getImage().getId(), "Image ID is empty");
        softAssert.assertNotNull(gtRes.getImage().getUrl(),"Image url is empty");
        softAssert.assertAll();
    }



    @Test
    public void testUserCanDeleteSpecificFavourite(){
        CatAPIResponse response = catService.deleteSpecificFavourite("DEMO-API-KEY","7FXmHCZ5d", HttpStatus.SC_NO_CONTENT).body().as(CatAPIResponse.class);
        softAssert.assertEquals(response.getMessage(), String.format("SUCCESS"), "Success message mismatch!");
        softAssert.assertAll();
    }
}
