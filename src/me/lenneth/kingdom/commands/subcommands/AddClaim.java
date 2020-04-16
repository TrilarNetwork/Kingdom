package me.lenneth.kingdom.commands.subcommands;

import me.lenneth.kingdom.Kingdom;
import me.lenneth.kingdom.commands.ClaimSubCommands;
import me.lenneth.kingdom.utils.Claims;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AddClaim extends ClaimSubCommands {
    private Kingdom plugin;

    public AddClaim(Kingdom plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDesc() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSyntax() {
        return "/dungeon add <name>";
    }

    @Override
    public ArrayList<String> getSubComArgs(Player p, String[] args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void perform(Player p, String[] args) {
        if (args.length == 1) {
            p.sendMessage(ChatColor.RED + "Try " + ChatColor.RED + getSyntax());
        }
        if (args.length == 2) {
            String name = args[1];
            if (plugin.claimPoints.size() != 2) {
                p.sendMessage(ChatColor.RED + "You don't have enough selections!");
            } else if (plugin.claimPoints.size() == 2) {
                plugin.claims.add(new Claims(plugin.claimPoints.get(0).getX(), plugin.claimPoints.get(1).getX(),
                        plugin.claimPoints.get(0).getZ(), plugin.claimPoints.get(1).getZ(), name));
                plugin.claimPoints.clear();
                p.sendMessage(ChatColor.GOLD + "You have claimed a mining zone!");
            }
        }

    }


}
