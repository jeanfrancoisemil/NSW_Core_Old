package me.herbyvor.nswcoreplugin;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.herbyvor.nswcoreplugin.Commands.*;
import me.herbyvor.nswcoreplugin.Listeners.GPlayerListener;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.comphenix.protocol.ProtocolLibrary.*;

public final class NswCorePlugin extends JavaPlugin {

    public HashMap<Player, Integer> VoteNumber = new HashMap<Player, Integer>();
    public HashMap<UUID, Boolean> IsStaffMod = new HashMap<UUID, Boolean>();
    public HashMap<UUID, Boolean> IsFrozen = new HashMap<UUID, Boolean>();
    public HashMap<UUID, Inventory> OldInv = new HashMap<UUID, Inventory>();
    public HashMap<UUID, Location> OldLoc = new HashMap<UUID, Location>();
    public HashMap<UUID, GameMode> OldGamemod = new HashMap<UUID, GameMode>();

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
        if (this.getConfig().contains("IsStaffdata") && this.getConfig().contains("IsFrozendata")){
            this.restoreMaps();
        }
    }

    @Override
    public void onDisable() {
        if (!IsStaffMod.isEmpty()){
            saveMaps();
        }
    }

    public void saveMaps() {

        for (Map.Entry<UUID, Boolean> entry : IsStaffMod.entrySet()) {
            this.getConfig().set("IsStaffdata." + entry.getKey(), entry.getValue());
            System.out.println("le joueur " + entry.getKey() + "a bien été sauvegardé avec le isStaff : " + entry.getValue());
        }
        for (Map.Entry<UUID, Boolean> entry : IsFrozen.entrySet()) {
            this.getConfig().set("IsFrozendata." + entry.getKey(), entry.getValue());
            System.out.println("le joueur " + entry.getKey() + "a bien été sauvegardé avec le isFrozen : " + entry.getValue());
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
    }
}
