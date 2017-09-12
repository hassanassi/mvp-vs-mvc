package project.demo.model;

import android.provider.ContactsContract;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import project.demo.model.service.SyncReceiver;



import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hassan on 9/11/2017.
 */
public class DataManagerTest {

   // @Captor
   // private ArgumentCaptor<SyncReceiver.onReceive> onReceive;

    @Mock
    SyncReceiver.onReceive onReceive;


    @Mock
    private DataManager.Actions actions;

    DataManager dataManager;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dataManager=new DataManager(actions);
    }

    @Test
    public void test_load(){

        dataManager.onReceive(null);

        //verify calling error method
        verify(actions).onError();

        //verify data not loaded
        Assert.assertFalse(dataManager.isLoaded());

        dataManager.onReceive(new ArrayList<Item>());

        //verify calling onFinish
        verify(actions).onFinish(anyListOf(Item.class));

        //verify data loaded
        Assert.assertTrue(dataManager.isLoaded());

    }
}