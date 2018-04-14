package com.tw;

import java.util.Map;

public class ExitCommand implements Command {
    private AchievementSystem achievementSystem;

    public ExitCommand(AchievementSystem achievementSystem) {
        this.achievementSystem = achievementSystem;
    }

    @Override
    public void invoke() {
        achievementSystem.exit();
    }
}
