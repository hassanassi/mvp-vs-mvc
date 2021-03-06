package project.demo.model;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import project.demo.model.service.SyncReceiver;
import project.demo.model.service.SyncService;


/**
 * Created by hassan on 8/31/2017.
 */

public class DataManager implements SyncReceiver.onReceive {

    public interface Actions{
        void onFinish(ArrayList<Item> itemList);
        void onError();
    }

    SyncReceiver syncReceiver;
    Actions actions;

    ArrayList<Item> itemList;

    public void loadData(Actions actions){
        syncReceiver=new SyncReceiver("test",this);
        syncReceiver.register();
        this.actions=actions;
        SyncService.startService();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String error=intent.getStringExtra("error");

        if(error!=null){
            actions.onError();
            return;
        }

        this.itemList= (ArrayList<Item>) intent.getSerializableExtra("itemList");
        actions.onFinish(itemList);
    }

    public void onPause(){
        if(syncReceiver!=null){
            syncReceiver.unregister();;
        }
    }

    public boolean isLoaded(){
        return this.itemList!=null;
    }

    public ArrayList<Item> getItemList(){return this.itemList;}
}
