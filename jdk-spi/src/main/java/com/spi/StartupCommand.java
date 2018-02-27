package com.spi;

/**
 * Created by admin on 2018/2/27.
 */
public class StartupCommand implements Command {

    @Override
    public void execute() {
        System.out.println("start....");
    }
}
