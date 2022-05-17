import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestGithub {
    @Test
    public void firstTestCase() {
        String apiKey = "2364dc4e778c297b79cd557d87257914";
        String baseUrl = "https://api.themoviedb.org/3";
        String token = "/authentication/token/new?api_key=";
        String url = baseUrl + token + apiKey;
        Response response =
                given()
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String nextTitleLink = response.jsonPath().getString("success");
        System.out.println(nextTitleLink);
    }
}
