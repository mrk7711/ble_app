package com.example.ble_application;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguageManager {

    private Context ct;
    private SharedPreferences sharedPreferences;
    public LanguageManager(Context ctx)
    {
        ct=ctx;
        sharedPreferences=ct.getSharedPreferences("LANG",Context.MODE_PRIVATE);

    }
    public void updateResource(String code)
    {
        Locale locale = new Locale(code);
        Locale.setDefault(locale);
        Resources resources = ct.getResources();
        Configuration config = resources.getConfiguration();
        //config.setLocale(locale);
        //config.setLayoutDirection(locale);
        config.locale=locale;
        resources.updateConfiguration(config,resources.getDisplayMetrics());
        setLang(code);

    }
    public String getLang()
    {
        return sharedPreferences.getString("lang","en");
    }
    public void setLang(String code)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("lang",code);
        editor.commit();
    }
}
