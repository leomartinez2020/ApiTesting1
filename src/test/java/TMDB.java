import Requests.DeleteRequests;
import Requests.GetRequests;
import Requests.PostRequests;
import org.testng.annotations.Test;

public class TMDB {
    @Test
    public void getToken() {
        GetRequests getRequest = new GetRequests();
        getRequest.generateToken();
    }

    @Test
    public void validateToken() {
        PostRequests postRequest = new PostRequests();
        postRequest.validateToken();
    }

    @Test
    void createSession() {
        PostRequests postRequest = new PostRequests();
        postRequest.createSession();
    }

    @Test
    void rateMovie() {
        PostRequests postRequest = new PostRequests();
        postRequest.rateMovie(75174, 9);
    }

    @Test
    public void deleteRating() {
        DeleteRequests deleteRequest = new DeleteRequests();
        deleteRequest.deleteRating(195623);
    }

    @Test
    public void addMovie() {
        PostRequests postRequest = new PostRequests();
        postRequest.addMovieToList(799876, "8200420");
    }

    @Test
    public void confirmMovieInList() {
        GetRequests listDetailsRequest = new GetRequests();
        listDetailsRequest.assertMovieInList("8200420", "The Outfit");
    }

    @Test
    public void confirmMovieGenre() {
        GetRequests listDetailsRequest = new GetRequests();
        listDetailsRequest.assertMovieListName(799876, "Crime");
    }

    @Test
    public void assertCastGreaterThan10() {
        GetRequests listDetailsRequest = new GetRequests();
        listDetailsRequest.assertCastLength(799876, 10);
    }
}
