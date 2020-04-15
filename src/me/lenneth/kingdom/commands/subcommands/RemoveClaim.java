package me.lenneth.kingdom.commands.subcommands;

import me.lenneth.kingdom.Kingdom;
import me.lenneth.kingdom.commands.ClaimSubCommands;
import me.lenneth.kingdom.utils.Claims;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RemoveClaim extends ClaimSubCommands {
    private Kingdom plugin;

    public RemoveClaim(Kingdom plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDesc() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSyntax() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<String> getSubComArgs(Player p, String[] args) {
        if (args.length == 2) {
            ArrayList<String> claimNames = new ArrayList<>();
            for (Claims claim : plugin.claims) {
                claimNames.add(claim.getName());
            }
            return claimNames;
        }
        return null;
    }

    @Override
    public void perform(Player p, String[] args) {
        if (args.length == 1) {
            if (plugin.claims.isEmpty()) {
                p.sendMessage(ChatColor.DARK_RED + "There are no mining zones!");
            } else if (!plugin.claims.isEmpty()) {
                Location loc = p.getLocation();
                for (Claims claim : plugin.claims) {
                    if (claim.between((int) loc.getX(), (int) loc.getZ())) {
                        p.sendMessage(ChatColor.GOLD + "You have removed claim: " + ChatColor.AQUA + claim.getName());
                        plugin.claims.remove(claim);
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "You are not in a claimed zone!");
                    }
                }
            }
        }
        if (args.length == 2) {
            String name = args[1];
            for (Claims claim : plugin.claims) {
                if (name.equalsIgnoreCase(claim.getName())) {
                    p.sendMessage(ChatColor.GOLD + "You have removed claim: " + ChatColor.AQUA + claim.getName());
                    plugin.claims.remove(claim);
                }
                else {
                    p.sendMessage(ChatColor.DARK_RED + "This claim does not exist!");
                }
            }
        }
    }
}
