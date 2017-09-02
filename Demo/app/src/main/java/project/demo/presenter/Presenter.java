package project.demo.presenter;

/**
 * Created by hassan on 8/31/2017.
 */


public interface Presenter {
    public void onPause();
    public void onResume();
    public void onDestroy();
    public void onItemClicked();
}
