package project.demo.mvp;

import java.util.ArrayList;

import project.demo.data.DataManager;
import project.demo.data.Item;

/**
 * Created by hassan on 8/31/2017.
 */

public class MainPresenter implements Presenter,DataManager.Actions {
    MainView mainView;
    DataManager model;

    public  MainPresenter(MainView mainView,DataManager model){
        this.mainView=mainView;
        this.model=model;
    }

    @Override
    public void onPause() {model.onPause();}

    @Override
    public void onResume() {
        if(model.isLoaded()){
            onFinish(model.getItemList(),null);
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
    public void onItemClicked() {}

    @Override
    public void onFinish(ArrayList<Item> itemList, String error) {
        //if no error
        if(error==null){
            mainView.hideProgress();
            mainView.setItems(itemList);
        }else{
            mainView.displayMsgError("something went wrong");
        }
    }
}
