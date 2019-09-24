package me.victorfaste.arena.builders.exceptions;

public class SubCommandNotFoundException extends Exception {

    private final String arg;

    public SubCommandNotFoundException(String arg) {
        super("SubCommand not found.");
        this.arg = arg;
    }

    public String getArgument() {
        return this.arg;
    }

}