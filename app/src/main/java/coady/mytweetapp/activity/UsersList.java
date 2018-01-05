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
import android.widget.AdapterView;
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

public class UsersList extends AppCompatActivity implements Callback<List<User>>, AdapterView.OnItemClickListener {

    private ListView listView;
    private TweetApp app;
    private UserAdapter adapter;
    private Button follow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userslist);

        app = (TweetApp) getApplication();

        listView = (ListView) findViewById(R.id.usersList);
        adapter = new UserAdapter(this, app.users);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        //follow = (Button) findViewById(R.id.follow);
        //follow.setOnClickListener(this);

        Call<List<User>> call = (Call<List<User>>) app.tweetService.getAllUsers();
        call.enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userslist_menu, menu);
        return true;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Call<List<User>> call1 = (Call<List<User>>) app.tweetService.getAllUsers();
//        call1.enqueue(this);
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tweet:
                startActivity(new Intent(this, Tweet.class));
                break;
            case R.id.timeline:
                startActivity(new Intent(this, Timeline.class));
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
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        adapter.users = response.body();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Toast toast = Toast.makeText(this, "Error retrieving users", Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        final User user = adapter.getItem(position);
        String userId = user._id;
        if(app.currentUser.following != null) {
            if(app.currentUser.following.contains(userId)) {
                Call<User> call1 = (Call<User>) app.tweetService.unfollow(app.currentUser._id, userId);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast toast = Toast.makeText(UsersList.this, "Unfollowed " + user.firstName + " " + user.lastName, Toast.LENGTH_LONG);
                        toast.show();
                        app.currentUser = response.body();
                        startActivity(new Intent(UsersList.this, Timeline.class));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast toast = Toast.makeText(UsersList.this, "Error unfollowing " + user.firstName + " " + user.lastName, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            } else if (!app.currentUser.following.contains(userId)) {
                Call<User> call1 = (Call<User>) app.tweetService.follow(app.currentUser._id, userId);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast toast = Toast.makeText(UsersList.this, "Started following " + user.firstName + " " + user.lastName, Toast.LENGTH_LONG);
                        toast.show();
                        app.currentUser = response.body();
                        startActivity(new Intent(UsersList.this, Timeline.class));
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast toast = Toast.makeText(UsersList.this, "Error following " + user.firstName + " " + user.lastName, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        }
    }

    // Intended on using this with listener on the button(line 54 & 55) but it does not have the correct parameters
    // as position and id needed
//    @Override
//    public void onClick(View view) {
//
//    }

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