package me.herbyvor.nswcoreplugin;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.utility.MinecraftReflection;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import me.herbyvor.nswcoreplugin.Commands.SpecCommand;
import me.herbyvor.nswcoreplugin.Commands.StaffCommand;
import me.herbyvor.nswcoreplugin.Commands.VoteCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    @Override
    public void onEnable() {

        System.out.println("NSW Core plugin : On");

        Objects.requireNonNull(getCommand("vote")).setExecutor(new VoteCommand(this));
        Objects.requireNonNull(getCommand("staff")).setExecutor(new StaffCommand(this));
        Objects.requireNonNull(getCommand("spec")).setExecutor(new SpecCommand(this));
        this.saveDefaultConfig();

        if (this.getConfig().contains("data")){
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
        }
        for (Map.Entry<UUID, Boolean> entry : IsFrozen.entrySet()) {
            this.getConfig().set("IsFrozendata." + entry.getKey(), entry.getValue());
        }
        this.saveConfig();

    }

    public void restoreMaps() {

        Objects.requireNonNull(this.getConfig().getConfigurationSection("IsStaffdata")).getKeys(false).forEach(key -> {
            boolean content = this.getConfig().getBoolean("IsStaffdata." + key);
            IsStaffMod.put(UUID.fromString(key), content);
        });
        Objects.requireNonNull(this.getConfig().getConfigurationSection("IsFrozendata")).getKeys(false).forEach(Key -> {
            boolean content = this.getConfig().getBoolean("IsFrozendata." + Key);
            IsFrozen.put(UUID.fromString(Key), content);
        });
    }
}
