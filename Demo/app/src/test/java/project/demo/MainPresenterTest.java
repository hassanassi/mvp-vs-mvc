package project.demo;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import project.demo.model.DataManager;
import project.demo.model.Item;
import project.demo.presenter.MainPresenter;
import project.demo.presenter.Presenter;
import project.demo.view.MainView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hassan on 9/3/2017.
 */

public class MainPresenterTest {

    /**
     * testing that the model and view receives the correct calls from the presenter
     */

    @Mock
    MainView mainView;

    @Mock
    DataManager model;


    MainPresenter presenter;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter=new MainPresenter(mainView,model);
    }


    /**
     * verify presenter hiding progress and calling setItem in view when data is loaded
     */
    @Test
    public void testonFinishCalled(){
        when(model.isLoaded()).thenReturn(true);
        presenter.onResume();


        verify(mainView).hideProgress();
        verify(mainView).setItems(model.getItemList());
    }

    /*
     * verify presenter calling displayMsgError when error occurred
     */
    @Test
    public void testDisplayErrorCalled(){
        presenter.onError();

        verify(mainView).displayMsgError();
    }


    /**
     * verify that the presenter calling loadData in model and showProgress in view
     */
    @Test
    public void testLoadDataCalled(){

        presenter.onResume();

        verify(mainView).showProgress();
        verify(model).loadData(presenter);
    }
}
