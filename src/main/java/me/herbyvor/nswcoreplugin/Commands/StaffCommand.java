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

import java.util.Objects;

public class StaffCommand implements CommandExecutor {

    private NswCorePlugin main;
    public StaffCommand(NswCorePlugin main1) {this.main = main1;}
    Player p;
    Location oldLoc;
    Inventory oldInv;

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


            //wand
            ItemStack wand = new ItemStack(Material.STICK);
            Objects.requireNonNull(wand.getItemMeta()).setDisplayName("imobilisator");

            main.IsStaffMod.putIfAbsent(p, false);


            if (main.IsStaffMod.get(p) == false){
                main.IsStaffMod.put(p, true);
                //activation du staffmod
                p.sendMessage("§4§ACTIVATION DU STAFFMOD");

                //stockage anciennes informations
                oldLoc = p.getLocation();
                oldInv = Bukkit.createInventory(p, InventoryType.PLAYER);
                oldInv.setContents(p.getInventory().getContents());

                //entrée en staffmod
                p.getInventory().clear();
                p.setGameMode(GameMode.SPECTATOR);
                p.getInventory().setItemInMainHand(wand);

            } else if (main.IsStaffMod.get(p) == true){
                main.IsStaffMod.put(p, false);
                //désactivation du staffmod
                p.sendMessage("§4§lDESACTIVATION DU STAFFMOD");

                //reset des anciennes informations
                p.teleport(oldLoc);
                p.getInventory().setContents(oldInv.getContents());

                //sortie du staffmod
                p.setGameMode(GameMode.SURVIVAL);



            }



        }




        return true;
    }
}
