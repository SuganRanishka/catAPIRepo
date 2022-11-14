package com.cat.api.favourites.data.servicedata;


import com.cat.api.service.model.CatAPIRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CatServiceTestData {


    public CatAPIRequest getImageForFavouritesSave(String imageID, String subID) {
        CatAPIRequest proj = CatAPIRequest
                .builder()
                .image_id(imageID)
                .sub_id(subID)
                .build();
        return proj;
    }

}