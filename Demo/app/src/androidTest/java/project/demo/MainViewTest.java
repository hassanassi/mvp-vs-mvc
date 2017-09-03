package project.demo;

import android.support.test.rule.ActivityTestRule;
import android.widget.ProgressBar;

import org.junit.Rule;
import org.junit.Test;

import project.demo.view.MainActivity;

/**
 * Created by hassan on 9/3/2017.
 */

public class MainViewTest {


    //rule to launch the activity before each test
    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    /**
     * verify progress bar is visible and recycleView is gone after showProgress is called
     */
    @Test
    public void testShowProgressBehaviour(){
        ProgressBar progressBar= (ProgressBar) mainActivityRule.getActivity().findViewById(R.id.progressBar);
    }
}
