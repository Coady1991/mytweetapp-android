package coady.mytweetapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import coady.mytweetapp.R;
import coady.mytweetapp.activity.Welcome;
import coady.mytweetapp.main.TweetApp;
import coady.mytweetapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity implements Callback<User> {

    private TweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        app = (TweetApp) getApplication();
    }

    public void signupPressed (View view) {
        EditText firstName = (EditText)  findViewById(R.id.firstName);
        EditText lastName  = (EditText)  findViewById(R.id.lastName);
        EditText email     = (EditText)  findViewById(R.id.Email);
        EditText password  = (EditText)  findViewById(R.id.Password);

        String valid = email.getText().toString().trim();

        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // http://www.donnfelker.com/android-validation-with-edittext/ Used to validate input and return error
        if(firstName.getText().toString().length() == 0) {
            firstName.setError("First name is required");
        } else if(lastName.getText().toString().length() == 0) {
            lastName.setError("Last name is required");
        } else if (!valid.matches(pattern)) {
            email.setError("Email must be a valid Email");
        } else if(password.getText().toString().length() == 0) {
            password.setError("Password name is required");
        } else {
            User user = new User (firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

            TweetApp app = (TweetApp) getApplication();
            Call<User> call = (Call<User>) app.tweetService.createUser(user);
            call.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        app.users.add(response.body());
        startActivity(new Intent(this, Welcome.class));
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        app.tweetServiceAvailable = false;
        Toast toast = Toast.makeText(this, "MyTweet Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
        startActivity (new Intent(this, Welcome.class));

    }
}
