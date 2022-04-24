package me.herbyvor.nswcoreplugin.Commands;

import me.herbyvor.nswcoreplugin.NswCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.awt.*;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class VoteCommand implements CommandExecutor {

    private NswCorePlugin main;
    public VoteCommand(NswCorePlugin main1) {this.main = main1;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (main.VoteNumber.get(p) >= 7) {
                main.VoteNumber.put(p, 1);
            }

            //chaque votes :

            if (main.VoteNumber.get(p) == 1) {
                System.out.println(p.getName() + "à effectué le vote : 1");
                //création du book
                    BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                    meta.setTitle("Votez pour NoSkillworld !");
                    meta.setAuthor("Bobby");
                    meta.setPages("1");
                    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                    book.setItemMeta(meta);
                //ouverture du book
                    main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #1 : " + "https://www.serveurs-minecraft.org/vote.php?id=60934");

            }else if (main.VoteNumber.get(p) == 2) {
                System.out.println(p.getName() + "à effectué le vote : 2");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("2");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #2 : " + "https://serveur-minecraft.com/2598");

            }else if (main.VoteNumber.get(p) == 3) {
                System.out.println(p.getName() + "à effectué le vote : 3");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("3");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #3 : " + "https://serveurs-mc.net/index.php/serveur/251");

            }else if (main.VoteNumber.get(p) == 4) {
                System.out.println(p.getName() + "à effectué le vote : 4");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("4");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #4 : " + "https://top-serveurs.net/minecraft/vote/noskillworld");

            }else if (main.VoteNumber.get(p) == 5) {
                System.out.println(p.getName() + "à effectué le vote : 5");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("5");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #5 : " + "https://serveur-prive.net/minecraft/noskillworld-9550/vote");

            }else if (main.VoteNumber.get(p) == 6) {
                System.out.println(p.getName() + "à effectué le vote : 1");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("6");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);
                //si le book marche pas :
                p.sendMessage("§6§lVote #6 : " + "https://www.liste-serveurs-minecraft.org/serveur-minecraft/noskillworld/");

            }
        }


        return true;
    }
}
