package dev.uprank.prison.shop.entity;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShopEntity {

    private final UUID id;
    private Location schematicLocation;
    private Location teleportLocation;

    private final Map<String, Object> shopData;

    public ShopEntity(UUID id, Location schematicLocation, Location teleportLocation) {
        this.id = id;
        this.schematicLocation = schematicLocation;
        this.teleportLocation = teleportLocation;

        this.shopData = new HashMap<>();
    }

    public UUID getId() {
        return id;
    }

    public Location getSchematicLocation() {
        return schematicLocation;
    }

    public Location getTeleportLocation() {
        return teleportLocation;
    }

    public Map<String, Object> getShopData() {
        return shopData;
    }

    public void setSchematicLocation(Location schematicLocation) {
        this.schematicLocation = schematicLocation;
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("shops").updateOne(
                Filters.eq("id", id.toString()), Updates.set("schematicLocation", schematicLocation));
    }

    public void setTeleportLocation(Location teleportLocation) {
        this.teleportLocation = teleportLocation;
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("shops").updateOne(
                Filters.eq("id", id.toString()), Updates.set("teleportLocation", teleportLocation));
    }

    public void addShopData(String key, Object value) {
        Map<String, Object> data = this.shopData;
        data.put(key, value);
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("shops").updateOne(
                Filters.eq("id", id.toString()), Updates.set("teleportLocation", data));
    }

    public void removeShopData(String key) {
        Map<String, Object> data = this.shopData;
        data.remove(key);
        Prison.getInstance().getMongoClient().getDatabase("prison").getCollection("shops").updateOne(
                Filters.eq("id", id.toString()), Updates.set("teleportLocation", data));
    }

    public static class Location {

        private final String world;
        private final double x, y, z;
        private final float yaw, pitch;

        public Location(String world, double x, double y, double z, float yaw, float pitch) {
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        public String getWorld() {
            return world;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getZ() {
            return z;
        }

        public float getYaw() {
            return yaw;
        }

        public float getPitch() {
            return pitch;
        }

        public org.bukkit.Location toLocation() {
            return new org.bukkit.Location(Bukkit.getWorld(this.world), this.x, this.y, this.z, this.yaw, this.pitch);
        }

    }

}
