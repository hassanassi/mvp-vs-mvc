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

import project.demo.view.MainActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by hassan on 9/3/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,manifest = "src/main/AndroidManifest.xml",packageName = "project.demo")
public class MainViewTest{


    MainActivity mainActivity;

    @Before
    public void setUp(){
        mainActivity = Robolectric.setupActivity(MainActivity.class);

    }

    @Test
    public void test234(){
        MockitoAnnotations.initMocks(this);


        //verfiy that recycelview is visible and progress bar is gone when hiding progress
        RecyclerView recyclerView= (RecyclerView) mainActivity.findViewById(R.id.recycleView);
        ProgressBar progressBar= (ProgressBar) mainActivity.findViewById(R.id.progressBar);

        assertEquals(View.INVISIBLE,progressBar.getVisibility());
        assertEquals(View.VISIBLE,recyclerView.getVisibility());
    }
}
