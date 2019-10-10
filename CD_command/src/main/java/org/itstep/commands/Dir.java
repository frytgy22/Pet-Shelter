package org.itstep.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.Command;
import org.itstep.CommandsExecute;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dir implements CommandsExecute {
    private Command command;

    @Override
    public String execute() {
        return command.dir();
    }
}
