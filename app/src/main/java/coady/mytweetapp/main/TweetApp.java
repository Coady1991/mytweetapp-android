package coady.mytweetapp.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.model.User;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import coady.mytweetapp.model.UserPortfolio;
//import coady.mytweetapp.model.UserSerializer;

public class TweetApp extends Application {

    public TweetService tweetService;
    public boolean tweetServiceAvailable = false;
    public String service_url = "https://mytweetapp-12.herokuapp.com/";

    public static User             currentUser;
    public List<Tweeting> tweets = new ArrayList<Tweeting>();
    public List<User> users = new ArrayList<User>();

    @Override
    public void onCreate() {
        super.onCreate();
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(service_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        tweetService = retrofit.create(TweetService.class);

        Log.v("Tweet", "MyTweetApp started");
    }

    public void newUser(User user) {
        users.add(user);
    }

    public void newTweet(Tweeting tweet) {
        tweets.add(tweet);
    }

    public boolean validUser (String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public static String getCurrentUser(){
        return currentUser._id;
    }


//    private static final String FILENAME = "userportfolio.json";
//    public UserPortfolio userPortfolio;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        UserSerializer userSerializer = new UserSerializer(this, FILENAME);
//        userPortfolio = new UserPortfolio(userSerializer);
//        users = userPortfolio.users;
//        Log.v("Tweet", "MyTweet App Started!");
//    }
//

//
//    public void newUser(User user) {
//        users.add(user);
//        userPortfolio.users = users;
//        userPortfolio.saveUsers();
//    }
//

}