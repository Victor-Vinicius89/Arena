package me.victorfaste.arena.commands.subcommand;

import me.victorfaste.arena.Arena;
import me.victorfaste.arena.builders.SubCommand;
import me.victorfaste.arena.builders.exceptions.PlayerNotFoundException;
import me.victorfaste.arena.builders.exceptions.SubCommandCorrectUseException;
import me.victorfaste.arena.builders.objects.Arguments;
import me.victorfaste.arena.builders.objects.Sender;
import me.victorfaste.arena.utils.ArenaOO;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateArena extends SubCommand {

    private ArenaOO arenaOO;
    private Arena arena;

    public CreateArena(Arena arena) {
        super(arena, "setentrada");
        this.arena = arena;
    }


    @Override
    public boolean isPlayerExclusive() {
        return true;
    }

    @Override
    public String correctUse() {
        return "/arena setentrada";
    }

    @Override
    public String description() {
        return "Use esse comando para setar a entrada do jogador na arena.";
    }

    @Override
    public void onSubCommand(Sender sender, Arguments arguments) throws PlayerNotFoundException, SubCommandCorrectUseException {

        Player player = (Player) sender.getCommandSender();
        if (player.hasPermission("arena.create")) {
            player.sendMessage(ChatColor.RED + "Não tem permissão para criar a entrada da arena.");
            return;
        }
        if (arena.getConfig().getConfigurationSection("Warps." + "arena") != null) {
            player.sendMessage(ChatColor.AQUA + "Essa arena já existe.");
            return;
        }
        arenaOO.createWarp(player, sender.getLocation());
    }
}
