package project.demo.view;

import android.app.Activity;
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
import project.demo.presenter.MainPresenter;
import project.demo.presenter.Presenter;


public class MainActivity extends Activity implements MainView {

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
        setPresenter(new MainPresenter(this,new DataManager()));

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

    }

    public void setPresenter(Presenter presenter){
        this.presenter=presenter;
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
    public void setItems(ArrayList<Item> list) {
        adapter=new Adapter(list,null);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayMsgError() {
        Toast.makeText(this,R.string.error,Toast.LENGTH_LONG).show();
    }


}
