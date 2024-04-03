package com.floe.cli;

import com.floe.cli.command.ConfigCommand;
import com.floe.cli.command.GenerateCommand;
import com.floe.cli.command.ListCommand;
import picocli.CommandLine;
@CommandLine.Command(name = "floe",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {
    private final CommandLine commandLine;
    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }
    @Override
    public void run(){
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }
    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
