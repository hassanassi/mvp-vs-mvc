package project.demo.model.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import project.demo.App;


/**
 * Created by hassan on 11/10/2016.
 */
public class SyncReceiver extends BroadcastReceiver {

    // ========================================================================
    // Variables
    // ========================================================================
    private IntentFilter intentFilter;
    private boolean registered = false;
    private onReceive onReceive;

    public SyncReceiver(String filterAction,onReceive onReceive) {
        this.intentFilter = new IntentFilter(filterAction);
        this.onReceive=onReceive;
    }

    // ========================================================================
    // Override Method
    // ========================================================================

    /**
     * This will be called whenever an Intent
     * with an intent filter already defined as filterAction is broadcast
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (onReceive != null)
            onReceive.onReceive(context, intent);

        if (registered)
            unregister();
    }

    // ========================================================================
    // Methods
    // ========================================================================

    public void unregister() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(App.getContext());
        localBroadcastManager.unregisterReceiver(this);
        this.registered = false;
        this.onReceive = null;

    }

    public void register() {
        this.registered = true;
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(App.getContext());
        localBroadcastManager.registerReceiver(this, intentFilter);
    }

    // ========================================================================
    // GETTERS / SETTERS
    // ========================================================================
    public void setInterface(onReceive onReceive) {
        this.onReceive = onReceive;
    }

    // ========================================================================
    // Inner Classes
    // ========================================================================
    public interface onReceive {
        void onReceive(Context context, Intent intent);
    }
}
