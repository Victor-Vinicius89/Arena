package me.victorfaste.arena.builders.objects;

import me.victorfaste.arena.builders.exceptions.PlayerNotFoundException;
import me.victorfaste.arena.builders.exceptions.SubCommandCorrectUseException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Arguments {

    private String[] args;
    private String correctUse;

    public Arguments(String[] args, String correctUse) {
        this.args = args;
        this.correctUse = correctUse;
    }

    public Player nextPlayer() throws PlayerNotFoundException, SubCommandCorrectUseException {

        if (args.length < 1 || args[0] == null) throw new SubCommandCorrectUseException(correctUse);

        Player player = Bukkit.getPlayer(this.args[0]);
        if (player == null || !player.isOnline()) throw new PlayerNotFoundException(this.args[0]);

        String[] newArgs = Arrays.copyOfRange(this.args, 1, this.args.length);
        this.args = newArgs;

        return player;

    }

    public String nextString() throws SubCommandCorrectUseException {

        if (args.length < 1 || args[0] == null) throw new SubCommandCorrectUseException(correctUse);

        String string = args[0];

        String[] newArgs = Arrays.copyOfRange(this.args, 1, this.args.length);
        this.args = newArgs;

        return string;

    }

    public int nextInt() throws SubCommandCorrectUseException {

        if (args.length < 1 || args[0] == null) throw new SubCommandCorrectUseException(correctUse);

        int integer = 0;

        try {
            integer = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new SubCommandCorrectUseException(correctUse);
        }

        String[] newArgs = Arrays.copyOfRange(this.args, 1, this.args.length);
        this.args = newArgs;

        return integer;

    }

    public String nextStringList() throws SubCommandCorrectUseException {

        if (args.length < 1 || args[0] == null) throw new SubCommandCorrectUseException(correctUse);

        StringBuilder string = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            string.append(args[0]).append(" ");
        }

        String[] newArgs = Arrays.copyOfRange(this.args, 1, this.args.length);
        this.args = newArgs;

        return string.toString().trim();

    }


}