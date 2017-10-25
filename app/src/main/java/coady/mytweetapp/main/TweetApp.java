package coady.mytweetapp.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import coady.mytweetapp.model.Tweeting;
import coady.mytweetapp.model.User;
import coady.mytweetapp.model.UserPortfolio;
import coady.mytweetapp.model.UserSerializer;

public class TweetApp extends Application {
    
    public List<Tweeting> tweets = new ArrayList<Tweeting>();
    public ArrayList<User> users = new ArrayList<User>();
    private static final String FILENAME = "userportfolio.json";
    public UserPortfolio userPortfolio;

    @Override
    public void onCreate() {
        super.onCreate();
        UserSerializer userSerializer = new UserSerializer(this, FILENAME);
        userPortfolio = new UserPortfolio(userSerializer);
        users = userPortfolio.users;
        Log.v("Tweet", "MyTweet App Started!");
    }

    public void newTweet(Tweeting tweet) {
        tweets.add(tweet);
    }

    public void newUser(User user) {
        users.add(user);
        userPortfolio.users = users;
        userPortfolio.saveUsers();
    }

    public boolean validUser (String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
