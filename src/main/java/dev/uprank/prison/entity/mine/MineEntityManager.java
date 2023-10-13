package dev.uprank.prison.entity.mine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import dev.uprank.prison.Prison;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.BoundingBox;

import java.util.*;
import java.util.function.Consumer;

public class MineEntityManager {

    private final Prison plugin;

    private final Map<String, MineEntity> mines;

    private final Gson gson;
    private final MongoCollection<Document> collection;

    public MineEntityManager(Prison plugin) {
        this.plugin = plugin;
        this.mines = new HashMap<>();

        this.gson = new GsonBuilder().create();
        this.collection = Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("mines");
        this.collection.find().forEach((Consumer<Document>) document -> {
            MineEntity mineEntity = this.gson.fromJson(this.gson.toJson(document), MineEntity.class);
            this.mines.put(mineEntity.getName(), mineEntity);
        });
    }

    public TreeMap<String, MineEntity> getSortedMines() {
        return new TreeMap<>(this.mines);
    }

    public MineEntity getMine(String name) {
        for (MineEntity mine : this.mines.values()) {
            if (mine.getName().equals(name)) {
                return mine;
            }
        }
        return null;
    }

    public MineEntity getMine(Player player) {
        return this.getMine(player.getLocation());
    }

    public MineEntity getMine(Location location) {
        for (MineEntity mine : this.mines.values()) {
            if (mine.getCuboid().contains(location)) {
                return mine;
            }
        }
        return null;
    }

    public Map<String, MineEntity> getMines() {
        return this.mines;
    }

    public void registerMine(MineEntity mine) {
        this.collection.insertOne(this.gson.fromJson(this.gson.toJson(mine), Document.class));
        this.mines.put(mine.getName(), mine);
    }

    public void unregisterMine(String name) {
        this.mines.remove(name);
    }

}
