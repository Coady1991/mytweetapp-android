package coady.mytweetapp;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TweetApp extends Application {

    public final int limit = 140;
    public int totalTweet = 0;
    public List<Tweet> tweets = new ArrayList<Tweet>();

    public boolean newTweet(Tweet tweets) {
        boolean limitAchieved = totalTweet > limit;

        Toast toast = Toast.makeText(this, "Character Limit Exceeded!", Toast.LENGTH_SHORT);
        toast.show();

        return limitAchieved;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Tweet", "MyTweet App Started!");
    }
}