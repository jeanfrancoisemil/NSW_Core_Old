package me.herbyvor.nswcoreplugin;


import me.herbyvor.nswcoreplugin.Commands.*;
import me.herbyvor.nswcoreplugin.Listeners.GPlayerListener;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class NswCorePlugin extends JavaPlugin {

    public HashMap<Player, Integer> VoteNumber = new HashMap<Player, Integer>();
    public HashMap<UUID, Boolean> IsStaffMod = new HashMap<UUID, Boolean>();
    public HashMap<UUID, Boolean> IsFrozen = new HashMap<UUID, Boolean>();
    public HashMap<UUID, ItemStack[]> OldInv = new HashMap<UUID, ItemStack[]>();
    public HashMap<UUID, Location> OldLoc = new HashMap<UUID, Location>();
    public HashMap<UUID, String> OldGamemod = new HashMap<UUID, String>();

    @Override
    public void onEnable() {

        System.out.println("NSW Core plugin : On");

        Objects.requireNonNull(getCommand("vote")).setExecutor(new VoteCommand(this));
        Objects.requireNonNull(getCommand("staff")).setExecutor(new StaffCommand(this));
        Objects.requireNonNull(getCommand("spec")).setExecutor(new SpecCommand(this));
        Objects.requireNonNull(getCommand("freeze")).setExecutor(new FreezeCommand(this));
        Objects.requireNonNull(getCommand("invsee")).setExecutor(new InvseeCommand(this));

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new GPlayerListener(this), this);


        this.saveDefaultConfig();
        if (this.getConfig().contains("IsStaffdata") && this.getConfig().contains("IsFrozendata") && this.getConfig().contains("OldInvdata") && this.getConfig().contains("OldLocdata") && this.getConfig().contains("OldGamemoddata")){
            this.restoreMaps();
        }
    }

    @Override
    public void onDisable() {
        if (!IsStaffMod.isEmpty() || !IsFrozen.isEmpty()){
            saveMaps();
        }
    }

    public void saveMaps() {

        for (Map.Entry<UUID, Boolean> entry : IsStaffMod.entrySet()) {
            this.getConfig().set("IsStaffdata." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<UUID, Boolean> entry : IsFrozen.entrySet()) {
            this.getConfig().set("IsFrozendata." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<UUID, ItemStack[]> entry : OldInv.entrySet()) {
            this.getConfig().set("OldInvdata." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<UUID, Location> entry : OldLoc.entrySet()) {
            this.getConfig().set("OldLocdata." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<UUID, String> entry : OldGamemod.entrySet()) {
            this.getConfig().set("OldGamemoddata." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();

    }

    public void restoreMaps() {

        this.getConfig().getConfigurationSection("IsStaffdata").getKeys(false).forEach(key -> {
            boolean content = this.getConfig().getBoolean("IsStaffdata." + key);
            IsStaffMod.put(UUID.fromString(key), content);
        });
        this.getConfig().getConfigurationSection("IsFrozendata").getKeys(false).forEach(key -> {
            boolean content = this.getConfig().getBoolean("IsFrozendata." + key);
            IsFrozen.put(UUID.fromString(key), content);
        });
        this.getConfig().getConfigurationSection("OldInvdata").getKeys(false).forEach(key -> {
            ItemStack[] content = ((List<ItemStack>) this.getConfig().get("OldInvdata." + key)).toArray(new ItemStack[0]);
            OldInv.put(UUID.fromString(key), content);
        });
        this.getConfig().getConfigurationSection("OldLocdata").getKeys(false).forEach(key -> {
            Location content = this.getConfig().getLocation("OldLocdata." + key);
            OldLoc.put(UUID.fromString(key), content);
        });
        this.getConfig().getConfigurationSection("OldGamemoddata").getKeys(false).forEach(key -> {
            String content = this.getConfig().getString("OldGamemoddata." + key);
            OldGamemod.put(UUID.fromString(key), content);
        });
    }
}
