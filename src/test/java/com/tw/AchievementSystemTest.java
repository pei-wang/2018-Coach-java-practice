package com.tw;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AchievementSystemTest {

    @Test
    public void should_addStudentAchievementSuccessfully_when_Infolegal() throws Exception {
        AchievementSystem app = new AchievementSystem();
        app.inputAchievement("老王, 1, 数学: 80.0");

        assertThat(app.getAchievementList().get(0).getScore(), is(80.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throwInvalidException_when_inputInfoIllegal() throws Exception {
        AchievementSystem app = new AchievementSystem();
        app.inputAchievement("老王, 1, 数学, 80.0");
    }
}