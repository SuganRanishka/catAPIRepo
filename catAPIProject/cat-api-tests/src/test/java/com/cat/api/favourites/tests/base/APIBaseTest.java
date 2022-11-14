package com.cat.api.favourites.tests.base;


import com.cat.api.favourites.CatAPITestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.asserts.SoftAssert;

@SpringBootTest(classes = CatAPITestApplication.class)
public abstract class APIBaseTest extends AbstractTestNGSpringContextTests {

    protected SoftAssert softAssert;


}
