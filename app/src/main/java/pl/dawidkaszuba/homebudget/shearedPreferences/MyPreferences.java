package pl.dawidkaszuba.homebudget.shearedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {

    private static MyPreferences myPreferences;
    private SharedPreferences sharedPreferences;

    public static MyPreferences getInstance(Context context) {
        if (myPreferences == null) {
            myPreferences = new MyPreferences(context);
        }
        return myPreferences;
    }

    private MyPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
    }

    public void savePreference(String key,String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor .putString(key, value);
        prefsEditor.apply();
    }

    public String getPreference(String key) {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }
}
