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

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
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
            app.newUser(user);

            startActivity (new Intent(this, Welcome.class));
        }
    }
}
