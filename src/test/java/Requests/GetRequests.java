package Requests;

import Utils.SetProperties;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class GetRequests extends SetProperties {
    private String token_path = "/authentication/token/new";

    public GetRequests() {
        super();
    }

    public String generateToken() {
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + token_path)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }
}
