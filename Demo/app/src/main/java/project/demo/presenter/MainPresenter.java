package project.demo.presenter;

import android.provider.ContactsContract;

import java.util.ArrayList;

import project.demo.model.DataManager;
import project.demo.model.Item;
import project.demo.view.MainView;

/**
 * Created by hassan on 8/31/2017.
 */

public class MainPresenter implements Presenter,DataManager.Actions {
    MainView mainView;
    DataManager model;

    public  MainPresenter(MainView mainView){
        this.mainView=mainView;
        setModel(new DataManager());
    }


    public void setModel(DataManager dataManager){
        this.model=dataManager;
    }

    @Override
    public void onPause() {model.onPause();}

    @Override
    public void onResume() {
        if(model.isLoaded()){
            onFinish(model.getItemList());
        }else{
            mainView.showProgress();
            model.loadData(this);
        }
    }

    @Override
    public void onDestroy() {
        mainView=null;
    }


    @Override
    public void onFinish(ArrayList<Item> itemList) {
        //if no error
        mainView.hideProgress();
        mainView.setItems(itemList);
    }

    @Override
    public void onError() {
        mainView.displayMsgError("something went wrong");
    }
}
