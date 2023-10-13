package dev.uprank.prison.reward.rarity;

import org.bukkit.ChatColor;

public enum Rarity {

    UNCOMMON(ChatColor.GREEN, false),
    COMMON(ChatColor.GREEN, false),
    RARE(ChatColor.BLUE, false),
    EPIC(ChatColor.DARK_PURPLE, false),
    LEGENDARY(ChatColor.GOLD, true),
    OP(ChatColor.DARK_RED, true),
    OP_(ChatColor.DARK_RED, true);

    private final ChatColor chatColor;
    private final Boolean bold;

    Rarity(ChatColor chatColor, Boolean bold) {
        this.chatColor = chatColor;
        this.bold = bold;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public Boolean isBold() {
        return bold;
    }
}
