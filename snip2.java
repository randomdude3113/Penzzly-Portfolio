package net.tree.duelwageraddon.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.tree.duelwageraddon.DWA;

public class InvClickListener implements Listener {
	
	private static HashMap<Player, ItemStack> itemForWager = new HashMap<Player, ItemStack>();
    private static HashMap<Player, Player> wagerer = new HashMap<Player, Player>();
    private static HashMap<Player, Boolean> finallyAccepted = new HashMap<Player, Boolean>();
    public static HashMap<Player, List<ItemStack>> wagerItems = new HashMap<Player, List<ItemStack>>();
    
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onClick(InventoryClickEvent event) {
		 
		if (ChatColor.stripColor(event.getInventory().getName()).equals("Place Item To Wager")) { 
			 
			 ItemStack clicked = event.getCurrentItem();
			  if (clicked.getType().equals(Material.STAINED_CLAY)) {
					ItemMeta clay_meta = clicked.getItemMeta();
				  if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Deny!")) {
						 
						 Player player = (Player) event.getWhoClicked();
						 Player otherPlayer = DWA.itemTradeGui.get(player);
						  player.closeInventory();
						  otherPlayer.closeInventory();
						 otherPlayer.sendMessage(ChatColor.RED+player.getName()+" doesnt want to duel anymore");
						 
						 itemForWager.put(player,null); itemForWager.put(otherPlayer, null);
						 wagerer.put(player,null); wagerer.put(otherPlayer, null);
						 finallyAccepted.put(player,null); finallyAccepted.put(otherPlayer, null);
					 }
				  
			ItemStack item = event.getInventory().getItem(13);
			 if (item != null && item.getType() != Material.AIR) {
				 
				  if (clicked.getType().equals(Material.STAINED_CLAY)) {
				
					  if (clicked.hasItemMeta()) {

						 if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Accept!")) {
							 
							 //Start Calling GUI Methods
							 Inventory inv = Bukkit.createInventory(null, 36, ChatColor.GREEN+"Accept Items For Wager");
							 ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7);
							   ItemMeta im = is.getItemMeta(); im.setDisplayName(" ");
							   is.setItemMeta(im);
								 ItemStack i2 = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)5);
								   ItemMeta im2 = i2.getItemMeta(); im2.setDisplayName(" ");
								   i2.setItemMeta(im2);
								   ItemStack accept = new ItemStack(Material.STAINED_CLAY, 1, (short)5);
								    ItemMeta accept_meta = accept.getItemMeta();
								     accept_meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Accept!");
								     accept.setItemMeta(accept_meta);
								     ItemStack deny = new ItemStack(Material.STAINED_CLAY, 1, (short)14);
									    ItemMeta deny_meta = deny.getItemMeta();
									     deny_meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Deny!");
									     deny.setItemMeta(deny_meta);
							  for (int i = 0; i < inv.getSize(); i++) {
								  inv.setItem(i,is);
							  }
							  inv.setItem(12, item);
							  inv.setItem(14, null);
							  inv.setItem(14, i2);
							  inv.setItem(21, accept);
							  inv.setItem(23, deny);
							  // End of GUI Drawing
							  
							  wagerer.put(DWA.itemTradeGui.get((Player)event.getWhoClicked()), (Player)event.getWhoClicked());
							   if (wagerer.get((Player)event.getWhoClicked()) != null) {
								   ItemStack otherItem = itemForWager.get(wagerer.get((Player)event.getWhoClicked()));
								   inv.setItem(14, otherItem);
								   wagerer.get((Player)event.getWhoClicked()).getOpenInventory().setItem(14, item);
							   }
							  itemForWager.put(((Player) event.getWhoClicked()), item);
							  ((Player) event.getWhoClicked()).openInventory(inv);
							 
						 } else if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Deny!")) {
							 
							 Player player = (Player) event.getWhoClicked();
							 Player otherPlayer = DWA.itemTradeGui.get(player);
							  player.closeInventory();
							  otherPlayer.closeInventory();
							 otherPlayer.sendMessage(ChatColor.RED+player.getName()+" doesnt want to duel anymore");
							 
							 itemForWager.put(player,null); itemForWager.put(otherPlayer, null);
							 wagerer.put(player,null); wagerer.put(otherPlayer, null);
							 finallyAccepted.put(player,null); finallyAccepted.put(otherPlayer, null);
						 }
					  }
				  }
			 }
			  event.setCancelled(true);
		} else if (clicked.getType().equals(Material.STAINED_GLASS_PANE)) {
			 event.setCancelled(true);
		}
		}
		if (ChatColor.stripColor(event.getInventory().getName()).equals("Accept Items For Wager")) { 
			 ItemStack clicked = event.getCurrentItem();
			  if (clicked.getType().equals(Material.STAINED_CLAY)) {
			ItemStack item = event.getInventory().getItem(13);
			
			
			if (clicked.getType().equals(Material.STAINED_CLAY) && clicked.hasItemMeta()) {
			ItemMeta clay_meta = clicked.getItemMeta();
				if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Deny!")) {
					 Player player = (Player) event.getWhoClicked();
					 Player otherPlayer = DWA.itemTradeGui.get(player);
					  player.closeInventory();
					  otherPlayer.closeInventory();
					 otherPlayer.sendMessage(ChatColor.RED+player.getName()+" doesnt want to duel anymore");
					 
					 itemForWager.put(player,null); itemForWager.put(otherPlayer, null);
					 wagerer.put(player,null); wagerer.put(otherPlayer, null);
					 finallyAccepted.put(player,null); finallyAccepted.put(otherPlayer, null);
					 
				 }
			}
			
			 if (item != null && item.getType() != Material.AIR) {
				  if (clicked.getType().equals(Material.STAINED_CLAY)) {
					  if (clicked.hasItemMeta()) {
						  ItemMeta clay_meta = clicked.getItemMeta();
						 if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Accept!")) {
							 finallyAccepted.put((Player)event.getWhoClicked(), true);
							 Player otherPlayer = DWA.itemTradeGui.get((Player)event.getWhoClicked());
							 Player player = (Player) event.getWhoClicked();
							 
								 if (finallyAccepted==null||finallyAccepted.get(otherPlayer)==null) {
									 player.closeInventory();
									  player.sendMessage(ChatColor.GOLD+"Waiting for "+otherPlayer.getName()+" to accept wager");
								 }
							 
							 try {
								 if (finallyAccepted.get(otherPlayer)) {
									  
									  player.closeInventory();
									  otherPlayer.closeInventory();
									  
									  otherPlayer.sendMessage(ChatColor.GREEN+"Wager has been accepted, duel starting");
									  player.sendMessage(ChatColor.GREEN+"Wager has been accepted, duel starting");
									  List<ItemStack> wi = new ArrayList<>();
									  wi.add(itemForWager.get(player));
									  wi.add(itemForWager.get(otherPlayer));
									  
									  wagerItems.put(player, wi);
									  wagerItems.put(otherPlayer, wi);
									  
									  player.getInventory().remove(itemForWager.get(player));
									  otherPlayer.getInventory().remove(itemForWager.get(otherPlayer));
									  
									  itemForWager.put(player,null); itemForWager.put(otherPlayer, null);
										 wagerer.put(player,null); wagerer.put(otherPlayer, null);
										 finallyAccepted.put(player,null); finallyAccepted.put(otherPlayer, null);
										 
										 Player p = DWA.req_init.get(otherPlayer);
										 
										 p.performCommand("duel accept " + (p.equals(otherPlayer) ? player.getName():otherPlayer.getName()));
										 
								  }
							 } catch (Exception e) {}
						 } else if (ChatColor.stripColor(clay_meta.getDisplayName()).equals("Deny!")) {
							 Player player = (Player) event.getWhoClicked();
							 Player otherPlayer = DWA.itemTradeGui.get(player);
							  player.closeInventory();
							  otherPlayer.closeInventory();
							 otherPlayer.sendMessage(ChatColor.RED+player.getName()+" doesnt want to duel anymore");
							 
							 itemForWager.put(player,null); itemForWager.put(otherPlayer, null);
							 wagerer.put(player,null); wagerer.put(otherPlayer, null);
							 finallyAccepted.put(player,null); finallyAccepted.put(otherPlayer, null);
							 
						 }
					  }
					  event.setCancelled(true);
				  }
			 }
			 event.setCancelled(true);
		} else  {
			event.setCancelled(true);
		}
		}
	}
	
}
