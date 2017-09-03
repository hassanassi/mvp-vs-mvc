package project.demo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ProgressBar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.demo.view.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static org.junit.Assert.*;
/**
 * Created by hassan on 9/3/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainViewTest {


//    //rule to launch the activity before each test
//    @Rule
//    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(
//            MainActivity.class);
//

    /**
     * verify progress bar is visible and recycleView is gone after showProgress is called
     */
    @Test
    public void testShowProgressBehaviour(){
     //   ProgressBar progressBar= (ProgressBar) mainActivityRule.getActivity().findViewById(R.id.progressBar);
    }
}
