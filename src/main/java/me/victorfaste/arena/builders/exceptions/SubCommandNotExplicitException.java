package me.victorfaste.arena.builders.exceptions;

public class SubCommandNotExplicitException extends Exception {

    public SubCommandNotExplicitException() {
        super("No explicit subcommand.");
    }

}