package net.banmanager.utils;

import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
// Tree 2017. Portfolio purposes only.
import net.banmanager.BanManager;

public class BanUtil {

	public static String formatBanMessage(String ban_code, String ban_type, 
			String ban_time_created, String ban_time_expiration, String ban_reason) {
			return ChatColor.RED+""+ChatColor.BOLD+"You are permanently banned from "+ChatColor.AQUA+""+
		           ChatColor.BOLD+"StormCraft"+ChatColor.RED+""+ChatColor.BOLD+"!\n\n"
				 + ChatColor.GRAY+"Reason: "+ChatColor.WHITE+ban_reason+"\n"
				 + ChatColor.GRAY+"Appeal at "+ChatColor.AQUA+""+"http://stormcraft.co/appeal\n\n"
				 + ChatColor.GRAY+"Ban Code: " + ChatColor.DARK_GRAY+ban_code;	
	}
	
	public static String formatTempBanMessage(String ban_code, String ban_type, 
			String ban_time_created, String ban_time_expiration, String ban_reason) {
			return ChatColor.RED+""+ChatColor.BOLD+"You are temporarily banned from "+ChatColor.AQUA+""+
		           ChatColor.BOLD+"StormCraft"+ChatColor.RED+""+ChatColor.BOLD+"!\n\n"
				 + ChatColor.GRAY+"Reason: "+ChatColor.WHITE+ban_reason+"\n"
				 + ChatColor.GRAY+"Time Remaining: "+ChatColor.GREEN+timeRemainingString(ban_time_expiration)+"\n"
				 + ChatColor.GRAY+"Appeal at "+ChatColor.AQUA+""+"http://stormcraft.co/appeal\n\n"
				 + ChatColor.GRAY+"Ban Code: " + ChatColor.DARK_GRAY+ban_code;	
	}
	
	public static String timeRemainingString(String ban_time_expiration) {
		long btc = System.currentTimeMillis()/1000L, bte = Long.parseLong(ban_time_expiration.replace(".0",""));
		Bukkit.broadcastMessage("bte " + String.valueOf(bte) + " btc " +  String.valueOf(btc));
		 long rem = Math.abs(bte - btc);
		  int years = 0, months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0;
		   double const_yrs = 31557600.0, const_mths = 2629746.0,
				  const_wks = 604800, const_days = 86400, const_hrs = 3600,
				  const_min = 60, const_sec = 1;
		   while (rem >= const_yrs) {
			   years++;
			   rem -= const_yrs;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_mths) {
			   months++;
			   rem -= const_mths;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_wks) {
			   weeks++;
			   rem -= const_wks;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_days) {
			   days++;
			   rem -= const_days;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_hrs) {
			   hours++;
			   rem -= const_hrs;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_min) {
			   minutes++;
			   rem -= const_min;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   } while (rem >= const_sec) {
			   seconds++;
			   rem -= const_sec;
				// Bukkit.broadcastMessage(String.valueOf(rem));
		   }
		 String result = "";
		  if (years > 0) {
			  result = result + (years+" "+"Year"+(years == 1 ? "":"s")) + " ";
		  }
		  if (months > 0) {
			  result = result + (months+" "+"Month"+(months == 1 ? "":"s")) + " ";
		  }
		  if (weeks > 0) {
			  result = result + (weeks+" "+"Week"+(weeks == 1 ? "":"s")) + " ";
		  }
		  if (days > 0) {
			  result = result + (days+" "+"Day"+(days == 1 ? "":"s")) + " ";
		  }
		  if (hours > 0) {
			  result = result + (hours+" "+"Hour"+(years == 1 ? "":"s")) + " ";
		  }
		  if (minutes > 0) {
			  result = result + (minutes+" "+"Minute"+(minutes == 1 ? "":"s")) + " ";
		  }
		  if (seconds > 0) {
			  result = result + (seconds+" "+"Second"+(seconds == 1 ? "":"s")) + " ";
		  }
		  Bukkit.broadcastMessage(result.substring(0, result.length() - 1));
		 return result.substring(0, result.length() - 1);
	}
	
