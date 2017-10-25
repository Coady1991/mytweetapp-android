package coady.mytweetapp.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class UserSerializer {
    private Context Context;
    private String Filename;

    public UserSerializer(Context context, String filename) {
        Context = context;
        Filename = filename;
    }

    public void saveUsers(ArrayList<User> users) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (User user : users)
            array.put(user.toJSON());

        Writer writer = null;
        try {
            OutputStream out = Context.openFileOutput(Filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<User> loadUsers() throws IOException, JSONException {
        ArrayList<User> users = new ArrayList<User>();
        BufferedReader reader = null;
        try {
            InputStream in = Context.openFileInput(Filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of users from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                users.add(new User(array.getJSONObject(i)));
            }
        }
        catch(FileNotFoundException e) {

        }
        finally {
            if (reader != null)
                reader.close();
        }
        return users;
    }
}