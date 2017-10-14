package coady.mytweetapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Tweet extends AppCompatActivity {

    private Button tweetButton;
    private TweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        app = (TweetApp) getApplication();

        tweetButton = (Button) findViewById(R.id.tweetButton);
    }

    public void tweetButtonPressed (View view) {
        Toast toast = Toast.makeText(this, "Tweet Tweeted", Toast.LENGTH_SHORT);
        toast.show();
        Log.v("Tweet", "Tweet Pressed!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.timeline:
                startActivity(new Intent(this, Timeline.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

