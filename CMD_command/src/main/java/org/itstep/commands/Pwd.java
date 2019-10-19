package org.itstep.commands;

import lombok.Data;
import org.itstep.CommandsExecute;
import org.itstep.Context;

@Data
public class Pwd implements CommandsExecute {
    Context context;

    public Pwd(Context context) {
        this.context = context;
    }

    /**
     * Метод, чтоб вывести полный путь до текущей директории
     */

    @Override
    public String execute(String... args) {
        return context.getFile().getAbsolutePath();
    }
}
