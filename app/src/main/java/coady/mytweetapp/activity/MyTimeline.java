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

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.R;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Coady on 06/01/2018.
 */

public class MyTimeline extends AppCompatActivity implements Callback<List<Tweeting>> {

    private ListView listView;
    private TweetApp app;
    private MyTimelineAdapter adapter;
    private String selectedItem;
    private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        app = (TweetApp) getApplication();

        listView = (ListView) findViewById(R.id.timelineList);
        adapter = new MyTimelineAdapter(this, app.tweets);
        listView.setAdapter(adapter);

        Call<List<Tweeting>> call = (Call<List<Tweeting>>) app.tweetService.myTweets(app.currentUser._id);
        call.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mytimeline_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tweet:
                startActivity(new Intent(this, Tweet.class));
                break;
            case R.id.timeline:
                startActivity(new Intent(this, Timeline.class));
                break;
            case R.id.followingTimeline:
                startActivity(new Intent(this, FollowingTimeline.class));
                break;
            case R.id.user:
                startActivity(new Intent(this, UsersList.class));
                break;
            case R.id.map:
                startActivity(new Intent(this, Map.class));
                break;
            case R.id.menuSettings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.menuLogout:
                startActivity(new Intent(this, Welcome.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Call<List<Tweeting>> call, Response<List<Tweeting>> response) {
        adapter.tweets = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Tweeting>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving tweets", Toast.LENGTH_LONG);
        toast.show();
    }
}

class MyTimelineAdapter extends ArrayAdapter<Tweeting> {
    private Context context;
    public List<Tweeting> tweets = new ArrayList<Tweeting>();

    public MyTimelineAdapter(Context context, List<Tweeting> tweets) {
        super(context, R.layout.row_layout, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_layout, parent, false);
        Tweeting tweet = tweets.get(position);

        TextView tweetView = (TextView) view.findViewById(R.id.row_tweet);
        TextView tweetDate = (TextView) view.findViewById(R.id.date);

        tweetView.setText(tweet.tweet);
        tweetDate.setText(tweet.date);

        return view;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }
}