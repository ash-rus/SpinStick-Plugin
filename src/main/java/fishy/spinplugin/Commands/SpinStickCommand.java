package fishy.spinplugin.Commands;

import fishy.spinplugin.SpinStick;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpinStickCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            final TextComponent invalidMessage = Component.text("Invalid arguments").color(TextColor.color(0xFF5555));

            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage(invalidMessage);
            return true;
        }

        Integer duration = args.length > 0
                ? Integer.parseInt(args[0])
                : 3;

        float spinDegrees = args.length > 1
                ? Float.parseFloat(args[1])
                : 20.0F;

        Player player = (Player) sender;
        player.getInventory().addItem(new SpinStick(duration, spinDegrees).getStick());
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return new ArrayList<>();
    }
}
