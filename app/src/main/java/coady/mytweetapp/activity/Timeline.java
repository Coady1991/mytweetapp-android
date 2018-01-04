package coady.mytweetapp.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import coady.mytweetapp.R;
import coady.mytweetapp.main.TweetService;
import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static coady.mytweetapp.android.helpers.IntentHelper.navigateUp;

public class Timeline extends AppCompatActivity  implements Callback<List<Tweeting>> {

    private ListView listView;
    private TweetApp app;
    private TweetAdapter adapter;
    private String selectedItem;
    private final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (TweetApp) getApplication();

        listView = (ListView) findViewById(R.id.timelineList);
        adapter = new TweetAdapter(this, app.tweets);
        listView.setAdapter(adapter);

        Call<List<Tweeting>> call = (Call<List<Tweeting>>) app.tweetService.getAllTweets();
        call.enqueue(this);



//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                adapter.remove(adapter.getItem(i));
//                adapter.notifyDataSetChanged();
//            }
//        });

        // http://piyushovte.blogspot.ie/2011/03/listview-data-select-and-delete.html
        listView.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this tweet?");
                builder.setCancelable(false);
                //Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // Due to getting tweets from API issue here with deleting a tweet
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        //adapter.remove(adapter.getItem(i));
                        //Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
                        //adapter.notifyDataSetChanged();

                        Toast.makeText(getApplicationContext(), "Unable to delete tweet at this time, sorry!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case R.id.tweet: startActivity(new Intent(this, Tweet.class));
                //break;
            case R.id.user:
                startActivity(new Intent(this, UsersList.class));
                break;
            case R.id.menuSettings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.menuLogout:
                startActivity(new Intent(this, Welcome.class));
                break;
            case android.R.id.home:
                navigateUp(this);
                return true;
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

class TweetAdapter extends ArrayAdapter<Tweeting> {
    private Context context;
    public List<Tweeting> tweets = new ArrayList<Tweeting> ();

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

        // Sorts tweets with newest first, but does not work because String date begins with day of week Mon, Tue etc.
        // https://stackoverflow.com/questions/2592501/how-to-compare-dates-in-java#answer-21085919
//        Collections.sort(tweets, new Comparator<Tweeting>() {
//            @Override
//            public int compare(Tweeting t1, Tweeting t2) {
//
//                if (t1.date.compareTo(t2.date) > 0) {
//                    return 1;
//                } else if (t1.date.compareTo(t2.date) <0) {
//                    return -1;
//                } else {
//                    return 0;
//                }
//
//            }
//        });
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