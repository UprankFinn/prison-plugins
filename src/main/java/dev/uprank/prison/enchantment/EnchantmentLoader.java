package dev.uprank.prison.enchantment;

import dev.uprank.prison.Prison;
import dev.uprank.prison.enchantment.tool.Efficiency;
import dev.uprank.prison.enchantment.tool.Fortune;
import dev.uprank.prison.enchantment.tool.Unbreaking;
import dev.uprank.prison.enchantment.tool.custom.Laser;
import dev.uprank.prison.enchantment.weapon.Fireaspect;
import dev.uprank.prison.enchantment.weapon.KnockBack;
import dev.uprank.prison.enchantment.weapon.Sharpness;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EnchantmentLoader {

    private final Prison plugin;
    private final List<Enchantment> enchantments = new ArrayList<>();

    public EnchantmentLoader(Prison plugin) {
        this.plugin = plugin;
    }

    public void loadEnchantments() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(Enchantment.class, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.enchantments.add(new Laser(this.plugin));
        this.enchantments.add(new Efficiency(this.plugin));
        this.enchantments.add(new Fortune(this.plugin));
        this.enchantments.add(new Unbreaking(this.plugin));

        this.enchantments.add(new Fireaspect(this.plugin));
        this.enchantments.add(new KnockBack(this.plugin));
        this.enchantments.add(new Sharpness(this.plugin));

        this.enchantments.forEach((enchantments) -> {

            Enchantment.registerEnchantment(enchantments);
            System.out.println("[Enchantments] loaded Enchantment " + enchantments.getKey());
        });


    }

}
