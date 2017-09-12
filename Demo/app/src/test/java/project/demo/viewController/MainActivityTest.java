package project.demo.viewController;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;

import project.demo.R;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by hassan on 9/12/2017.
 */
public class MainActivityTest {


    MainActivity activity;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        assertNotNull(activity);
    }


    /**
     * verify that refreshLayout is visible (refreshing) and recycleView is gone when showProgress is called
     * @throws Exception
     */
    @Test
    public void testShowProgress() throws Exception {
        activity.showProgress();

        SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) activity.findViewById(R.id.refreshLayout);
        RecyclerView recyclerView=(RecyclerView) activity.findViewById(R.id.recycleView);

        assertTrue(refreshLayout.isRefreshing());
        assertTrue(recyclerView.getVisibility()== View.GONE);

    }


    /**
     * verify that refreshLayout  refreshing is false and recycleView is visible when hideProgress is called
     * @throws Exception
     */
    @Test
    public void testHideProgress(){
        activity.hideProgress();
        SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) activity.findViewById(R.id.refreshLayout);
        RecyclerView recyclerView=(RecyclerView) activity.findViewById(R.id.recycleView);

        assertTrue(!refreshLayout.isRefreshing());
        assertTrue(recyclerView.getVisibility()==View.VISIBLE);

    }


    /**
     * verify display error message in toastMsg
     */
    @Test
    public void verifyErrorMessage(){
        activity.displayMsgError();
        ShadowLooper.idleMainLooper();

        assertEquals(ShadowToast.getTextOfLatestToast(),activity.getString(R.string.error));

    }


}