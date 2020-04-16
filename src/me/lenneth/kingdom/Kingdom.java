package me.lenneth.kingdom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import me.shizleshizle.core.mysql.MySQLManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.java.JavaPlugin;

import me.lenneth.kingdom.commands.ClaimCommand;
import me.lenneth.kingdom.events.ClaimEvent;
import me.lenneth.kingdom.events.MiningEvent;
import me.lenneth.kingdom.events.SkillEvent;
import me.lenneth.kingdom.utils.BlockMetaData;
import me.lenneth.kingdom.utils.Claims;
import net.md_5.bungee.api.ChatColor;

public class Kingdom extends JavaPlugin{
    public HashMap<BlockMetaData, Long> blockMetaData = new HashMap<>();
    public ArrayList<BlockMetaData> claimPoints = new ArrayList<BlockMetaData>();
    public ArrayList<Claims> claims = new ArrayList<Claims>();
    public static MySQLManager sql;

    public SkillEvent skillEvent;
    public MiningEvent miningEvent;
    public ClaimEvent claimEvent;
    public Claims claim;

    public void onEnable() {
        final long time = System.currentTimeMillis();
        getLogger().info("Kingdom >> Enabling...");
        sql = MySQLManager.getInstance();
        if (!sql.hasConnection()) {
            sql.openConnection();
        }
        getCommand("dungeon").setExecutor(new ClaimCommand(this));
        getServer().getPluginManager().registerEvents(new MiningEvent(this), this);
        getServer().getPluginManager().registerEvents(new ClaimEvent(this), this);
        loadConfig();
        skillEvent = new SkillEvent(this);
        miningEvent = new MiningEvent(this);
        claimEvent = new ClaimEvent(this);
        claim = new Claims(this);
        skillEvent.runBlockChanger();

        final long result = System.currentTimeMillis() - time;
        getLogger().info("Kingdom >> Enabled! (" + result + " ms)");
    }

    @Override
    public void onDisable() {
        final long time = System.currentTimeMillis();
        getLogger().info("Kingdom >> Disabling...");
        try {
            if (sql.hasConnection()) {
                sql.closeConnection();
            }
        } catch (SQLException e) {
            getLogger().info("Kingdom >> MySQL Error: " + e);
        }
        final long result = System.currentTimeMillis() - time;
        getLogger().info("Kingdom >> Disabled! (" + result + " ms)");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }
}
