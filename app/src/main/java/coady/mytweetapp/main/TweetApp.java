package coady.mytweetapp.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.model.User;

public class TweetApp extends Application {

    public final int limit = 140;
    public int totalTweet = 0;
    public List<Tweeting> tweets = new ArrayList<Tweeting>();
    public List<User> users = new ArrayList<User>();

    public Boolean newTweet(Tweeting tweet) {
        boolean limitAchieved = totalTweet > limit;

        if(!limitAchieved) {
            tweets.add(tweet);
        } else {
            Toast toast = Toast.makeText(this, "Limit Exceeded!", Toast.LENGTH_SHORT);
            toast.show();
        }

        return limitAchieved;
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
