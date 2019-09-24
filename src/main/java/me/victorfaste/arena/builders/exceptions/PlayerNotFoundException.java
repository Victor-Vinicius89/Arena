package me.victorfaste.arena.builders.exceptions;

public class PlayerNotFoundException extends Exception {

    private final String player;

    public PlayerNotFoundException(String player) {
        super("Player not found.");
        this.player = player;
    }

    public String getPlayer() {
        return this.player;
    }

}
