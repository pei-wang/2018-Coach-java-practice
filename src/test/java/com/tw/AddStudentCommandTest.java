package com.tw;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
public class AddStudentCommandTest {

    @Mock
    AchievementService achievementService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockStatic(Console.class);
    }

    @Test
    public void should_addAddStudentSuccess_whenCommandInvokedAndInputlegal() {
        AddStudentCommand addStudentCommand = new AddStudentCommand();
        when(Console.getInput()).thenReturn("王培, 1, 数学:99");
        addStudentCommand.setAchievementService(achievementService);

        addStudentCommand.invoke();

        verify(achievementService, times(1)).addAchievement(any(Achievement.class));
    }


}