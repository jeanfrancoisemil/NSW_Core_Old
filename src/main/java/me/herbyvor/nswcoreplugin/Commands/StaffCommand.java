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

import java.util.Objects;

public class StaffCommand implements CommandExecutor {

    private NswCorePlugin main;
    public StaffCommand(NswCorePlugin main1) {this.main = main1;}
    Player p;
    Location oldLoc;
    Inventory oldInv;
    GameMode oldGamemod;

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
            freezer.getItemMeta().setDisplayName("immobilisator");

            //invsee
            ItemStack invsee = new ItemStack(Material.SPYGLASS);
            invsee.getItemMeta().setDisplayName("investigator");

            main.IsStaffMod.putIfAbsent(p, false);


            if (main.IsStaffMod.get(p) == false){
                main.IsStaffMod.put(p, true);
                //activation du staffmod
                p.sendMessage("§4§ACTIVATION DU STAFFMOD");

                //stockage anciennes informations
                oldLoc = p.getLocation();
                oldInv = Bukkit.createInventory(p, InventoryType.PLAYER);
                oldInv.setContents(p.getInventory().getContents());
                oldGamemod = p.getGameMode();
                //entrée en staffmod
                p.getInventory().clear();
                p.setInvisible(true);
                p.setInvulnerable(true);
                p.setAllowFlight(true);
                p.setCanPickupItems(false);
                p.getInventory().setItem(0, freezer);
                p.getInventory().setItem(1, invsee);

            } else if (main.IsStaffMod.get(p) == true){
                main.IsStaffMod.put(p, false);
                //désactivation du staffmod
                p.sendMessage("§4§lDESACTIVATION DU STAFFMOD");

                //sortie du staffmod
                p.setInvisible(false);
                p.setInvulnerable(false);
                p.setAllowFlight(false);
                p.setCanPickupItems(true);
                //reset des anciennes informations
                p.teleport(oldLoc);
                p.getInventory().setContents(oldInv.getContents());
                p.setGameMode(oldGamemod);




            }



        }




        return true;
    }
}
