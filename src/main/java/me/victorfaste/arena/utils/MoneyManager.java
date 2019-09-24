package me.victorfaste.arena.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MoneyManager {

    private static MoneyManager INSTANCE;
    private Map<String, Double> playersMoney;

    private MoneyManager() {
        this.playersMoney = new HashMap<>();
    }

    public static MoneyManager getInstance() {
        if (MoneyManager.INSTANCE == null) {
            MoneyManager.INSTANCE = new MoneyManager();
        }

        return INSTANCE;
    }

    public boolean contains(String player) {
        if (this.playersMoney.containsKey(player)) {
            return true;
        }
        return false;
    }

    public void setBalance(String player, double balance) {
        this.playersMoney.put(player, balance);
    }

    public double getBalance(String player) {
        if (this.contains(player)) {
            double balance = this.playersMoney.get(player);

            return balance;
        } else {
            this.setBalance(player, 0);
        }

        return 0;
    }

    public boolean hasBalance(Player player, double balance) {
        return this.getBalance(player.getName()) >= balance;
    }

    public void deposit(Player player, double amount) {
        double balance = getBalance(player.getName());
        double depositAmount = balance + amount;

        this.setBalance(player.getName(), depositAmount);
    }

    public boolean withdraw(Player player, double amount) {
        double balance = getBalance(player.getName());
        if(balance >= amount){
            setBalance(player.getName(), balance - amount);
            return true;
        }
        return false;

    }
}
