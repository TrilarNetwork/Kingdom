package me.lenneth.kingdom.commands;

import me.lenneth.kingdom.Kingdom;
import me.lenneth.kingdom.commands.subcommands.AddClaim;
import me.lenneth.kingdom.commands.subcommands.RemoveClaim;
import me.lenneth.kingdom.utils.Claims;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClaimCommand implements TabExecutor {

    private Kingdom plugin;
    private ArrayList<ClaimSubCommands> subcommands = new ArrayList<ClaimSubCommands>();
    public ArrayList<ClaimSubCommands> getSubCommands(){return subcommands;}

    public ClaimCommand(Kingdom pl) {
        this.plugin = pl;
        subcommands.add(new AddClaim(plugin));
        subcommands.add(new RemoveClaim(plugin));
    }

    @SuppressWarnings("static-access")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.DARK_RED + "Invalid usage!");
            return true;
        }
        Player p = (Player) sender;
        if(args.length > 0) {
            for(int i = 0 ; i < getSubCommands().size(); i++) {
                if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    getSubCommands().get(i).perform(p, args);
                }
            }
            if(args[0].equalsIgnoreCase("list")) {
                if(plugin.claims.isEmpty()) {
                    p.sendMessage(ChatColor.RED + "There are no dungeons!");
                } else{
                    p.sendMessage(ChatColor.BOLD.DARK_GREEN + "Dungeons: ");
                    for(Claims claim : plugin.claims) {
                        p.sendMessage(ChatColor.DARK_RED + "\n----------\n");
                        p.sendMessage(ChatColor.GOLD + claim.getName());
                    }
                }
            }
        }
        else {
            p.sendMessage(ChatColor.DARK_RED + "Use an argument!");
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        ArrayList<String> subCommandArgs = new ArrayList<>();

        if(args.length == 1) {
            for(int i = 0; i < getSubCommands().size(); i++) {
                subCommandArgs.add(getSubCommands().get(i).getName());
            }
            subCommandArgs.add("list");
            return subCommandArgs;
        }
        else if(args.length == 2) {
            for(int i = 0 ; i < getSubCommands().size(); i++) {
                if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    return getSubCommands().get(i).getSubComArgs((Player)sender, args);
                }
            }
        }
        return null;
    }
}
