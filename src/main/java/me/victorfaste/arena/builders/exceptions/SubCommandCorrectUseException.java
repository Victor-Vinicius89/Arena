package me.victorfaste.arena.builders.exceptions;

public class SubCommandCorrectUseException extends Exception {

    private final String correctUse;

    public SubCommandCorrectUseException(String correctUse) {
        super("No explicit subcommand arguments.");
        this.correctUse = correctUse;
    }


    public String getCorrectUse() {
        return correctUse;
    }
}