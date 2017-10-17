package coady.mytweetapp.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.model.User;

public class TweetApp extends Application {
    
    public List<Tweeting> tweets = new ArrayList<Tweeting>();
    public List<User> users = new ArrayList<User>();

    public void newTweet(Tweeting tweet) {
        tweets.add(tweet);
    }

    public void newUser(User user) {
        users.add(user);
    }

    public boolean validUser (String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Tweet", "MyTweet App Started!");
    }
}
