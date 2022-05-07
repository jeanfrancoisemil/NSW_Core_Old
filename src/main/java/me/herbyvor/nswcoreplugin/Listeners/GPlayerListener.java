package me.herbyvor.nswcoreplugin.Listeners;

import com.google.common.util.concurrent.Service;
import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;

public class GPlayerListener implements Listener {

    private NswCorePlugin main;

    public GPlayerListener(NswCorePlugin main1){this.main = main1;}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        main.VoteNumber.put(p, 1);

        if (main.getConfig().contains("data")) {
            main.restoreMaps();
        }

        main.IsStaffMod.putIfAbsent(p.getUniqueId(), false);
        if (main.IsStaffMod.get(p.getUniqueId()) == true){
            p.setAllowFlight(true);
            p.sendMessage("§dVous êtes toujours en staffmod");
        }


    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent event){
        Player p = event.getPlayer();
        if (!main.IsStaffMod.isEmpty()){
            main.saveMaps();
        }
    }

    @EventHandler
    public void onLeftClick(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getDamager();
                Player t = (Player) event.getEntity();
                if (main.IsStaffMod.get(p.getUniqueId()) == true) {
                    if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("immobilisator")){
                        main.IsFrozen.putIfAbsent(t.getUniqueId(), false);
                        if (main.IsFrozen.get(t.getUniqueId()) == false) {
                            //freeze du joueur
                            main.IsFrozen.put(t.getUniqueId(), true);
                            t.sendMessage("§4§lVOUS AVEZ ETES IMOBILISES PAR " + p.getName());
                            p.sendMessage("§5§lvous avez imobilisé " + t.getName());
                        } else if (main.IsFrozen.get(t.getUniqueId()) == true) {
                            //unfreeze du joueur
                            main.IsFrozen.put(t.getUniqueId(), false);
                            t.sendMessage("§avous pouvez à nouveau bouger");
                            p.sendMessage("§5§lvous avez libére" + t.getName());
                        }
                    }else if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("investigator")){
                        p.openInventory(t.getInventory());
                    }


                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        main.IsFrozen.putIfAbsent(p.getUniqueId(), false);
        if (main.IsFrozen.get(p.getUniqueId()) == true) {
            event.setCancelled(true);
        }
    }



}
