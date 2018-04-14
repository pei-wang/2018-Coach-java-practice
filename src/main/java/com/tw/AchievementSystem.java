package com.tw;

import java.util.*;

class AchievementSystem {

    private Scanner scanner = new Scanner(System.in);
    private boolean status = true;

    void start() {
        Map<String, Command> routes = initRoutes();
        MenuCommand menuCommand = new MenuCommand();
        while (status) {
            menuCommand.invoke();
            String input = scanner.nextLine();
            routes.get(input).invoke();
        }
    }

    private Map<String, Command> initRoutes() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("1", new AddStudentCommand());
        commands.put("2", new MenuCommand());
        commands.put("3", new ExitCommand(this));
        return commands;
    }

    void exit() {
        this.status = false;
    }
}
