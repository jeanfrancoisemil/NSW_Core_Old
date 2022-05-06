package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        }




        return true;
    }
}
