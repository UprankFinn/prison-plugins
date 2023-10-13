package dev.uprank.prison.util;

import dev.uprank.prison.Prison;
import dev.uprank.prison.entity.player.PlayerEntity;
import dev.uprank.prison.scoreboard.ScoreboardUtil;
import lombok.Getter;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RankManager {

    private final Map<String, Integer> minePrices = new HashMap<>();
    private final Map<Integer, Integer> prestigePrices = new HashMap<>();
    private final Integer rebirthPrice = 10000000;

    public RankManager() {
        minePrices.put("A", 0);
        minePrices.put("B", 1000);
        minePrices.put("C", 100000);
        minePrices.put("D", 500000);
        minePrices.put("E", 1000000);
        minePrices.put("F", 5);
        minePrices.put("G", 5);
        minePrices.put("H", 5);
        minePrices.put("I", 5);
        minePrices.put("J", 5);
        minePrices.put("K", 5);
        minePrices.put("L", 5);
        minePrices.put("M", 5);
        minePrices.put("N", 5);
        minePrices.put("O", 5);
        minePrices.put("P", 5);
        minePrices.put("Q", 5);
        minePrices.put("R", 5);
        minePrices.put("S", 5);
        minePrices.put("T", 5);
        minePrices.put("U", 5);
        minePrices.put("V", 5);
        minePrices.put("W", 5);
        minePrices.put("X", 5);
        minePrices.put("Y", 5);
        minePrices.put("Z", 5);

        prestigePrices.put(1, 1000);
        prestigePrices.put(2, 1000);
        prestigePrices.put(3, 1000);
        prestigePrices.put(4, 1000);
        prestigePrices.put(5, 1000);
        prestigePrices.put(6, 1000);
        prestigePrices.put(7, 1000);
        prestigePrices.put(8, 1000);
        prestigePrices.put(9, 1000);
        prestigePrices.put(10, 1000);
        prestigePrices.put(11, 1000);
        prestigePrices.put(12, 1000);
        prestigePrices.put(13, 1000);
        prestigePrices.put(14, 1000);
        prestigePrices.put(15, 1000);
        prestigePrices.put(16, 1000);
        prestigePrices.put(17, 1000);
        prestigePrices.put(18, 1000);
        prestigePrices.put(19, 1000);
        prestigePrices.put(20, 1000);
        prestigePrices.put(21, 1000);
        prestigePrices.put(22, 1000);
        prestigePrices.put(23, 1000);
        prestigePrices.put(24, 1000);
        prestigePrices.put(25, 1000);
        prestigePrices.put(26, 1000);
        prestigePrices.put(27, 1000);
        prestigePrices.put(28, 1000);
        prestigePrices.put(29, 1000);
        prestigePrices.put(30, 1000);
        prestigePrices.put(31, 1000);
        prestigePrices.put(32, 1000);
        prestigePrices.put(33, 1000);
        prestigePrices.put(34, 1000);
        prestigePrices.put(35, 1000);
        prestigePrices.put(36, 1000);
        prestigePrices.put(37, 1000);
        prestigePrices.put(38, 1000);
        prestigePrices.put(39, 1000);
        prestigePrices.put(40, 1000);
        prestigePrices.put(41, 1000);
        prestigePrices.put(42, 1000);
        prestigePrices.put(43, 1000);
        prestigePrices.put(44, 1000);
        prestigePrices.put(45, 1000);
        prestigePrices.put(46, 1000);
        prestigePrices.put(47, 1000);
        prestigePrices.put(48, 1000);
        prestigePrices.put(49, 1000);
        prestigePrices.put(50, 1000);

    }

    public void rankUp(PlayerEntity player) {

        String currentRank = player.getMine();
        Integer currentPrestige = player.getPrestige();

        /*if (!(currentRank.equalsIgnoreCase("Z"))) {
            if (player.getBalance() >= minePrices.get(!player.getMine().equalsIgnoreCase("Z") ? Character.toString((char) (player.getMine().charAt(0) + 1)) : "Z")) {
                player.setMine(!player.getMine().equalsIgnoreCase("Z") ? Character.toString((char) (player.getMine().charAt(0) + 1)) : "A");
                player.removeBalance(this.minePrices.get(!player.getMine().equalsIgnoreCase("Z") ? Character.toString((char) (player.getMine().charAt(0) + 1)) : "Z"));
            } else {
                player.toPlayer().sendMessage(Prison.PREFIX + "§cDafür hast du nicht genügend Geld!");
                return;
            }
        } */
        if (!(currentRank.equalsIgnoreCase("Z"))) {
            String nextRank = !currentRank.equalsIgnoreCase("Z") ? Character.toString((char) (currentRank.charAt(0) + 1)) : "A";
            if (player.getBalance() >= minePrices.get(nextRank)) {
                player.setMine(nextRank);
                player.removeBalance(this.minePrices.get(nextRank));
            } else {
                player.toPlayer().sendMessage(Prison.PREFIX + "§cYou don't have enough money for that!");
                return;
            }
        }
        else {
            if (!(currentPrestige.equals(50))) {
                Integer price = minePrices.get(player.getMine()) + (player.getPrestige() * 50000);

                if (player.getBalance() >= prestigePrices.get(player.getPrestige() + 1)) {
                    player.setPrestige(player.getPrestige() + 1);
                    player.removeBalance(prestigePrices.get(player.getPrestige() + 1) + (price));
                } else {
                    player.toPlayer().sendMessage(Prison.PREFIX + "§cYou don't have enough money for that!");
                    return;
                }
            } else {
                if (player.getBalance() >= rebirthPrice) {
                    player.setRebirth(player.getRebirth() + 1);
                    player.setPrestige(0);
                    player.removeBalance(rebirthPrice);
                } else {
                    player.toPlayer().sendMessage(Prison.PREFIX + "§cYou don't have enough money for that!");
                    return;
                }
            }
            player.setMine("A");
        }

        player.addAllowedMine(Character.toString((char) (player.getMine().charAt(0))));

        ScoreboardUtil.updateScoreboard(player.toPlayer(), 7, "§7Current Rank: §6" + Prison.getInstance().getPlayerEntityManager().getPlayer(player.getUniqueId()).getMine());
        ScoreboardUtil.updateScoreboard(player.toPlayer(), 6, "§7Next Rank: §6" + (!Prison.getInstance().getPlayerEntityManager().getPlayer(player.getUniqueId()).getMine().equalsIgnoreCase("Z") ? Character.toString((char) (Prison.getInstance().getPlayerEntityManager().getPlayer(player.getUniqueId()).getMine().charAt(0) + 1)) : "A"));
        ScoreboardUtil.updateScoreboard(player.toPlayer(), 4, "§7Balance: §6" + MathUtil.kuerzeInteger(Prison.getInstance().getPlayerEntityManager().getPlayer(player.getUniqueId()).getBalance()));

        player.toPlayer().sendMessage(Prison.PREFIX + "§aYou have received a rankup!");
        player.toPlayer().playSound(player.toPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
    }
}
