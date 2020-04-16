package me.lenneth.kingdom.events;

import me.lenneth.kingdom.Kingdom;
import me.lenneth.kingdom.utils.BlockMetaData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClaimEvent implements Listener {


    private Kingdom plugin;
    private BlockMetaData first;
    private BlockMetaData second;

    public ClaimEvent(Kingdom plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void leftClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Block b = event.getClickedBlock();
        Action a = event.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.SAND) && a.equals(Action.LEFT_CLICK_BLOCK)) {
            event.setCancelled(true);
            first = new BlockMetaData(b.getWorld(), b.getX(), b.getY(), b.getZ(), b.getType());
            if(plugin.claimPoints.size() != 2) {
                plugin.claimPoints.add(first);
            }
            p.sendMessage(ChatColor.AQUA + "First Selection: " + ChatColor.GOLD + first.toString());
        }
    }

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Block b = event.getClickedBlock();
        Action a = event.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.SAND) && a.equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            second = new BlockMetaData(b.getWorld(), b.getX(), b.getY(), b.getZ(), b.getType());
            if(plugin.claimPoints.size() != 2) {
                plugin.claimPoints.add(second);
            }
            p.sendMessage(ChatColor.GREEN + "Second Selection: " + ChatColor.LIGHT_PURPLE + second.toString());
        }
    }
}
