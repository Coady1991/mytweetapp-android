package coady.mytweetapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Timeline extends AppCompatActivity {

    ListView listView;

    static final String[] string = new String [] {
            "Tweet",
            "Hello World",
            "I like to tweet"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        listView = (ListView) findViewById(R.id.timelineList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, string);
        listView.setAdapter(adapter);
    }
}
