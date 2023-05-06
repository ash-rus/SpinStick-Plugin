package fishy.spinplugin.Events;

import fishy.spinplugin.SpinEffect;
import fishy.spinplugin.SpinStick;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpinStickListeners implements Listener {
    @EventHandler
    public void onSpinStickAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getDamager();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.STICK) return;

        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (!(container.has(SpinStick.willSpinKey))) {
            return;
        }

        LivingEntity target = (LivingEntity) event.getEntity();
        Integer duration = container.get(SpinStick.spinDurationKey, PersistentDataType.INTEGER);
        float spinDegrees = container.get(SpinStick.spinDegreesKey, PersistentDataType.FLOAT);
        new SpinEffect(target, duration, spinDegrees);
    }

    @EventHandler
    public void onDualWield(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        if (!(container.has(SpinStick.willSpinKey))) {
            return;
        }

        event.setCancelled(true);
    }
}
