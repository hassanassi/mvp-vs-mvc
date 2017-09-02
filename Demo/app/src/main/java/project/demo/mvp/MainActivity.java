package project.demo.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import project.demo.R;
import project.demo.data.DataManager;
import project.demo.data.Item;


public class MainActivity extends AppCompatActivity implements MainView {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    Adapter adapter;

    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize recycleview
        recyclerView= (RecyclerView) findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


        //initialize presenter
        presenter=new MainPresenter(this,new DataManager());

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadData(ArrayList<Item> list) {
        adapter=new Adapter(list,null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayMsgError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
    }


}
