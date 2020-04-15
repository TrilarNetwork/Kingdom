package me.lenneth.kingdom.events;

import me.lenneth.kingdom.Kingdom;
import me.lenneth.kingdom.utils.BlockMetaData;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class SkillEvent implements Listener {

    private Kingdom plugin;

    public SkillEvent(Kingdom plugin) {this.plugin = plugin;}

    public void runBlockChanger() {
        Bukkit.getScheduler().runTaskTimer((Plugin) plugin, this::restoreBlocks, 0, 20);
    }

    public void restoreBlocks(){
        try{
            for(BlockMetaData metadata : plugin.blockMetaData.keySet()) {
                long old = plugin.blockMetaData.get(metadata);
                long current = System.currentTimeMillis();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(current - old);
                if(seconds >= 5) {
                    Location blockloc = new Location(metadata.getWorld(), metadata.getX(), metadata.getY(), metadata.getZ());
                    metadata.getWorld().getBlockAt(blockloc).setType(metadata.getMaterial());
                    metadata.getWorld().playEffect(blockloc, Effect.EXTINGUISH, 1, 10);
                    plugin.blockMetaData.remove(metadata);
                }
            }
        }
        catch(Exception e) {

        }
    }

}
