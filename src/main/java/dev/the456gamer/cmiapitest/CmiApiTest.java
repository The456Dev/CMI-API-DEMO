package dev.the456gamer.cmiapitest;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Containers.CMIUser;
import com.Zrips.CMI.Containers.PlayerMail;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CmiApiTest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Plugin CMIPlugin = getServer().getPluginManager().getPlugin("CMI");
        if (CMIPlugin != null && CMIPlugin.isEnabled()) {
            getLogger().info("CMI is enabled v:" + CMI.getInstance().getDescription().getVersion());
        } else {
            getLogger().severe("CMI is not enabled, disabling");
            setEnabled(false);
        }
        getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if (!player.hasPlayedBefore()) {
            CMIUser user = CMI.getInstance().getPlayerManager().getUser(player);
            user.addMail(new PlayerMail("Billy Bob Jones", System.currentTimeMillis(), "Welcome to the server!\nmake sure to enjoy yourself and follow the rules"), true);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
