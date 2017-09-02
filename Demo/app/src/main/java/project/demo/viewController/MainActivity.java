package project.demo.viewController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import project.demo.R;
import project.demo.model.DataManager;
import project.demo.model.Item;


/**
 * the mainActivity acts as the controller in mvc pattern
 */
public class MainActivity extends AppCompatActivity implements DataManager.Actions {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    Adapter adapter;


    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize recycleview
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        //initialize model
        this.dataManager=new DataManager();

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.dataManager.loadData(this);
        showProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.dataManager.onPause();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }


    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    public void setItems(ArrayList<Item> list) {
        adapter=new Adapter(list,null);
        recyclerView.setAdapter(adapter);
    }


    public void displayMsgError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onFinish(ArrayList<Item> itemList, String error) {
        //if no error
        if(error==null){
            hideProgress();
            setItems(itemList);
        }else{
            displayMsgError("something went wrong");
        }
    }
}
