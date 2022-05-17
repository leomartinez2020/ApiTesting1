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
        postRequest.addMovieToList(799876, "8200567");
    }
}
