package coady.mytweetapp.model;

import java.util.ArrayList;

import static coady.mytweetapp.android.helpers.LogHelpers.info;

public class UserPortfolio {
    public ArrayList<User> users;
    private UserSerializer serializer;

    public UserPortfolio(UserSerializer serializer) {
        this.serializer = serializer;
        try {
            users = serializer.loadUsers();
        } catch (Exception e) {
            info(this, "Error loading users: " + e.getMessage());
            users = new ArrayList<User>();
        }
    }

    public boolean saveUsers() {
        try {
            serializer.saveUsers(users);
            info(this, "Users saved to file");
            return true;
        } catch (Exception e) {
            info(this, "Error saving users: " + e.getMessage());
            return false;
        }
    }
}
