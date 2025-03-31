package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class ApiSpec {

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON);


    public static ResponseSpecification successResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification unauthorizedErrorSpec = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .log(ALL)
            .build();

    public static ResponseSpecification notFoundErrorSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();

}