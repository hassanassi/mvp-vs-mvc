package project.demo.model;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import project.demo.model.service.SyncReceiver;
import project.demo.model.service.SyncService;


/**
 * Created by hassan on 8/31/2017.
 */

public class DataManager implements SyncReceiver.onReceive {

    public interface Actions{
        void onFinish(List<Item> itemList);
        void onError();
    }

    SyncReceiver syncReceiver;
    Actions actions;

    ArrayList<Item> itemList;


    public DataManager(Actions actions){
        this.actions=actions;
    }

    public void loadData(){
        this.itemList=null;
        syncReceiver=new SyncReceiver("test",this);
        syncReceiver.register();
        SyncService.startService();
    }

    @Override
    public void onReceive(ArrayList<Item> itemList) {
        if(itemList==null){
            actions.onError();
            return;
        }

        this.itemList= itemList;
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
