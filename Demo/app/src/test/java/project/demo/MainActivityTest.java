package project.demo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import org.apache.tools.ant.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLooper;
import org.robolectric.shadows.ShadowToast;

import project.demo.presenter.MainPresenter;
import project.demo.view.MainActivity;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,packageName = "project.demo",sdk = 21)
public class MainActivityTest {


    MainActivity activity;


    @Mock
    MainPresenter mainPresenter;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        activity = Robolectric.buildActivity(MainActivity.class).create().resume().get();

        assertNotNull(activity);
        activity.setPresenter(mainPresenter);
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

        assertTrue(progressBar.getVisibility()==View.GONE);
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
