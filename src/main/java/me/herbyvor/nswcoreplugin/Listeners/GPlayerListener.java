package me.herbyvor.nswcoreplugin.Listeners;

import com.google.common.util.concurrent.Service;
import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GPlayerListener implements Listener {

    private NswCorePlugin main;

    public GPlayerListener(NswCorePlugin main1){this.main = main1;}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        main.VoteNumber.put(p, 1);


    }

    @EventHandler
    public void onLeftClick(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getDamager();
                Player t = (Player) event.getEntity();
                if (main.IsStaffMod.get(p) == true) {
                    main.IsFrozen.putIfAbsent(p, false);
                    if (main.IsFrozen.get(t) == true) {
                        //freeze du joueur
                        main.IsFrozen.put(t, true);
                        t.sendMessage("§4§lVOUS AVEZ ETES IMOBILISES PAR " + p.getName());
                        p.sendMessage("§5§lvous avez imobilisé " + t.getName());
                    } else if (main.IsFrozen.get(t) == false) {
                        //unfreeze du joueur
                        main.IsFrozen.put(t, false);
                        t.sendMessage("§avous pouvez à nouveau bouger");
                        p.sendMessage("§5§lvous avez libére" + t.getName());
                    }

                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        main.IsFrozen.putIfAbsent(p, false);
        if (main.IsFrozen.get(p) == true) {
            event.setCancelled(true);
        }
    }



}
