package api;

import config.LoginConfig;
import models.LoginRequest;
import models.LoginResponse;
import org.aeonbits.owner.ConfigFactory;

import static data.ApiEndpoints.LOGIN;
import static data.UserData.PASSWORD;
import static data.UserData.USER_NAME;
import static io.restassured.RestAssured.given;
import static specs.Specification.*;

public class AuthApi {
    public static LoginResponse authResponse () {
        LoginRequest userData = new LoginRequest();
        userData.setUserName(USER_NAME);
        userData.setPassword(PASSWORD);

        return
                given(request)
                        .body(userData)
                        .when()
                        .post(LOGIN)
                        .then()
                        .log().all()
                        .spec(successLoginResponse200)
                        .extract().as(LoginResponse.class);
    };
}
