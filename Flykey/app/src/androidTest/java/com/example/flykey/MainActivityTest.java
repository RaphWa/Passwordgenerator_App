package com.example.flykey;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import control.MyControl;

/**
 * This class contains a few tests which test the class MainActivity with Espresso.
 * @author Raphael Walger
 */
public class MainActivityTest {

    int maxPasswordLength;
    int minPasswordLength;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Before
    public void setTheMaximumAndMinimumLengthForPassword(){
        maxPasswordLength = MyControl.MAXIMUM_LENGTH_OF_PASSWORD;
        minPasswordLength = MyControl.MINIMUM_LENGTH_OF_PASSWORD;
    }

    @Test
    public void areTheThreeButtonsCreated_Test(){
        onView(withId(R.id.button1)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void isFrameCreated_Test(){
        onView(withId(R.id.frame1)).check(matches(isDisplayed()));
    }

    @Test
    public void isZeroFragmentAtBeginningVisible_Test(){
        onView(withId(R.id.textViewF0_1)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewF0_2)).check(matches(isDisplayed()));
    }

    @Test
    public void doesTheFirstButtonChangesTheFrameCorrectly_Test(){
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.textViewF1_1)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewF1_2)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewF1_3)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewF1_4)).check(matches(isDisplayed()));
    }

    @Test
    public void doesTheSecondButtonChangesTheFrameCorrectly_Test(){
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.buttonF2_1)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonF2_2)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewF2_1)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextF2_1)).check(matches(isDisplayed()));
    }

    @Test
    public void doesTheThirdButtonChangesTheFrameCorrectly_Test(){
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.recyclerViewF3_1)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToGeneratePassword_Normal_Test(){
        onView(withId(R.id.button2)).perform(click());
        onView(withText(R.string.generatedPasswordTextDefault)).check(matches(isDisplayed()));

        onView(withId(R.id.editTextF2_1)).perform(clearText());
        int validInput = 0;
        if(minPasswordLength==maxPasswordLength){
            validInput = minPasswordLength;
        }else{
            validInput = minPasswordLength+1;
        }
        onView(withId(R.id.editTextF2_1)).perform(typeText(Integer.toString(validInput)));

        onView(withId(R.id.buttonF2_1)).perform(click());
        onView(withText(R.string.generatedPasswordTextStartGeneratingByShakingPhone)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToGeneratePassword_NumberTooHigh_Test(){
        onView(withId(R.id.button2)).perform(click());

        String stringOfMaxPasswordLength = Integer.toString(maxPasswordLength+1);
        onView(withId(R.id.editTextF2_1)).perform(clearText());
        onView(withId(R.id.editTextF2_1)).perform(typeText(stringOfMaxPasswordLength));

        onView(withId(R.id.buttonF2_1)).perform(click());
        onView(withText(R.string.generatedPasswordTextWrongInputWrongNumberTooHigh)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToGeneratePassword_NumberTooLow_Test(){
        onView(withId(R.id.button2)).perform(click());

        String stringOfMinPasswordLength = Integer.toString(minPasswordLength-1);
        onView(withId(R.id.editTextF2_1)).perform(clearText());
        onView(withId(R.id.editTextF2_1)).perform(typeText(stringOfMinPasswordLength));

        onView(withId(R.id.buttonF2_1)).perform(click());
        onView(withText(R.string.generatedPasswordTextWrongInputWrongNumberTooLow)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToGeneratePassword_Empty_Test(){
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.editTextF2_1)).perform(clearText());
        onView(withId(R.id.editTextF2_1)).perform(typeText(""));

        onView(withId(R.id.buttonF2_1)).perform(click());
        onView(withText(R.string.generatedPasswordTextWrongInputNotANumber)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToGeneratePassword_Letters_Test(){
        onView(withId(R.id.button2)).perform(click());

        onView(withId(R.id.editTextF2_1)).perform(clearText());
        onView(withId(R.id.editTextF2_1)).perform(typeText("abc"));

        onView(withId(R.id.buttonF2_1)).perform(click());
        onView(withText(R.string.generatedPasswordTextWrongInputNotANumber)).check(matches(isDisplayed()));
    }

    @Test
    public void tryToCopyText_Test(){
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.buttonF2_2)).perform(click());

        //onView(withText(R.string.textPasswordSuccessfullyCopied)).check(matches(isDisplayed()));
        onView(withText(R.string.textPasswordSuccessfullyCopied)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}