package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand implements CommandExecutor {

    private NswCorePlugin main;
    public InvseeCommand(NswCorePlugin main1) {this.main = main1;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        main.IsStaffMod.putIfAbsent(p.getUniqueId(), false);
        if (main.IsStaffMod.get(p.getUniqueId()) == true) {
            if (args.length == 0) {
                p.sendMessage("§dVeuillez indiquer le joueur dont vous souhaitez inspecter l'inventaire");
            }
            if (args.length >= 1) {
                Player t = Bukkit.getPlayer(args[0]);

                Inventory tInv = t.getInventory();
                p.openInventory(tInv);

            }
        }

        return true;
    }
}
