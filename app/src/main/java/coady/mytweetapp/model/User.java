package coady.mytweetapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class User {
    public Long userId;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    private static final String JSON_USERID    = "userId";
    private static final String JSON_FIRSTNAME = "firstName";
    private static final String JSON_LASTNAME  = "lastName";
    private static final String JSON_EMAIL     = "email";
    private static final String JSON_PASSWORD  = "password";

    public User(String firstName, String lastName, String email, String password) {
        userId = unsignedLong();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(JSONObject json) throws JSONException {
        userId = json.getLong(JSON_USERID);
        firstName = json.getString(JSON_FIRSTNAME);
        lastName = json.getString(JSON_LASTNAME);
        email = json.getString(JSON_EMAIL);
        password = json.getString(JSON_PASSWORD);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_USERID, Long.toString(userId));
        json.put(JSON_FIRSTNAME, firstName);
        json.put(JSON_LASTNAME, lastName);
        json.put(JSON_EMAIL, email);
        json.put(JSON_PASSWORD, password);
        return json;
    }

    /**
     * Generate a long greater than zero
     * @return Unsigned Long value greater than zero
     */
    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }
}
