package dev.uprank.prison.crate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CrateItems {

    ICE_SWORD("§bIce Sword", RarityType.RARE, new ItemStack(Material.DIAMOND_SWORD)),
    ICE_AXE("§bIce Axe", RarityType.RARE, new ItemStack(Material.DIAMOND_AXE)),
    ICE_SHOVEL("§bIce Shovel", RarityType.RARE, new ItemStack(Material.DIAMOND_SHOVEL)),
    ICE_PICKAXE("§bIce Pickaxe", RarityType.RARE, new ItemStack(Material.DIAMOND_PICKAXE)),
    ICE_HELMET("§bIce Helmet", RarityType.RARE, new ItemStack(Material.DIAMOND_HELMET)),
    ICE_CHESTPLATE("§bIce Chestplace", RarityType.RARE, new ItemStack(Material.DIAMOND_CHESTPLATE)),
    ICE_LEGGINS("§bIce Leggins", RarityType.RARE, new ItemStack(Material.DIAMOND_LEGGINGS)),
    ICE_BOOTS("§bIce Boots", RarityType.RARE, new ItemStack(Material.DIAMOND_BOOTS))



    ;

    private final String name;
    private final RarityType rarityType;
    private final ItemStack itemStack;

    CrateItems(String name, RarityType rarityType, ItemStack itemStack) {
        this.name = name;
        this.rarityType = rarityType;
        this.itemStack = itemStack;
    }
}
