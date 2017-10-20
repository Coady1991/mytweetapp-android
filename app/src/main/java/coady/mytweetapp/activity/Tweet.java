package coady.mytweetapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import coady.mytweetapp.R;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.main.TweetApp;

import static coady.mytweetapp.R.attr.title;


public class Tweet extends AppCompatActivity {

    private Button tweetButton;
    private EditText tweetText;
    private TextView counter;
    private TweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        app = (TweetApp) getApplication();

        tweetButton = (Button) findViewById(R.id.tweetButton);
        tweetText = (EditText) findViewById(R.id.tweetText);
        counter = (TextView) findViewById(R.id.counter);

        tweetText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                counter.setText(String.valueOf(140 - (tweetText.getText().toString().length())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void tweetButtonPressed (View view) {
        String text = tweetText.getText().toString();
        if(text.equals("")) {
            Toast toast = Toast.makeText(this, "Tweet must contain characters", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(!text.equals("")) {
            app.newTweet(new Tweeting(text));

            Toast toast = Toast.makeText(this, "Tweet Tweeted", Toast.LENGTH_SHORT);
            toast.show();
            Log.v("Tweet", "Tweet Pressed!");
        }
        tweetText.getText().clear();
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
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
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

