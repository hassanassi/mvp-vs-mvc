package project.demo.model.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import project.demo.App;
import project.demo.model.Item;


/**
 * Created by hassan on 9/2/2017.
 */

public class SyncService extends IntentService {


    public SyncService(){
        super("SyncService");
    }
    public SyncService(String name) {
        super(name);
    }

    public static void startService(){
        Intent intent=new Intent(App.getContext(),SyncService.class);
        App.getAppInstance().startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        requestData();
    }


    private void requestData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        //example url
        String url ="http://feeds.bbci.co.uk/news/rss.xml?edition=us#";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //parse the xml to list of items
                        ArrayList<Item> itemList=new Parser().parseXml(response);
                        sendData(itemList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sendData(null);
            }
        });
        queue.add(stringRequest);
    }


    private void sendData(ArrayList<Item> itemList){
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(SyncService.this);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("test");
        Bundle bundle=new Bundle();

        //if null means error
        if(itemList==null)
            bundle.putString("error","error");
        else{
            bundle.putSerializable("itemList",itemList);
        }

        broadcastIntent.putExtras(bundle);
        localBroadcastManager.sendBroadcast(broadcastIntent);
    }
}

