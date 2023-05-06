package fishy.spinplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static fishy.spinplugin.SpinPlugin.GetPlugin;

public class SpinStick {
    public static final String willSpinString = "willSpin";
    public final Integer spinDuration;
    public final float spinDegrees;


    private final ItemStack stick = new ItemStack(Material.STICK, 1);
    private final ItemMeta stickMeta = stick.getItemMeta();
    PersistentDataContainer container = stickMeta.getPersistentDataContainer();
    public static final NamespacedKey willSpinKey = new NamespacedKey(GetPlugin(), willSpinString),
            spinDurationKey = new NamespacedKey(GetPlugin(), "spinDuration"),
            spinDegreesKey = new NamespacedKey(GetPlugin(), "spinDegrees");

    public SpinStick(Integer spinDuration, float spinDegrees) {
        this.spinDuration = spinDuration;
        this.spinDegrees = spinDegrees;

        stickMeta.lore(stickLore());
        stickMeta.displayName(stickName());

        container.set(willSpinKey, PersistentDataType.STRING, willSpinString);
        container.set(spinDurationKey, PersistentDataType.INTEGER, spinDuration);
        container.set(spinDegreesKey, PersistentDataType.FLOAT, spinDegrees);

        stick.setItemMeta(stickMeta);
    }

    Component stickName() {
        return Component.text().content("Spin Stick").color(TextColor.color(NamedTextColor.GOLD)).decoration(TextDecoration.ITALIC, false).build();
    }

    private List<Component> stickLore() {
        List<Component> loreList = new ArrayList<Component>();
        loreList.add(Component.text().content("An incredibly magical stick that").color(TextColor.color(NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false).build());
        loreList.add(Component.text().content("will send your enemies spinning!").color(TextColor.color(NamedTextColor.GRAY)).decoration(TextDecoration.ITALIC, false).build());
        loreList.add(Component.text(""));
        loreList.add(Component.text().content("Duration: ").color(TextColor.color(NamedTextColor.DARK_GRAY)).decoration(TextDecoration.ITALIC, false).
                append(Component.text().content(String.valueOf(spinDuration)).color(TextColor.color(NamedTextColor.GREEN)).decoration(TextDecoration.ITALIC, false)).build());
        loreList.add(Component.text().content("Degree of Rotation: ").color(TextColor.color(NamedTextColor.DARK_GRAY)).decoration(TextDecoration.ITALIC, false)
                .append(Component.text().content(String.valueOf(spinDegrees)).color(TextColor.color(NamedTextColor.GREEN)).decoration(TextDecoration.ITALIC, false)).build());

        return loreList;
    }

    public ItemStack getStick() {
        return stick;
    }
}