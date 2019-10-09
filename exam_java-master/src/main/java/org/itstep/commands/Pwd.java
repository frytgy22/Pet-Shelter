package org.itstep.commands;


import java.io.File;

public class Pwd implements Command {
    @Override
    public String execute(String... arg) {
        if (arg.length > 0) {
            return new File(arg[0]).getAbsolutePath();
        }
        return "Ошибка ввода";
    }
}
