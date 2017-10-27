package coady.mytweetapp.activity;

import android.content.DialogInterface;
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

import java.text.DateFormat;
import java.util.Date;

import coady.mytweetapp.R;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.settings.SettingsActivity;

import static coady.mytweetapp.R.attr.title;
import static coady.mytweetapp.R.string.tweet;
import static coady.mytweetapp.android.helpers.ContactHelper.getContact;
import static coady.mytweetapp.android.helpers.ContactHelper.getEmail;
import static coady.mytweetapp.android.helpers.ContactHelper.sendEmail;
import static coady.mytweetapp.android.helpers.IntentHelper.selectContact;


public class Tweet extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CONTACT = 1;

    private Button tweetButton;
    private EditText tweetText;
    private TextView counter;
    private TweetApp app;
    private TextView tweetDate;
    private Button   contactButton;
    private Button emailButton;
    private String emailAddress = "";
    private String emailBody = "";
    private Tweeting tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        app = (TweetApp) getApplication();

        tweetButton = (Button) findViewById(R.id.tweetButton);
        tweetText = (EditText) findViewById(R.id.tweetText);
        counter = (TextView) findViewById(R.id.counter);
        tweetDate = (TextView) findViewById(R.id.tweetDate);
        contactButton = (Button) findViewById(R.id.contact);
        emailButton = (Button) findViewById(R.id.sendEmail);

        contactButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        tweetDate.setText(currentDateTimeString);


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
        String date = tweetDate.getText().toString();
        if(text.equals("")) {
            Toast toast = Toast.makeText(this, "Tweet must contain characters", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(!text.equals("")) {
            tweet = new Tweeting(text, date);
            app.newTweet(tweet);

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
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.contact : selectContact(this, REQUEST_CONTACT);
                break;
            case R.id.sendEmail:
                sendEmail(this, emailAddress,
                        getString(R.string.newTweet), tweetText.getText().toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case REQUEST_CONTACT:
                String name = getContact(this, data);
                emailAddress = getEmail(this, data);
                contactButton.setText(name + " : " + emailAddress);
                //residence.tenant = name;
                break;
        }
    }
}

