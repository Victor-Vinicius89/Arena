package me.victorfaste.arena.builders;

import me.victorfaste.arena.builders.exceptions.*;
import me.victorfaste.arena.builders.objects.Sender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class Command implements CommandExecutor {

    protected final JavaPlugin arena;
    protected final String commandName;
    protected final Set<SubCommand> subCommandSet;

    public Command(JavaPlugin arena, String commandName, SubCommand... subCommands) {
        this.arena = arena;
        this.commandName = commandName;
        this.subCommandSet = new HashSet<SubCommand>(Arrays.asList(subCommands));
    }


    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] args) {

        if (!command.getName().equalsIgnoreCase(commandName)) return true;

        Sender sender = new Sender(commandSender);

        try {

            execute(commandSender, args);

        } catch (SubCommandNotExplicitException e) {
            noArgs(sender);
        } catch (SubCommandNotFoundException e) {
            subCommandNotFound(sender, e.getArgument());
        } catch (PlayerExclusiveSubCommandException e) {
            playerExclusiveCommand(sender);
        } catch (PlayerNotFoundException e) {
            playerNotFound(sender, e.getPlayer());
        } catch (SubCommandCorrectUseException e) {
            correctUse(sender, e.getCorrectUse());
        } catch (NoPermissionException e) {
            noPermission(sender);
        }

        return true;
    }


    private void execute(CommandSender commandSender, String[] args) throws SubCommandNotExplicitException, SubCommandNotFoundException, PlayerExclusiveSubCommandException, PlayerNotFoundException, SubCommandCorrectUseException, NoPermissionException {

        if (needsBasePermission() && !commandSender.hasPermission(basePermission())) throw new NoPermissionException();

        if (args.length < 1) throw new SubCommandNotExplicitException();

        Optional<SubCommand> subCommandOptional = subCommandSet.stream().filter(subCommand ->
                subCommand.checkAliases(args[0])
        ).findFirst();

        if (!subCommandOptional.isPresent()) throw new SubCommandNotFoundException(args[0]);

        SubCommand subCommand = subCommandOptional.get();

        if (subCommand.isPlayerExclusive() && !(commandSender instanceof Player))
            throw new PlayerExclusiveSubCommandException();

        subCommand.execute(commandSender, args);

    }

    public abstract void noArgs(Sender sender);

    public abstract void subCommandNotFound(Sender sender, String subCommand);

    public abstract void playerExclusiveCommand(Sender sender);

    public abstract void playerNotFound(Sender sender, String player);

    public abstract void correctUse(Sender sender, String correctUse);

    public abstract void noPermission(Sender sender);

    public abstract String basePermission();

    public abstract boolean needsBasePermission();

    public void register() {
        arena.getCommand(this.commandName).setExecutor(this);
    }

}