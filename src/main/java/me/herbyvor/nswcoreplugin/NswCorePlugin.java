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

    }

    /*public void openBook(Player p, ItemStack book){
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);
        try {
            PacketContainer pc = getProtocolManager().createPacket(PacketType.Play.Server.CUSTOM_PAYLOAD);
            pc.getModifier().writeDefaults();
            // NOTICE THE CODE BELOW!
            ByteBuf bf = Unpooled.buffer(256); // #1
            bf.setByte(0, (byte)0); // #2
            bf.writerIndex(1); // #3
            pc.getModifier().write(1, MinecraftReflection.getPacketDataSerializer(bf));
            // END OF NOTABLE CODE
            pc.getStrings().write(0, "MC|BOpen");
            getProtocolManager().sendServerPacket(p, pc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        p.getInventory().setItem(slot, old);
    } */

}
