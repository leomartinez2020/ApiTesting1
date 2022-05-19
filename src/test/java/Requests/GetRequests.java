package Requests;

import Utils.SetProperties;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class GetRequests extends SetProperties {
    private String token_path = "/authentication/token/new";
    private String listPath = "/list/";
    JSONObject jsonObject = new JSONObject();

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

    public void assertMovieInList(String listId, String expectedName) {
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + listPath + listId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        //System.out.println(response.jsonPath().get("items[0].original_title").toString());
        String actualName = response.jsonPath().get("items[0].original_title").toString();
        //System.out.println(actualName);
        Assert.assertEquals(expectedName, actualName);
        //return response.jsonPath().getString("request_token");
    }

    public void assertMovieListName(int movieId, String expectedName) {
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + "/movie/" + movieId)
                .then()
                .statusCode(200)
                .extract()
                .response();
        String actualName = response.jsonPath().get("genres[0].name").toString();
        System.out.println(actualName);
        Assert.assertEquals(expectedName, actualName);
    }

    public void assertCastLength(int movieId, int minLength) {
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url() + "/movie/" + movieId + "/credits")
                .then()
                .statusCode(200)
                .extract()
                .response();
        ArrayList castList = response.jsonPath().get("cast");
        System.out.println(response.jsonPath().get("cast"));
        //Assert.assertTrue(castList.length() > minLength);
    }
}
