package org.itstep;

import java.util.List;

public class CommandJobs extends Command {
    @Override
    public void jobs(List<String> list) {
        list.forEach(System.out::println);
    }
}
