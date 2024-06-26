package com.samuel.spaceships.api.Domain.Bus.Command;


public abstract class CommandHandler<T extends Command> {
    public void execute(T command) {
        this.run(command);
        this.log(command);
    }

    abstract protected void log(T command);
    abstract protected void run(T command);
}