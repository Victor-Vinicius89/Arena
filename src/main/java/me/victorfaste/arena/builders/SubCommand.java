package me.victorfaste.arena.builders;

import me.victorfaste.arena.builders.exceptions.PlayerNotFoundException;
import me.victorfaste.arena.builders.exceptions.SubCommandCorrectUseException;
import me.victorfaste.arena.builders.objects.Arguments;
import me.victorfaste.arena.builders.objects.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public abstract class SubCommand {

    private JavaPlugin main;
    private List<String> aliases;

    public SubCommand(JavaPlugin main, String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public boolean execute(CommandSender commandSender, String[] args) throws SubCommandCorrectUseException, PlayerNotFoundException {

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        Sender sender = new Sender(commandSender);
        Arguments arguments = new Arguments(newArgs, correctUse());

        onSubCommand(sender, arguments);

        return true;

    }

    public boolean checkAliases(String s) {
        return aliases.contains(s);
    }

    public abstract boolean isPlayerExclusive();

    public abstract String correctUse();

    public abstract String description();

    public abstract void onSubCommand(Sender sender, Arguments arguments) throws PlayerNotFoundException, SubCommandCorrectUseException;

}