package dev.uprank.prison.entity.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.mine.MineEntity;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerEntityManager {

    private final Prison plugin;
    private final Map<UUID, PlayerEntity> players;

    private final Gson gson;
    private final MongoCollection<Document> collection;

    public PlayerEntityManager(Prison plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();

        this.gson = new GsonBuilder().create();
        this.collection = Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players");
        this.collection.find().forEach((Consumer<Document>) document -> {
            PlayerEntity playerEntity = this.gson.fromJson(this.gson.toJson(document), PlayerEntity.class);
            this.players.put(playerEntity.getUniqueId(), playerEntity);
        });
    }

    public void createPlayer(PlayerEntity player) {
        MongoCollection<Document> document = Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("players");
        if (document.find(Filters.eq("uniqueId", player.getUniqueId().toString())).first() == null) {
            document.insertOne(new Gson().fromJson(new Gson().toJson(player), Document.class));
            addPlayerToCach(player);
        }
    }

    public PlayerEntity getPlayer(UUID uniqueId) {
        if (this.players.containsKey(uniqueId)) return this.players.get(uniqueId);
        return null;
    }

    public void addPlayerToCach(PlayerEntity player) {
        this.players.put(player.getUniqueId(), player);
    }

    public void removePlayerFromCach(UUID uniqueId) {
        this.players.remove(uniqueId);
    }

    public Map<UUID, PlayerEntity> getPlayers() {
        return players;
    }


}
