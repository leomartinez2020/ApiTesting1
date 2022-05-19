package Requests;

import Utils.SetProperties;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PostRequests extends SetProperties {
    JSONObject jsonObject = new JSONObject();
    GetRequests getRequest = new GetRequests();
    private  String validateTokenPath = "/authentication/token/validate_with_login";

    private String createSessionPath = "/authentication/session/new";

    private String rateMoviePath = "/movie/";

    private String listPath = "/list/";

    public PostRequests() {
        super();
    }

    public String validateToken() {
        //getRequest.generateToken();
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token", getRequest.generateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + validateTokenPath)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public String createSession() {
        jsonObject
                .put("request_token", validateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + createSessionPath)
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public void rateMovie(int movieId, float value) {
        jsonObject
                .put("value", value);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + rateMoviePath + movieId + "/rating")
                .then()
                .statusCode(201)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
    }

    public void addMovieToList(int movieId, String listId) {
        jsonObject
                .put("media_id", movieId);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + listPath + listId + "/add_item")
                .then()
                .statusCode(201)
                .extract()
                .response();
        Assert.assertEquals("The item/record was updated successfully.", response.jsonPath().getString("status_message"));
    }
}
