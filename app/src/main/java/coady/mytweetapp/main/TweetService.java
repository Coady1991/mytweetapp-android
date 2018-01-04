package coady.mytweetapp.main;

import java.util.List;

import coady.mytweetapp.activity.Tweet;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;



public interface TweetService
{
    @GET("/api/users")
    Call<List<User>> getAllUsers();

    @GET("/api/users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("/api/users")
    Call<User> createUser(@Body User User);

    @POST("/api/users/authenticate")
    Call<User> authenticate(@Body User user);

    @GET("/api/tweets")
    Call<List<Tweeting>> getAllTweets();

    @POST("/api/tweets")
    Call<Tweeting> createTweet(@Body Tweeting tweeting);

    @DELETE("/api/tweets/{id}")
    Call<String> deleteTweet(@Path("id") String id);

    @POST("/api/users/{id}/follow")
    Call<User> follow(@Path("id") String _id, @Body String user);

    @POST("/api/users/{id}/unfollow")
    Call<User> unfollow(@Path("id") String _id, @Body String user);
}
