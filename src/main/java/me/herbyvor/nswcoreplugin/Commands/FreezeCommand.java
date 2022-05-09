package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    private NswCorePlugin main;
    public FreezeCommand(NswCorePlugin main1) {this.main = main1;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        main.IsStaffMod.putIfAbsent(p.getUniqueId(), false);
        if (main.IsStaffMod.get(p.getUniqueId()) == true) {
            if (args.length == 0) {
                p.sendMessage("Veuillez indiquer le joueur que vous souhaitez freeze ou unfreeze");
            }
            if (args.length >= 1) {
                Player t = Bukkit.getPlayer(args[0]);

                if (main.IsFrozen.get(t.getUniqueId()) == false) {
                    //freeze du joueur
                    main.IsFrozen.put(t.getUniqueId(), true);
                    t.sendMessage("§4§lVOUS AVEZ ETES IMOBILISE PAR " + p.getName());
                    p.sendMessage("§5§lvous avez imobilisé " + t.getName());
                } else {
                    //unfreeze du joueur
                    main.IsFrozen.put(t.getUniqueId(), false);
                    t.sendMessage("§avous pouvez à nouveau bouger");
                    p.sendMessage("§5§lvous avez libére" + t.getName());
                }

            }
        }


        return true;
    }
}
