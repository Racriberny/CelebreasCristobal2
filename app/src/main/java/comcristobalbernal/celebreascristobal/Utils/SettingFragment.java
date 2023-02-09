package comcristobalbernal.celebreascristobal.Utils;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


import comcristobalbernal.celebreascristobal.R;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    Preference username, password,ip,puerto;
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.opcionespreferencias,rootKey);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        username = findPreference("etp_usuario");
        password = findPreference("etp_password");
        ip = findPreference("ipservidor");
        puerto = findPreference("puertoservidor");
        username.setSummary(prefs.getString("name",""));
        password.setSummary(prefs.getString("contrasena",""));
        ip.setSummary(prefs.getString("ip",""));
        puerto.setSummary(prefs.getString("puerto",""));

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        username.setSummary(sharedPreferences.getString("name",""));
        password.setSummary(sharedPreferences.getString("contrasena",""));
        ip.setSummary(sharedPreferences.getString("ip",""));
        puerto.setSummary(sharedPreferences.getString("puerto",""));
    }
}
