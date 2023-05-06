package fishy.spinplugin.Commands;

import fishy.spinplugin.SpinEffect;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpinCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length < 1) {
            final TextComponent invalidMessage = Component.text("Invalid arguments").color(TextColor.color(0xFF5555));
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(invalidMessage);
            } else if (sender instanceof ConsoleCommandSender) {
                ConsoleCommandSender console = (ConsoleCommandSender) sender;
                console.sendMessage(invalidMessage);
            }

            return true;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);

        Integer duration = args.length > 1
                ? Integer.parseInt(args[1])
                : 3;

        float spinDegrees = args.length > 2
                ? Float.parseFloat(args[2])
                : 20F;

        new SpinEffect(target, duration, spinDegrees);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> options = new ArrayList<>();

        if (args.length == 1) {
            return getOnlinePlayerNames();
        }

        return options;
    }


    private List<String> getOnlinePlayerNames() {
        List<String> playerNames = new LinkedList<String>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerNames.add(player.getName());
        }

        return playerNames;
    }
}