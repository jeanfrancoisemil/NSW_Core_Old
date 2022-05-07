package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpecCommand implements CommandExecutor {

    private NswCorePlugin main;
    public SpecCommand(NswCorePlugin main1) {this.main = main1;}

    boolean isSpec;
    GameMode oldGamemod;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        main.IsStaffMod.putIfAbsent(p, false);

        if (main.IsStaffMod.get(p) == true) {
            if (isSpec == false) {
                isSpec = true;
                oldGamemod = p.getGameMode();
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage("§dVous êtes désormais en gamemode spectateur. Vous ne pouvez plus utiliser les items du staffmod.");
            }else {
                isSpec = false;
                p.setGameMode(oldGamemod);
                p.setAllowFlight(true);
                p.sendMessage("§dVous n'êtes plus en gamemode spectateur");
            }

        }else {
            p.sendMessage("§dVous n'êtes pas en staffmod (/staff)");
        }

        return true;
    }
}
