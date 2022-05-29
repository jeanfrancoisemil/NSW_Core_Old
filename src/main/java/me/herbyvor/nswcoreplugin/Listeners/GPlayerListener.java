package me.herbyvor.nswcoreplugin.Listeners;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GPlayerListener implements Listener {

    private NswCorePlugin main;

    public GPlayerListener(NswCorePlugin main1){this.main = main1;}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player p = event.getPlayer();

        main.VoteNumber.put(p, 1);

        if (main.getConfig().contains("IsStaffdata") && main.getConfig().contains("IsFrozendata")){
            main.restoreMaps();
            System.out.println("map restaurées");
        }
        main.IsStaffMod.putIfAbsent(p.getUniqueId(), false);
        main.IsFrozen.putIfAbsent(p.getUniqueId(), false);
        if (main.IsStaffMod.get(p.getUniqueId()) == true){
            p.setAllowFlight(true);
            p.sendMessage("§dVous êtes toujours en staffmod");
        } else {
            if (p.hasPermission("staffmod")) {
                p.setInvisible(false);
                p.setInvulnerable(false);
                p.setCanPickupItems(true);
                p.sendMessage("§dVous n'êtes pas en staffmod. Faites /staff pour entrer en staff mod");
            }
        }


    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent event){
        Player p = event.getPlayer();
        if (!main.IsStaffMod.isEmpty() && !main.IsFrozen.isEmpty()){
            main.saveMaps();
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event){
            if (event.getRightClicked() instanceof Player) {
                Player p = (Player) event.getPlayer();
                Player t = (Player) event.getRightClicked();
                if(event.getHand() == EquipmentSlot.OFF_HAND) return;
                if (main.IsStaffMod.get(p.getUniqueId()) == true) {
                    if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("immobilisator")){
                        if (main.IsFrozen.get(t.getUniqueId()) == false) {
                            //freeze du joueur
                            main.IsFrozen.put(t.getUniqueId(), true);
                            t.sendMessage("§4§lVOUS AVEZ ETES IMOBILISE PAR " + p.getName());
                            p.sendMessage("§5§lvous avez imobilisé " + t.getName());
                        } else {
                            //unfreeze du joueur
                            main.IsFrozen.put(t.getUniqueId(), false);
                            t.sendMessage("§avous pouvez à nouveau bouger");
                            p.sendMessage("§5§lvous avez libéré " + t.getName());
                        }
                    }else if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("investigator")){
                        Inventory tInv = t.getInventory();
                        p.openInventory(tInv);
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

    @EventHandler
    public void onChangeDimension(PlayerChangedWorldEvent event) {
        Player p = event.getPlayer();

        if (main.IsStaffMod.get(p.getUniqueId()) == true) {
            p.setAllowFlight(true);
            p.sendMessage("§dvous êtes toujours en staffmod");
        }
    }

}
