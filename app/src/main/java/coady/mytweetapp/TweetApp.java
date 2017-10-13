package coady.mytweetapp;

import android.app.Application;
import android.util.Log;

public class TweetApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Tweet", "MyTweet App Started!");
    }
}
