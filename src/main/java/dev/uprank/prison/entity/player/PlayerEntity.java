package dev.uprank.prison.entity.player;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerEntity {

    private final UUID uniqueId;
    private String name;

    private Integer balance;
    private Integer gems;
    private Integer minedBlocks;

    private String mine;
    private Integer prestige;
    private Integer rebirth;

    private List<String> allowedMines;


    public PlayerEntity(UUID uniqueId, String name, Integer balance, Integer gems, Integer minedBlocks, String mine, Integer prestige, Integer rebirth, List<String> allowedMines) {
        this.uniqueId = uniqueId;
        this.name = name;

        this.balance = balance;
        this.gems = gems;
        this.minedBlocks = minedBlocks;

        this.mine = mine;

        this.prestige = prestige;
        this.rebirth = rebirth;

        this.allowedMines = allowedMines;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public void setDisplayName(String name) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("name", name));
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("balance", balance));
        this.balance = balance;
    }

    public void addBalance(Integer balance) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("balance", this.balance += balance));
        this.balance += balance;
    }

    public void removeBalance(Integer balance) {

        this.balance -= balance;
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("balance", this.balance));
        System.out.println("REMOVEBALANCE: 2: " + (this.balance));
    }

    public Integer getGems() {
        return gems;
    }

    public void setGems(Integer gems) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("gems", gems));
        this.gems = gems;
    }

    public void addGems(Integer gems) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("gems", this.gems + gems));
        this.gems += gems;
    }

    public void removeGems(Integer gems) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("gems", this.gems - gems));
        this.gems -= gems;
    }

    public Integer getMinedBlocks() {
        return minedBlocks;
    }

    public void addMinedBlock(Integer minedBlocks) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("minedBlocks", this.minedBlocks + minedBlocks));
        this.minedBlocks += minedBlocks;
    }

    public String getMine() {
        return mine;
    }

    public void setMine(String mine) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("mine", mine));
        this.mine = mine;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("prestige", prestige));
        this.prestige = prestige;
    }

    public Integer getRebirth() {
        return rebirth;
    }

    public void setRebirth(Integer rebirth) {
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("rebirth", rebirth));
        this.rebirth = rebirth;
    }

    public boolean isPrestige() {
        return prestige != 0;
    }

    public boolean isRebirth() {
        return rebirth != 0;
    }

    public List<String> getAllowedMines() {
        return allowedMines;
    }

    public Map<String, UUID> getMines() {

        Map<String, UUID> mines = new HashMap<>();

        for (String mine : this.allowedMines) {
            mines.put(mine, this.uniqueId);
        }
        return mines;
    }

    public void addAllowedMine(String mine) {
        if (this.allowedMines.contains(mine)) return;
        this.allowedMines.add(mine);
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("allowedMines", allowedMines));
    }

    public void removeAllowedMine(String mine) {
        if (!(this.allowedMines.contains(mine))) return;
        this.allowedMines.remove(mine);
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players").updateOne(
                Filters.eq("uniqueId", uniqueId.toString()), Updates.set("allowedMines", allowedMines));
    }

    public org.bukkit.entity.Player toPlayer() {
        return Bukkit.getPlayer(this.uniqueId);
    }

}
