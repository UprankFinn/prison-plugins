package dev.uprank.prison.reward.builder;

import com.google.gson.Gson;
import dev.uprank.prison.reward.rarity.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class RewardBuilder {

    private ItemStack itemStack;

    public RewardBuilder(Material material) {
        this(material, 1);
    }

    public RewardBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public RewardBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }

    public RewardBuilder(Material material, int amount, byte durability) {
        itemStack = new ItemStack(material, amount, durability);
    }

    public RewardBuilder clone() {
        return new RewardBuilder(itemStack);
    }

    public RewardBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    public RewardBuilder setDisplayName(String displayName) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public RewardBuilder removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public RewardBuilder setSkullOwner(String owner) {
        try {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwner(owner);
            itemStack.setItemMeta(skullMeta);
        } catch (ClassCastException expected) {
        }
        return this;
    }

    public RewardBuilder addEnchant(Enchantment enchantment, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addEnchant(enchantment, level, true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        itemStack.addEnchantments(enchantments);
        return this;
    }

    public RewardBuilder setUnbreakable() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder setBannerBaseColor(DyeColor dyeColor) {
        BannerMeta bannerMeta = (BannerMeta) itemStack.getItemMeta();
        bannerMeta.setBaseColor(dyeColor);
        itemStack.setItemMeta(bannerMeta);
        return this;
    }

    public RewardBuilder addBannerPattern(Pattern pattern) {
        BannerMeta bannerMeta = (BannerMeta) itemStack.getItemMeta();
        bannerMeta.addPattern(pattern);
        itemStack.setItemMeta(bannerMeta);
        return this;
    }

    public RewardBuilder addFlags(ItemFlag... flag) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(flag);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder hideFlags() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.values());
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder setLore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder removeLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList(itemMeta.getLore());
        if (!lore.contains(line)) return this;
        lore.remove(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder removeLoreLine(int index) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList(itemMeta.getLore());
        if (index < 0 || index > lore.size()) return this;
        lore.remove(index);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder addLoreLine(String line) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList();
        if (itemMeta.hasLore()) lore = new ArrayList(itemMeta.getLore());
        lore.add(line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public RewardBuilder addLoreLine(String line, int position) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList(itemMeta.getLore());
        lore.set(position, line);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    @SuppressWarnings("deprecation")
    public RewardBuilder setDyeColor(DyeColor color) {
        this.itemStack.setDurability(color.getDyeData());
        return this;
    }

    @Deprecated
    public RewardBuilder setWoolColor(DyeColor color) {
        if (!itemStack.getType().equals(Material.WHITE_WOOL)) return this;
        this.itemStack.setDurability(color.getDyeData());
        return this;
    }

    public RewardBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
            leatherArmorMeta.setColor(color);
            itemStack.setItemMeta(leatherArmorMeta);
        } catch (ClassCastException expected) {

        }
        return this;
    }

    public RewardBuilder setRarity(Rarity rarity) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        Objects.requireNonNull(itemMeta).setLocalizedName(ChatColor.stripColor(this.itemStack.getItemMeta().getDisplayName()));

        List<String> lore = new ArrayList<>();
        lore.add("§7Rarity: " + rarity.getChatColor() + (rarity.isBold() ? "§l" : "") + rarity.name().toUpperCase());
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }


}
