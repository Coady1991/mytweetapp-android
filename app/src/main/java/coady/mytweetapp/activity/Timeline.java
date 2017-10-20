package coady.mytweetapp.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import coady.mytweetapp.R;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.main.TweetApp;

public class Timeline extends AppCompatActivity {

    private ListView listView;
    private TweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        app = (TweetApp) getApplication();

        listView = (ListView) findViewById(R.id.timelineList);
        TweetAdapter adapter = new TweetAdapter(this, app.tweets);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tweet: startActivity(new Intent(this, Tweet.class));
                break;
            case R.id.menuSettings:  Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }
}

class TweetAdapter extends ArrayAdapter<Tweeting> {
    private Context context;
    public List<Tweeting> tweets;

    public TweetAdapter(Context context, List<Tweeting> tweets) {
        super(context, R.layout.row_layout, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_layout, parent, false);
        Tweeting tweet = tweets.get(position);
        TextView tweetView = (TextView) view.findViewById(R.id.row_tweet);
        TextView date = (TextView) view.findViewById(R.id.date);

        tweetView.setText(tweet.tweet);
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yy hh:mm a");
        date.setText("Posted " + sf.format(now));

        return view;
    }
}
