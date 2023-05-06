package fishy.spinplugin;

import fishy.spinplugin.Commands.SpinCommand;
import fishy.spinplugin.Commands.SpinStickCommand;
import fishy.spinplugin.Events.SpinStickListeners;
import fishy.spinplugin.Tasks.SpinTask;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public final class SpinPlugin extends JavaPlugin implements Listener {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new SpinStickListeners(), this);

        Objects.requireNonNull(getCommand("spin")).setExecutor(new SpinCommand());
        Objects.requireNonNull(getCommand("spinstick")).setExecutor(new SpinStickCommand());

        BukkitTask spinTask = new SpinTask(this).runTaskTimer(this, 0, 1);
    }

    public static Plugin GetPlugin() {
        return plugin;
    }
}

