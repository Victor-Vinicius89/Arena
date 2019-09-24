package me.victorfaste.arena.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArenaKits {


    public void ArenaInventory(Player player) {

        PlayerInventory playerInventory = player.getInventory();
        for (ItemStack itemStack : playerInventory.getContents()) {
            if (itemStack != null && !(itemStack.getType() == Material.AIR)) {
                player.sendMessage("§cInventario cheio, para entrar limpe o inventario.");
                return;
            }
        }

    }

    public void ArenaSetKits(Player player) {

    }
}
