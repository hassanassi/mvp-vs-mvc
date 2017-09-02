package project.demo;

import android.app.Application;
import android.content.Context;

/**
 * Created by hassan on 9/2/2017.
 */

public class App extends Application {
    private static App appInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        appInstance=this;
    }


    public static App getAppInstance(){
        return appInstance;
    }

    public static Context getContext(){
        return appInstance.getApplicationContext();
    }
}
