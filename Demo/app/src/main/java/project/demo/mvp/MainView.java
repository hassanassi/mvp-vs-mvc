package project.demo.mvp;

import java.util.ArrayList;

import project.demo.data.Item;

/**
 * Created by hassan on 8/31/2017.
 */

public interface MainView {

    public void showProgress();
    public void hideProgress();
    public void loadData(ArrayList<Item> list);
    public void displayMsgError(String error);
}