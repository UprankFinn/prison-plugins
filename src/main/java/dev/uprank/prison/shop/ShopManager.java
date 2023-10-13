package dev.uprank.prison.shop;

import dev.uprank.prison.Prison;
import dev.uprank.prison.shop.entity.ShopEntityManager;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
public class ShopManager {

    public final Prison plugin;

    private final ShopEntityManager shopEntityManager;

    public ShopManager(Prison plugin) {
        this.plugin = plugin;

        this.shopEntityManager = new ShopEntityManager(this.plugin);
    }

    public void rentShop(Player player) {



    }

}
