package tests.api;


import config.ApiConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import utils.RandomUtils;

public class ApiTestBase {

    RandomUtils randomUtils = new RandomUtils();

    @BeforeAll
    static void beforeAll() {
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);
        RestAssured.baseURI = apiConfig.baseURI();
        RestAssured.basePath = apiConfig.basePath();
    }
}
