package step;

import base.UserLogin;
import io.qameta.allure.Step;
import lombok.Setter;

import static io.restassured.RestAssured.*;

@Setter
public class UserPreStep {
    private UserLogin user;
    private UserLogin userLogin;

    @Step("Create user")
    public void createUser() {
        given().log().all()
                .spec(ApiStep.spec())
                .body(user)
                .when()
                .post("/register");
    }

    @Step("Get accessToken")
    public String getAccessToken() {
        return given().log().all()
                .spec(ApiStep.spec())
                .body(userLogin)
                .when().post("/login")
                .then()
                .extract().path("accessToken");
    }

    @Step("Delete user")
    public void deleteUser() {
        String accessToken = getAccessToken();
        if (accessToken != null)
            given().log().all()
                    .spec(ApiStep.spec())
                    .header("Authorization", accessToken)
                    .delete("/user");
    }
}