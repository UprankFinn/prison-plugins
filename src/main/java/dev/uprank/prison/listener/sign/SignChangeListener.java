package dev.uprank.prison.listener.sign;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private final Prison plugin;

    public SignChangeListener(Prison plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void handle(SignChangeEvent event) {
        if (event.getLine(0).equals("[SHOP]")) {

            if (event.getLine(1).equals("SELL")) {
                if (!(exists(event.getLine(2)))) {
                    event.getPlayer().sendMessage(Prison.PREFIX + "§cThis Material does not exists!");
                    return;
                }

                event.setLine(0, "§8[§9Shop§8]");
                String line2 = event.getLine(2);
                event.setLine(2, "§a" + line2);

                Block attachedBlock = event.getBlock().getState().getBlock();
                Bukkit.broadcastMessage(attachedBlock.getType().toString());

                event.getPlayer().sendMessage(Prison.PREFIX + "§acreated!");

            }

        }

    }

    private boolean exists(String name) {
        for (Material material : Material.values()) {
            if (material.name().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

}
