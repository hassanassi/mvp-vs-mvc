package project.demo.view;

import java.util.ArrayList;

import project.demo.model.Item;

/**
 * Created by hassan on 8/31/2017.
 */

public interface MainView {

    public void showProgress();
    public void hideProgress();
    public void setItems(ArrayList<Item> list);
    public void displayMsgError(String error);
}