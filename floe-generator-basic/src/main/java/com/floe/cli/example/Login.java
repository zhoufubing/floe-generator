package com.floe.cli.example;
import org.apache.commons.collections4.SetValuedMap;
import picocli.CommandLine;
import picocli.*;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    @CommandLine.Option(names = {"-u","--user"},description = "User name")
    String user;

    @CommandLine.Option(names = {"-p","--password"},description = "Passphrase",interactive = true)
    String password;
@CommandLine.Option(names = {"-cp","--checkPassword"},description = "checkPassword",interactive = true)
    String checkPassword;

    public Integer call()throws Exception{
        System.out.println("password = " +password);
        System.out.println("checkPassword = " +checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        args = new String[]{"-u","user123","-p","-cp"};
        new CommandLine(new Login()).execute(args);
    }
}