	public static String convertTimeString(String time_str) {
		time_str = time_str.toLowerCase();
		   double const_yrs = 31557600.0, const_mths = 2629746.0,
					  const_wks = 604800, const_days = 86400, const_hrs = 3600,
					  const_min = 60, const_sec = 1;
	  if (time_str.contains("y")) {
		 try {
			 long t_ldec = Long.parseLong(time_str.replaceAll("y",""));
			  return String.valueOf(t_ldec * const_yrs);
		 } catch (Exception ex) {
			 return null;
		 }
	  } else 
	  if (time_str.contains("mo")) {
		 try {
			 long t_ldec = Long.parseLong(time_str.replaceAll("mo",""));
			  return String.valueOf(t_ldec * const_mths);
		 } catch (Exception ex) {
			 return null;
		 }
	  } else 
	  if (time_str.contains("w")) {
			 try {
				 long t_ldec = Long.parseLong(time_str.replaceAll("w",""));
				  return String.valueOf(t_ldec * const_wks);
			 } catch (Exception ex) {
				 return null;
			 }
		  } else 
	  if (time_str.contains("d")) {
			 try {
				 long t_ldec = Long.parseLong(time_str.replaceAll("d",""));
				  return String.valueOf(t_ldec * const_days);
			 } catch (Exception ex) {
				 return null;
			 }
		  } else 
	  if (time_str.contains("h")) {
			 try {
				 long t_ldec = Long.parseLong(time_str.replaceAll("h",""));
				  return String.valueOf(t_ldec * const_hrs);
			 } catch (Exception ex) {
				 return null;
			 }
		  } else 
	  if (time_str.contains("m")) {
			 try {
				 long t_ldec = Long.parseLong(time_str.replaceAll("m",""));
				  return String.valueOf(t_ldec * const_min);
			 } catch (Exception ex) {
				 return null;
			 }
		  } else 
	  if (time_str.contains("s")) {
			 try {
				 long t_ldec = Long.parseLong(time_str.replaceAll("s",""));
				  return String.valueOf(t_ldec * const_sec);
			 } catch (Exception ex) {
				 return null;
			 }
		  } else {
			  return null;
		  }
	}
	
	public static void banPlayer(Player player, String ban_type, String ban_time_expiration, String ban_identifier, String ban_reason) {
		String banCode = ban_identifier+"-"+generateRandomBanCode(9);
		String uuid = player.getUniqueId().toString();
		String ban_time_created = String.valueOf(System.currentTimeMillis()/1000L);
		String ban_appealed = "NO";
        boolean startGenLoop = true;
	   while (startGenLoop) {
		   String query = "SELECT * FROM bans WHERE ban_code = '"+banCode+"';";	  
			try {
				Statement exec = BanManager.getSQL().getConnection().createStatement();
			     ResultSet rs = exec.executeQuery(query);
			      if (rs.next()) {
			    	  startGenLoop = true;
			    	  banCode = ban_identifier+"-"+generateRandomBanCode(9); 
			      } else {
			    	  startGenLoop = false;
			      }
			} catch (Exception e) {
				Logger log = BanManager.getPluginLogger();
				 log.severe("[BanManager]: An error has occured! Read below for details");
				  e.printStackTrace();
			}
	   }
	   try {
	    	Statement exec = BanManager.getSQL().getConnection().createStatement();
		     String sql_stmt = "INSERT INTO bans (ban_code, uuid, ban_type, ban_time_created, ban_time_expiration, ban_reason, ban_appealed) VALUES ('"+banCode+"',"
		     		+ " '"+uuid+"',"
		     				+ " '"+ban_type+"',"
		     						+ " '"+ban_time_created+"',"
		     								+ " '"+ban_time_expiration+"',"
		     										+ " '"+ban_reason+"',"
		     												+ " '"+ban_appealed+"');";
		       exec.execute(sql_stmt);
	    } catch (Exception e) {
 			Logger log = BanManager.getPluginLogger();
			 log.severe("[BanManager]: An error has occured! Read below for details");
			  e.printStackTrace();
	    }	
	    try {
			   String sql_stmt = "UPDATE playerdata SET current_ban_code = '"+banCode+"' where uuid = '"+uuid+"';";
		    	Statement statement = BanManager.getSQL().getConnection().createStatement();
			      statement.executeUpdate(sql_stmt);
		    } catch (Exception e) {
		    	Logger log = BanManager.getPluginLogger();
				 log.severe("[BanManager]: An error has occured! Read below for details");
				  e.printStackTrace();
		    }
	   if (ban_time_expiration.equals("-1")) {
		   player.kickPlayer(formatBanMessage(banCode,ban_type,ban_time_created,ban_time_expiration,ban_reason));
	   } else {
		   player.kickPlayer(formatTempBanMessage(banCode,ban_type,ban_time_created,ban_time_expiration,ban_reason));
	   }
	}
	
