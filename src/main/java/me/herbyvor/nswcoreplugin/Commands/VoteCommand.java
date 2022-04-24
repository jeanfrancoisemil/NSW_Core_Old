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

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.title;

public class VoteCommand implements CommandExecutor {

    private NswCorePlugin main;
    public VoteCommand(NswCorePlugin main1) {this.main = main1;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {

            Player p = (Player) sender;


            main.VoteNumber.merge(p, 1, Integer::sum);

            if (main.VoteNumber.get(p) >= 8) {
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

            }else if (main.VoteNumber.get(p) == 7) {
                System.out.println(p.getName() + "à effectué le vote : 1");
                //création du book
                BookMeta meta = (BookMeta) Bukkit.getItemFactory().getItemMeta(Material.WRITTEN_BOOK);
                assert meta != null;
                meta.setTitle("Votez pour NoSkillworld !");
                meta.setAuthor("Bobby");
                meta.setPages("7");
                ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
                book.setItemMeta(meta);
                //ouverture du book
                main.openBook(p,book);

            }




        }


        return true;
    }
}
