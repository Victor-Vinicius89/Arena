package me.victorfaste.arena.commands.subcommand;

import me.victorfaste.arena.Arena;
import me.victorfaste.arena.builders.SubCommand;
import me.victorfaste.arena.builders.exceptions.PlayerNotFoundException;
import me.victorfaste.arena.builders.exceptions.SubCommandCorrectUseException;
import me.victorfaste.arena.builders.objects.Arguments;
import me.victorfaste.arena.builders.objects.Sender;
import me.victorfaste.arena.utils.ArenaKits;
import me.victorfaste.arena.utils.ArenaOO;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EntryArena extends SubCommand {


    private ArenaKits arenaKits;
    private ArenaOO arenaOO;
    private Arena arena;

    public EntryArena(Arena arena) {
        super(arena, "entrar");
        this.arena = arena;
    }

    @Override
    public boolean isPlayerExclusive() {
        return true;
    }

    @Override
    public String correctUse() {
        return "/arena entrar";
    }

    @Override
    public String description() {
        return "Comando para entrar na arena.";
    }

    @Override
    public void onSubCommand(Sender sender, Arguments arguments) throws PlayerNotFoundException, SubCommandCorrectUseException {

        Player player = (Player) sender.getCommandSender();

        if (arena.getConfig().getConfigurationSection("Warps." + "arena") == null) {
            player.sendMessage(ChatColor.RED + "Essa arena não existe.");
            return;
        }
        arenaKits.ArenaInventory(player);

    }
}
