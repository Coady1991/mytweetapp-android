package coady.mytweetapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Tweet", "MyTweet App Started!");
    }
}
