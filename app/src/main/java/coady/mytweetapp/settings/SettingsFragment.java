package coady.mytweetapp.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import coady.mytweetapp.R;

public class SettingsFragment extends PreferenceFragment {

    private SharedPreferences prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        prefs = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
    }
}
