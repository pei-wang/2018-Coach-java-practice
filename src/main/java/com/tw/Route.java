package com.tw;

public class Route {
    private String path;
    private Command command;

    public Route(String path, Command command) {
        this.path = path;
        this.command = command;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
