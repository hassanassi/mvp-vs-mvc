package project.demo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;

import project.demo.model.Item;
import project.demo.viewController.MainActivity;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hassan on 9/4/2017.
 */
//test view and controller together
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,packageName = "project.demo",sdk = 21)
public class MainActivityTest {


    MainActivity activity;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();
        assertNotNull(activity);
    }


    /**
     * verify that progress bar is visible and recycleView is gone when showProgress is called
     * @throws Exception
     */
    @Test
    public void testShowProgress() throws Exception {
        activity.showProgress();

        ProgressBar progressBar= (ProgressBar) activity.findViewById(R.id.progressBar);
        RecyclerView recyclerView=(RecyclerView) activity.findViewById(R.id.recycleView);

        assertTrue(progressBar.getVisibility()==View.VISIBLE);
        assertTrue(recyclerView.getVisibility()==View.GONE);

    }


    /**
     * verify that progress bar is gone and recycleView is visible when showProgress is called
     * @throws Exception
     */
    @Test
    public void testHideProgress(){
        activity.hideProgress();
        ProgressBar progressBar= (ProgressBar) activity.findViewById(R.id.progressBar);
        RecyclerView recyclerView=(RecyclerView) activity.findViewById(R.id.recycleView);

        assertTrue(progressBar.getVisibility()== View.GONE);
        assertTrue(recyclerView.getVisibility()==View.VISIBLE);

    }


    /**
     * verify display error message in toast
     */
    @Test
    public void verifyErrorMessage(){
        activity.displayMsgError();
        ShadowLooper.idleMainLooper();

        assertEquals(ShadowToast.getTextOfLatestToast(),activity.getString(R.string.error));

    }



}
