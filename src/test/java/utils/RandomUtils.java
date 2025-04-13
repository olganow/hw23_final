package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {

    private String userPassword;
    private String userEmail;

    Faker faker = new Faker(new Locale("en-GB"));

    public String getUserPassword() {
        userPassword = faker.number().digits(7);
        return userPassword;
    }

    public String getUserEmail() {
        userEmail = faker.internet().emailAddress();
        return userEmail;
    }

}