	public static void banPlayerOffline(UUID player_uuid, String ban_type, String ban_time_expiration, String ban_identifier, String ban_reason) {
		String banCode = ban_identifier+"-"+generateRandomBanCode(9);
		String uuid = player_uuid.toString();
		String ban_time_created = String.valueOf(System.currentTimeMillis()/1000L);
		String ban_appealed = "NO";
        boolean startGenLoop = true;
	   while (startGenLoop) {
		   String query = "SELECT * FROM bans WHERE ban_code = '"+banCode+"';";	  
			try {
				Statement exec = BanManager.getSQL().getConnection().createStatement();
			     ResultSet rs = exec.executeQuery(query);
			      if (rs.next()) {
			    	  startGenLoop = true;
			    	  banCode = ban_identifier+"-"+generateRandomBanCode(9); 
			      } else {
			    	  startGenLoop = false;
			      }
			} catch (Exception e) {
				Logger log = BanManager.getPluginLogger();
				 log.severe("[BanManager]: An error has occured! Read below for details");
				  e.printStackTrace();
			}
	   }
	   try {
	    	Statement exec = BanManager.getSQL().getConnection().createStatement();
		     String sql_stmt = "INSERT INTO bans (ban_code, uuid, ban_type, ban_time_created, ban_time_expiration, ban_reason, ban_appealed) VALUES ('"+banCode+"',"
		     		+ " '"+uuid+"',"
		     				+ " '"+ban_type+"',"
		     						+ " '"+ban_time_created+"',"
		     								+ " '"+ban_time_expiration+"',"
		     										+ " '"+ban_reason+"',"
		     												+ " '"+ban_appealed+"');";
		       exec.execute(sql_stmt);
	    } catch (Exception e) {
 			Logger log = BanManager.getPluginLogger();
			 log.severe("[BanManager]: An error has occured! Read below for details");
			  e.printStackTrace();
	    }	
	    try {
			   String sql_stmt = "UPDATE playerdata SET current_ban_code = '"+banCode+"' where uuid = '"+uuid+"';";
		    	Statement statement = BanManager.getSQL().getConnection().createStatement();
			      statement.executeUpdate(sql_stmt);
		    } catch (Exception e) {
		    	Logger log = BanManager.getPluginLogger();
				 log.severe("[BanManager]: An error has occured! Read below for details");
				  e.printStackTrace();
		    }
	}
	
	
	
	  public static String generateRandomBanCode(int length)
	  {
	      String letters = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";
	      Random random = new SecureRandom();
	      String pw = "";
	      for (int i=0; i<length; i++)
	      {
	          int index = (int)(random.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	      return pw;
	  }
	
}
