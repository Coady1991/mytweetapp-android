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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.R;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.model.User;
import coady.mytweetapp.settings.SettingsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Coady on 03/01/2018.
 */

public class UsersList extends AppCompatActivity implements Callback<List<User>> {

    private ListView listView;
    private TweetApp app;
    private UserAdapter adapter;
    private Button follow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userslist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (TweetApp) getApplication();

        listView = (ListView) findViewById(R.id.usersList);
        adapter = new UserAdapter(this, app.users);
        listView.setAdapter(adapter);

        Call<List<User>> call = (Call<List<User>>) app.tweetService.getAllUsers();
        call.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userslist_menu, menu);
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
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        adapter.users = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving users", Toast.LENGTH_LONG);
        toast.show();
    }
}

class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private TweetApp app;
    public List<User> users = new ArrayList<User>();

    public UserAdapter(Context context, List<User> users) {
        super(context, R.layout.users_row_layout, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.users_row_layout, parent, false);
        User user = users.get(position);
        TextView userName = (TextView) view.findViewById(R.id.userName);
        userName.setText(user.firstName + " " + user.lastName);

        TextView button = (TextView) view.findViewById(R.id.follow);
        if(app.currentUser.following != null) {
            if(app.currentUser.following.contains(user._id)) {
                button.setText("Unfollow");
            } else {
                button.setText("Follow");
            }
        }

        return view;
    }
}