package org.itstep;

public interface CommandsExecute {
    String execute(String... args);

    // default String[] getArgs(String args) { return args.split("\\s+"); }
}
