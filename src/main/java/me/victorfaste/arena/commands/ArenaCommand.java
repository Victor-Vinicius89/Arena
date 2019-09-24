package me.victorfaste.arena.commands;

import me.victorfaste.arena.Arena;
import me.victorfaste.arena.builders.Command;
import me.victorfaste.arena.builders.objects.Sender;
import me.victorfaste.arena.commands.subcommand.CreateArena;
import me.victorfaste.arena.commands.subcommand.DeleteArena;
import me.victorfaste.arena.commands.subcommand.EntryArena;
import org.bukkit.ChatColor;

public class ArenaCommand extends Command {


    public ArenaCommand(Arena arena) {
        super(arena, "arena", new EntryArena(arena), new CreateArena(arena), new DeleteArena(arena));
    }

    @Override
    public void noArgs(Sender sender) {
        sender.sendMessage(ChatColor.RED + "  \n               \n                Arena - Comandos:\n");
        this.subCommandSet.forEach(subCommand ->
                sender.sendMessage(ChatColor.RED + "\n         " + subCommand.correctUse() + ": " + ChatColor.GRAY + subCommand.description()));
    }

    @Override
    public void subCommandNotFound(Sender sender, String subCommand) {
        sender.sendMessage("&cO sub comando " + subCommand + " não foi encontrado.");
    }

    @Override
    public void playerExclusiveCommand(Sender sender) {
    }

    @Override
    public void playerNotFound(Sender sender, String player) {
        sender.sendMessage("&cO jogador " + player + " não foi encontrado.");

    }

    @Override
    public void correctUse(Sender sender, String correctUse) {
        sender.sendMessage("&cUtilize /" + this.commandName + " " + correctUse);
    }

    @Override
    public void noPermission(Sender sender) {
        sender.sendMessage("&cNo permission.");
    }

    @Override
    public String basePermission() {
        return null;
    }

    @Override
    public boolean needsBasePermission() {
        return false;
    }
}
