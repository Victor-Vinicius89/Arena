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

public class DeleteArena extends SubCommand {

    private ArenaOO arenaOO;
    private Arena arena;

    public DeleteArena(Arena arena) {
        super(arena, "delentrada");
    }

    @Override
    public boolean isPlayerExclusive() {
        return true;
    }

    @Override
    public String correctUse() {
        return "/arena delentrada";
    }

    @Override
    public String description() {
        return "Use para deletar a entrada da arena";
    }

    @Override
    public void onSubCommand(Sender sender, Arguments arguments) throws PlayerNotFoundException, SubCommandCorrectUseException {

        Player player = (Player) sender.getCommandSender();

        if (player.hasPermission("arena.delete")) {
            player.sendMessage(ChatColor.RED + "Não tem permissão para deletar a entrada da arena.");
            return;
        }

        if (arena.getConfig().getConfigurationSection("Warps." + "arena") == null) {
            player.sendMessage(ChatColor.RED + "Entrada não foi setada.");
            return;
        }
        arenaOO.delEntrada(player);
    }
}
