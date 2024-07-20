package step;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static base.Url.*;
import static io.restassured.RestAssured.*;

public class ApiStep {
    public static RequestSpecification spec() {
        return given()
                .baseUri(BASE_URI)
                .basePath(API_AUTH)
                .contentType(ContentType.JSON);
    }
}
