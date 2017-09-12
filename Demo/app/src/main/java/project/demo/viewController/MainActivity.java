package project.demo.viewController;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import project.demo.R;
import project.demo.model.DataManager;
import project.demo.model.Item;


/**
 * the mainActivity acts as the controller in mvc pattern
 */
public class MainActivity extends AppCompatActivity implements DataManager.Actions {



    //view in mvc
    Toast toastMsg;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    Adapter adapter;


    //model in mvc
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to MainActivity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Demo");
        setSupportActionBar(toolbar);

        //initialize recycleview
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        //initialize model
        this.dataManager = new DataManager(this);


        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //reload data on refresh
                loadData();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (dataManager.isLoaded()) {
            onFinish(dataManager.getItemList());
        } else {
            loadData();
        }
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
        refreshLayout.setRefreshing(true);
        recyclerView.setVisibility(View.GONE);
    }


    public void hideProgress() {
        refreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }


    public void setItems(List<Item> list) {
        adapter = new Adapter(list, null);
        recyclerView.setAdapter(adapter);
    }



    public void displayMsgError() {
        refreshLayout.setRefreshing(false);
        showToast();
    }


    @Override
    public void onFinish(List<Item> itemList) {
        hideProgress();
        setItems(itemList);
    }


    @Override
    public void onError() {
        displayMsgError();
    }


    private void loadData(){
        showProgress();
        dataManager.loadData();
    }

    private void showToast(){
        if(toastMsg !=null)
            toastMsg.cancel();

        toastMsg =Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT);
        toastMsg.show();
    }
}
