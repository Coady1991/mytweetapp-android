package coady.mytweetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Tweet extends AppCompatActivity {

    private Button tweetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        tweetButton = (Button) findViewById(R.id.tweetButton);
    }

    public void tweetButtonPressed (View view) {
        Log.v("Tweet", "Tweet Pressed!");
    }
}

