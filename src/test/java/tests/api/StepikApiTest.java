package tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import api.models.CourseResponseModel;
import api.models.LoginRequestModel;
import api.models.LoginResponseModel;
import api.models.UserResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import api.specs.ApiSpec;

import static io.qameta.allure.Allure.step;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("api")
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



    @Test
    public void testGetUser() {
        RestAssured.baseURI = "https://stepik.org/api";

        Response response = given()
                .when()
                .get("/users/23008752")
                .then()
                .spec(ApiSpec.successResponseSpec)
                .extract().response();

        System.out.println("Response: " + response.asString());
        UserResponseModel userResponse = response.as(UserResponseModel.class);
        assertThat(userResponse.getUsers()).isNotEmpty();

        UserResponseModel.User user = userResponse.getUsers().get(0);
        assertThat(user.getId()).isEqualTo(23008752);
        assertThat(user.getFirst_name()).isEqualTo("Оксана");
        assertThat(user.getLast_name()).isEqualTo("Еськова");
        assertThat(user.getDetails()).isNotEmpty();
        assertThat(user.getAvatar()).isNotEmpty();
        assertThat(user.getFollowers_count()).isGreaterThan(0);
    }

    @Test
    public void testUserLogin() {
        RestAssured.baseURI = "https://stepik.org/api";

        LoginRequestModel loginRequest = new LoginRequestModel();
        loginRequest.setEmail("polyanow@yandex.ru");
        loginRequest.setPassword("Gjgfgjgf76");

        Response response = given()
                .header("accept", "*/*")
                .header("accept-language", "ru,en;q=0.9")
                .header("content-type", "application/json; charset=UTF-8")
                .header("referer", "https://stepik.org/catalog?auth=login")
                .header("x-csrftoken", "COzo7TQx7LhqYi7RsKwyO6Xbq7IjfFffbFiY90QneiYRIdW13uV1SHuOT7ZhNBe7") // Убедитесь, что CSRF токен актуален
                .body(loginRequest)
                .when()
                .post("/users/login")
                .then()
                .extract().response();

        System.out.println("Response: " + response.asString());

        // Проверка статуса ответа
        assertThat(response.statusCode()).isEqualTo(200);

        // Десериализация ответа
        LoginResponseModel loginResponse = response.as(LoginResponseModel.class);
        assertThat(loginResponse.getUser()).isNotNull();
        assertThat(loginResponse.getUser().getEmail()).isEqualTo("polya@yandex.ru");
      //  assertThat(loginResponse.getUser().isActive()).isTrue(); // Убедитесь, что метод называется isActive()

        // Проверка наличия токена
        assertThat(response.asString()).contains("token");

    }

    @Test
    public void testUserLogout() {
        RestAssured.baseURI = "https://stepik.org/api";

        Response response = given()
                .header("accept", "*/*")
                .header("accept-language", "ru,en;q=0.9")
                .header("content-type", "application/json; charset=UTF-8")
                .header("x-csrftoken", "COzo7TQx7LhqYi7RsKwyO6Xbq7IjfFffbFiY90QneiYRIdW13uV1SHuOT7ZhNBe7")
                .when()
                .post("/users/logout")
                .then()
                .statusCode(204)
                .extract().response();

        System.out.println("Response: " + response.asString());

        assertThat(response.asString()).isEmpty();
    }
}

