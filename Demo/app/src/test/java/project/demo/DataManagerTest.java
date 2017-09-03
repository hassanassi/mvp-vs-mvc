package project.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import project.demo.model.DataManager;
import project.demo.model.Item;
import project.demo.presenter.MainPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hassan on 9/3/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {



    @Mock
    DataManager dataManager;

    @Mock
    ArrayList<Item> itemList;


    /**
     * verify that presenter is called when data is loaded
     */
    @Test
    public void test(){
        DataManager.Actions actions=mock(MainPresenter.class);
    //    when(dataManager.isLoaded()).thenReturn(true);
        dataManager.loadData(actions);

        verify(actions).onFinish(itemList);
    }
}
