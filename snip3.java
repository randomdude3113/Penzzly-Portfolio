package net.pixelplanet.stats;

import java.sql.Statement;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.pixelplanet.stats.listener.JoinListener;
import net.pixelplanet.stats.mysql.MySQL;

public class PixelPlanetStats extends JavaPlugin {

	private static String host, user, password, database;
	private static int port;
	private static Plugin instance;
	private static MySQL sql;
	private static Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		host = getConfig().getString("mysql-host");
		user = getConfig().getString("mysql-user");
		password = getConfig().getString("mysql-password");
		database = getConfig().getString("mysql-database");
		port = getConfig().getInt("mysql-port");
		sql = new MySQL(host,port,user,password,database);
		try {
			if (!sql.hasConenction()) {
				sql.openConnection();			
				}	
		} catch (Exception e) {
			 log.severe("[PixelPlanetStats]: An error has occured! Read below for details");
			  e.printStackTrace();
			  log.severe("[PixelPlanetStats]: Plugin is being disabled, Please make sure you have the correct MySQL details and reload this plugin!");
			 getPluginLoader().disablePlugin(this);
		}
		if (sql.hasConenction()) {
			 String sql_stmt = "CREATE TABLE IF NOT EXISTS `stats_data` (\n"
			            + "    `uuid` VARCHAR(36) NOT NULL,\n"
			            + "    `tokens` VARCHAR(10) NOT NULL,\n"
			            + "    `crates` VARCHAR(30) NOT NULL,\n"
			            + "    `achievements` VARCHAR(6) NOT NULL,\n"
			            + "    PRIMARY KEY (`uuid`)\n"
			            + ");";
			 try {
				 Statement exec = sql.getConnection().createStatement();
				 exec.execute(sql_stmt);
			 } catch (Exception e) {
				 log.severe("[PixelPlanetStats]: An error has occured! Read below for details");
				  e.printStackTrace();
			 }
		}
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public static Plugin getInstance() {
		return instance;
	}
	
	public static Logger getPluginLogger() {
		return instance.getLogger();
	}
	
	public static MySQL getSQL() {
		return sql;
	}
	
}
