package tests.api;

import api.models.LoginRequestModel;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.restassured.response.Response;

import api.models.CourseResponseModel;
import api.models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import api.specs.ApiSpec;

import static io.qameta.allure.Allure.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Stepik")
@Owner("Olganow")
@Link(value = "Testing", url = "https://github.com/olganow")
@Tag("api")
public class StepikApiTest extends ApiTestBase {
    @Test
    @DisplayName("Get course")
    public void getCourse() {
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

    @Test
    @DisplayName("Get non existent course")
    public void getCourseNotFound() {
        int nonExistentCourseId = 99999999;

        Response response = given()
                .when()
                .get("/courses/" + nonExistentCourseId)
                .then()
                .spec(ApiSpec.notFoundErrorSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());

        step("Check error response", () -> {
            assertThat(response.statusCode()).isEqualTo(404);
            assertThat(response.body().asString()).contains("Not found");
        });
    }

    @Test
    @DisplayName("Get user details")
    public void getUserDetails() {
        Response response = given()
                .when()
                .get("/users/23008752")
                .then()
                .spec(ApiSpec.successResponseSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());

        step("Check response", () -> {
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(response.body().asString()).isNotNull();

            step("Map response to UserResponseModel", () -> {
                UserResponseModel userResponse = response.as(UserResponseModel.class);
                assertThat(userResponse.getUsers()).isNotEmpty();

                UserResponseModel.User user = userResponse.getUsers().get(0);
                assertThat(user.getId()).isEqualTo(23008752);
                assertThat(user.getFirst_name()).isEqualTo("Оксана");
                assertThat(user.getLast_name()).isEqualTo("Еськова");
                assertThat(user.getDetails()).isNotEmpty();
                assertThat(user.getAvatar()).isNotEmpty();
                assertThat(user.getFollowers_count()).isGreaterThan(0);
            });
        });
    }

    @Test
    @DisplayName("Get non existent user")
    public void getUserNotFound() {
        Response response = given()
                .when()
                .get("/users/99999999")
                .then()
                .spec(ApiSpec.notFoundErrorSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());

        step("Check error response", () -> {
            assertThat(response.statusCode()).isEqualTo(404);
            assertThat(response.body().asString()).contains("Not found");
        });
    }


    @Test
    @DisplayName("Login with invalid credentials")
    public void loginWithInvalidCredentials() {
        LoginRequestModel loginRequest = new LoginRequestModel();
        loginRequest.setEmail("invalid@yandex.ru");
        loginRequest.setPassword("wrongpassword");

        Response response = given()
                .body(loginRequest)
                .when()
                .post("users/login")
                .then()
                .spec(ApiSpec.unauthorizedErrorSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());

        step("Check error response", () -> {
            assertThat(response.statusCode()).isEqualTo(401);
            assertThat(response.body().asString()).contains("CSRF Failed: Referer checking failed - no Referer.");
        });
    }
}

