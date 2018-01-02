package coady.mytweetapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import coady.mytweetapp.R;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Welcome extends AppCompatActivity implements Callback<List<User>> {

    private TweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        app = (TweetApp) getApplication();
    }

    @Override
    public void onResume() {
        super.onResume();
        app.currentUser = null;
        Call<List<User>> call1 = (Call<List<User>>)app.tweetService.getAllUsers();
        call1.enqueue(this);
    }

    public void loginPressed (View view) {
        if (app.tweetServiceAvailable) {
            startActivity (new Intent(this, Login.class));
        } else {
            serviceUnavailableMessage();
        }
    }

    public void signupPressed(View view) {
        if (app.tweetServiceAvailable) {
            startActivity (new Intent(this, Signup.class));
        } else {
            serviceUnavailableMessage();
        }
    }

    @Override
    // https://stackoverflow.com/questions/5914040/onbackpressed-to-hide-not-destroy-activity
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        serviceAvailableMessage();
        app.users = response.body();
        app.tweetServiceAvailable = true;
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        app.tweetServiceAvailable = false;
        serviceUnavailableMessage();
    }

    void serviceUnavailableMessage()
    {
        Toast toast = Toast.makeText(this, "MyTweet Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
    }

    void serviceAvailableMessage()
    {
        Toast toast = Toast.makeText(this, "MyTweet Contacted Successfully", Toast.LENGTH_LONG);
        toast.show();
    }
}
