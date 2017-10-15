package coady.mytweetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void loginPressed (View view) {
        startActivity (new Intent(this, Tweet.class));
    }

    public void signupPressed(View view) {
        startActivity(new Intent(this, Tweet.class));
    }
}
