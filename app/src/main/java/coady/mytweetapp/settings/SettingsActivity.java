package coady.mytweetapp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import coady.mytweetapp.R;
import coady.mytweetapp.activity.FollowingTimeline;
import coady.mytweetapp.activity.Map;
import coady.mytweetapp.activity.Timeline;
import coady.mytweetapp.activity.Tweet;
import coady.mytweetapp.activity.UsersList;
import coady.mytweetapp.activity.Welcome;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.tweet: startActivity(new Intent(this, Tweet.class));
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
            case R.id.menuLogout:   startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

}

