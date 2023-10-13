package dev.uprank.prison.shop.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import dev.uprank.prison.Prison;
import org.bson.Document;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.Consumer;

public class ShopEntityManager {

    private final Prison plugin;

    private final Map<UUID, ShopEntity> shopEntities;

    private final Gson gson;
    private final MongoCollection<Document> collection;

    public ShopEntityManager(Prison plugin) {
        this.plugin = plugin;
        this.shopEntities = new HashMap<>();

        this.gson = new GsonBuilder().create();
        this.collection = Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("shops");
        this.collection.find().forEach((Consumer<Document>) document -> {
            ShopEntity shopEntity = this.gson.fromJson(this.gson.toJson(document), ShopEntity.class);
            this.shopEntities.put(shopEntity.getId(), shopEntity);
        });
    }

    public TreeMap<UUID, ShopEntity> getSortedMines() {
        return new TreeMap<>(this.shopEntities);
    }

    public ShopEntity getShop(UUID id) {
        for (ShopEntity shop : this.shopEntities.values()) {
            if (shop.getId().equals(id)) {
                return shop;
            }
        }
        return null;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    /*public ShopEntity getShop(Player player) {
        //return this.getMine(player.getLocation());
    }*/

    public ShopEntity getShop(Location location) {
        for (ShopEntity mine : this.shopEntities.values()) {
            /*if (mine.getCuboid().contains(location)) {
                return mine;
            }*/
        }
        return null;
    }

    public Map<UUID, ShopEntity> getShopEntities() {
        return shopEntities;
    }

    public void registerShop(ShopEntity shopEntity) {
        this.collection.insertOne(this.gson.fromJson(this.gson.toJson(shopEntity), Document.class));
        this.shopEntities.put(shopEntity.getId(), shopEntity);
    }

    public void unregisterShop(String id) {
        this.shopEntities.remove(id);
    }

}
