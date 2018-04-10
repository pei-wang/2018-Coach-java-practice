package com.tw;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class AchievementSystem {

    public static void main(String[] args) {
        new AchievementSystem().start();
    }Map<String, Course> courseMap;

    public void start() {
        courseMap = Arrays.stream(Course.values()).collect(toMap(Course::getDesc, e -> e));
        Scanner scanner = new Scanner(System.in);
        while (status) {
            menuInfo();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                    for (; ; ) {
                        String achievementsInfo = scanner.nextLine();
                        try {
                            inputAchievement(achievementsInfo);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                        }
                    }
                    break;
                case "2":
                    System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    for (; ; ) {
                        String studentIds = scanner.nextLine();
                        try {
                            generateReport(studentIds);
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                        }
                    }
                    break;
                case "3":
                    status = false;
                    break;
            }
        }
    }

    private void generateReport(String studentIds) {
        List<String> studentIdList = Arrays.asList(studentIds.split(","));
        Map<String, List<Achievement>> studentAndAchievementMap = achievements.stream()
                .filter(e -> studentIdList.contains(e.getStudent().getStudentId()))
                .sorted((x, y) -> Integer.compare(y.getCourse().ordinal(), x.getCourse().ordinal()))
                .collect(groupingBy(e -> e.getStudent().getStudentId()));
        System.out.println("姓名|数学|语文|英语|编程|平均分|总分");
        System.out.println("================================");
        studentAndAchievementMap.forEach((key, value) -> {
            String name = value.get(0).getStudent().getName();
            StringBuffer result = new StringBuffer();
            result.append(name).append("|");
            value.forEach((e) -> {
                result.append(e.getScore()).append("|");
            });
        });
    }

    private void menuInfo() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }

    private boolean status = true;
    private List<Achievement> achievements = new ArrayList<>();

    public boolean inputAchievement(String achievementInfo) throws IllegalArgumentException {
        //姓名，学号，学科：成绩
        Achievement achievement = formatAchievement(achievementInfo);
        achievements.add(achievement);
        return true;
    }


    private Achievement formatAchievement(String achievementInfo) throws IllegalArgumentException {
        validateInput(achievementInfo);
        String[] achievementInfoSplited = achievementInfo.split(",");
        String[] courseDetail = achievementInfoSplited[2].split(":");

        return new Achievement(new Student(achievementInfoSplited[0], achievementInfoSplited[1]), courseMap.get(courseDetail[0].trim()), Double.valueOf(courseDetail[1]));
    }

    private void validateInput(String achievementInfo) throws IllegalArgumentException {
        String[] achievementInfoSplited = achievementInfo.split(",");
        if (achievementInfoSplited.length == 3) {
            String[] courseDetail = achievementInfoSplited[2].split(":");
            if (courseDetail.length != 2) {
                throw new IllegalArgumentException("The input info is not legal");
            }
        } else {
            throw new IllegalArgumentException("The input info is not legal");
        }
    }

    public List<Achievement> getAchievementList() {
        return achievements;
    }
}
