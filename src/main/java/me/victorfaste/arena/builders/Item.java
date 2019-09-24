package me.victorfaste.arena.builders;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Item {

    private ItemStack itemStack;
    private Boolean closeable = false;
    private Boolean editable = true;
    private Boolean cancellable = false;
    private Consumer<InventoryClickEvent> clickEventConsumer;

    public Item(){}

    public Item(Material material){
        this.itemStack = new ItemStack(material);
    }

    public Item(ItemStack item){
        this.itemStack = item;
    }

    public Item setType(Material material){
        if (itemStack == null) itemStack = new ItemStack(material); else itemStack.setType(material);
        return this;
    }

    public Boolean isCloseable(){
        return closeable;
    }

    public Boolean isEditable(){
        return editable;
    }

    public Item setCloseable(Boolean closeable) {
        this.closeable = closeable;
        return this;
    }

    public Item setCancel(Boolean b){
        this.cancellable = b;
        return this;
    }

    public Boolean isCancellable(){
        return cancellable;
    }

    public Item setEditable(Boolean editable) {
        this.editable = editable;
        return this;
    }

    public Item onClick(Consumer<InventoryClickEvent> consumer){
        this.clickEventConsumer = consumer;
        return this;
    }

    public Consumer<InventoryClickEvent> getClickEventConsumer() {
        return clickEventConsumer;
    }


    public Item durability(Integer durability){
        itemStack.setDurability(Short.valueOf(durability.toString()));
        return this;
    }

    public Item name(String name){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item lore(String... line){
        ItemMeta meta = itemStack.getItemMeta();
        List<String> lore = Arrays.asList(line);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item lore(List<String> lore){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item amount(Integer amount){
        itemStack.setAmount(amount);
        return this;
    }

    public Item enchant(Enchantment enchantment, Integer level){
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public Item hideAttributes(){
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item color(Color color) {
        if (!itemStack.getType().name().contains("LEATHER_")) return this;
        LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
        meta.setColor(color);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item owner(String owner) {
        SkullMeta im = (SkullMeta) itemStack.getItemMeta();
        im.setOwner(owner);
        itemStack.setItemMeta(im);
        return this;
    }

    public ItemStack build(){
        return itemStack;
    }
}