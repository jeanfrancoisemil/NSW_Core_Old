package me.herbyvor.nswcoreplugin.Listeners;

import com.google.common.util.concurrent.Service;
import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GPlayerListener implements Listener {

    private NswCorePlugin main;

    public GPlayerListener(NswCorePlugin main1){this.main = main1;}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        main.VoteNumber.put(p.getName(), 1);


    }

}
