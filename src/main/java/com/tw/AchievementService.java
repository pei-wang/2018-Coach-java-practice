package com.tw;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AchievementService {
    private List<Achievement> achievements;

    AchievementService() {
        this.achievements = new ArrayList<>();
    }

    void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    List<Achievement> getAchievements() {
        return achievements;
    }


    List<Achievement> getAchievementsByStudentId(String studentId) {
        return achievements.stream().filter(e -> e.getStudent().getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }
}