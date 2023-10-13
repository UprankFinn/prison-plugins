package dev.uprank.prison.shop;

import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import dev.uprank.prison.Prison;
import dev.uprank.prison.shop.entity.ShopEntityManager;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Random;

@Getter
public class ShopManager {

    public final Prison plugin;

    private final ShopEntityManager shopEntityManager;

    public ShopManager(Prison plugin) {
        this.plugin = plugin;

        this.shopEntityManager = new ShopEntityManager(this.plugin);
    }

    public void rentShop(Player player) {

        Integer random = Prison.staticRandom(1, this.shopEntityManager.getShopEntities().values().size());


    }

}
