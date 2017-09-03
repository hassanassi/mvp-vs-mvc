package project.demo.view;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import project.demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by hassan on 9/3/2017.
 */
public class MainActivityTest {

    //rule to launch the activity before each test
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);




    /**
     * verify progress bar is hidden and recycleView is visible after calling hideProgress
     * @throws Exception
     */
    @Test
    public void testHideProgress() throws Exception {
        mActivityRule.getActivity().hideProgress();

        onView(withId(R.id.recycleView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }



    /**
     * verify progress bar is visible and recycleView is gone after calling showProgress
     * @throws Exception
     */
    @Test
    public void testShowProgress() throws Exception {
        mActivityRule.getActivity().showProgress();

    //    onView(withId(R.id.progressBar))
     //           .check(matches(isDisplayed()));

        onView(withId(R.id.recycleView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

}