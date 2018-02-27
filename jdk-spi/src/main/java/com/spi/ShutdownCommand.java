package com.spi;

/**
 * Created by ville on 2018/2/27.
 */
public class ShutdownCommand implements Command {

    @Override
    public void execute() {
        System.out.println("shutdown....");
    }
}
