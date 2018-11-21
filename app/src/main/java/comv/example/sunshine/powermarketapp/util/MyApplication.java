package comv.example.sunshine.powermarketapp.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Sunshine on 2018/11/14.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
