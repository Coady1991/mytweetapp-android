package coady.mytweetapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import coady.mytweetapp.R;
import coady.mytweetapp.main.TweetApp;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signinPressed (View view) {
        TweetApp app = (TweetApp) getApplication();

        TextView email     = (TextView)  findViewById(R.id.Email);
        TextView password  = (TextView)  findViewById(R.id.Password);

        if (app.validUser(email.getText().toString(), password.getText().toString())) {
            startActivity (new Intent(this, Tweet.class));
        } else {
            Toast toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
