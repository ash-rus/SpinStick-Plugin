package fishy.spinplugin.Tasks;

import fishy.spinplugin.SpinEffect;
import fishy.spinplugin.SpinPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpinTask extends BukkitRunnable {
    private int counter = 0;

    SpinPlugin plugin;

    public SpinTask(SpinPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        counter += 1;
        body();
    }

    private void body() {
        SpinEffect.runEffect();
        if (counter % 20 == 0) SpinEffect.decrementDurations();
    }
}
