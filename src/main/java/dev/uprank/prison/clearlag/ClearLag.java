package dev.uprank.prison.clearlag;

import dev.uprank.prison.Prison;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.ItemDespawnEvent;

public class ClearLag {

    private final Prison plugin;

    private Integer startCountdown;
    private Integer countdown = 600;

    private Boolean started = true;

    public ClearLag(Prison plugin) {
        this.plugin = plugin;
        this.start();
    }

    public void start() {
        this.startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {

            if (this.started) {

                if (this.countdown == 600) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + 10 + " §cminutes!");
                } else if (this.countdown == 300) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + 5 + " §cminutes!");
                } else if (this.countdown == 120) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + 2 + " §cminutes!");
                } else if (this.countdown == 60) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + 1 + " §cminute!");
                } else if (this.countdown == 30) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + 30 + " §cseconds!");
                } else if (this.countdown == 15 || this.countdown == 10) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + this.countdown + " §cseconds!");
                } else if (this.countdown == 5 || this.countdown == 4 || this.countdown == 3 || this.countdown == 2) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + this.countdown + " §cseconds!");
                } else if (this.countdown == 1) {
                    Bukkit.broadcastMessage(Prison.PREFIX + "§cItems lying on the floor will be deleted in §e" + this.countdown + " §csecond!");
                } else if (this.countdown == 0) {

                    for (World world : Bukkit.getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity instanceof Item) {
                                entity.remove();
                            }
                        }
                    }

                    this.stopCountdown();
                    Bukkit.getScheduler().cancelTask(startCountdown);
                    this.start();
                }
                this.countdown--;
            } else {
                this.countdown = (600);
                Bukkit.getScheduler().cancelTask(startCountdown);
            }

        }, 20L, 20L);

    }

    public void stopCountdown() {
        Bukkit.getScheduler().cancelTask(this.startCountdown);
    }

}
