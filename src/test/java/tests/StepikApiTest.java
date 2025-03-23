package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import models.CourseResponseModel;
import org.junit.jupiter.api.Test;
import specs.ApiSpec;

import static io.qameta.allure.Allure.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class StepikApiTest {
    @Test
    public void getCourse() {
        RestAssured.baseURI = "https://stepik.org/api";

        Response response = given()
                .when()
                .get("/courses/230295")
                .then()
                .spec(ApiSpec.successResponseSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());

        step("Check response", () -> {
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(response.body().asString()).isNotNull();

            step("Map response to CourseResponseModel", () -> {
                CourseResponseModel courseResponse = response.as(CourseResponseModel.class);
                assertThat(courseResponse.getCourses()).isNotEmpty();

                CourseResponseModel.Course course = courseResponse.getCourses().get(0);
                assertThat(course.getId()).isEqualTo(230295);
                assertThat(course.getSummary()).isNotEmpty();
                assertThat(course.getWorkload()).isNotNull();
                assertThat(course.getCover()).isNotEmpty();
                assertThat(course.getIntro()).isNotNull();
                assertThat(course.getInstructors()).isNotEmpty();
                assertThat(course.getRequirements()).isNotEmpty();
                assertThat(course.getDescription()).isNotEmpty();

            });
        });
    }
}

