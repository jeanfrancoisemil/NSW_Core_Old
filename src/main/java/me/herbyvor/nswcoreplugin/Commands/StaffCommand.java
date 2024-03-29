package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class StaffCommand implements CommandExecutor {

    private NswCorePlugin main;
    public StaffCommand(NswCorePlugin main1) {this.main = main1;}
    Player p;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Player c = (Player) sender;
        if (c.isOp() || c.hasPermission("staffmod")){
            if (args.length == 0){
                p = c;
            }
            if (args.length >= 1) {
                p = Bukkit.getPlayer(args[0]);
            }


            //freezer
            ItemStack freezer = new ItemStack(Material.BLUE_ICE);
            ItemMeta freezerMeta = freezer.getItemMeta();
            freezerMeta.setDisplayName("immobilisator");
            freezer.setItemMeta(freezerMeta);

            //invsee
            ItemStack invsee = new ItemStack(Material.CHEST);
            ItemMeta invseeMeta = invsee.getItemMeta();
            invseeMeta.setDisplayName("investigator");
            invsee.setItemMeta(invseeMeta);

            main.IsStaffMod.putIfAbsent(p.getUniqueId(), false);


            if (main.IsStaffMod.get(p.getUniqueId()) == false){
                main.IsStaffMod.put(p.getUniqueId(), true);
                //activation du staffmod
                p.sendMessage("§4§lACTIVATION DU STAFFMOD");

                //stockage anciennes informations
                main.OldLoc.put(p.getUniqueId(), p.getLocation());
                main.OldInv.put(p.getUniqueId(), p.getInventory().getContents());
                main.OldGamemod.put(p.getUniqueId(), p.getGameMode().toString());
                //entrée en staffmod
                p.getInventory().clear();
                p.setInvisible(true);
                p.setInvulnerable(true);
                p.setAllowFlight(true);
                p.setCanPickupItems(false);
                p.getInventory().setItem(0, freezer);
                p.getInventory().setItem(1, invsee);

            } else if (main.IsStaffMod.get(p.getUniqueId()) == true){
                main.IsStaffMod.put(p.getUniqueId(), false);
                //désactivation du staffmod
                p.sendMessage("§4§lDESACTIVATION DU STAFFMOD");

                p.setAllowFlight(false);
                //reset des anciennes informations
                p.teleport(main.OldLoc.get(p.getUniqueId()));
                p.getInventory().setContents(main.OldInv.get(p.getUniqueId()));
                p.setGameMode(GameMode.valueOf(main.OldGamemod.get(p.getUniqueId())));

                //sortie du staffmod
                p.setInvisible(false);
                p.setInvulnerable(false);
                p.setCanPickupItems(true);




            }



        }




        return true;
    }
}
