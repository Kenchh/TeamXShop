package me.freakzboy.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import me.freakzboy.main.TeamXShop;
import me.freakzboy.shop.items.CARROT;
import me.freakzboy.shop.items.FEED;
import me.freakzboy.shop.items.HEAL;
import me.freakzboy.shop.items.IRON_SWORD;
import me.freakzboy.shop.items.ShopItem;

public class Shop implements Listener {
	
	/* This class is the shop-inventory, in which all of the stuff happens */
	
	/* Additional list with the shop items, to gain access to its containing variables (like buyprice) */
	public ArrayList<ShopItem> shopitems = new ArrayList<ShopItem>();
	public Inventory inv;
	
	public Shop() {
		
		inv = Bukkit.createInventory(null, InventoryType.CHEST, "PvP Shop");
		
	}
	
	/* This method is used, to initialize all shopitems at the start of the server, instead of calling it everytime someone openes the shop
	   By only calling it once at the start of the server, instead on every interact, it will reduce server lag */
	public void initShopItems() {
		addShopItem(new IRON_SWORD(), 10);
		addShopItem(new CARROT(), 12);
		addShopItem(new HEAL(), 14);
		addShopItem(new FEED(), 16);
	}
	
	/* Here I set an item in the shop and add it to the arraylist */
	public void addShopItem(ShopItem shopitem, int slot) {
		inv.setItem(slot, shopitem);
		shopitems.add(shopitem);
	}
	
	/* With this method, I can easily grab myself a shopitem by its displayname and gain access to its values and methods */
	public ShopItem getShopItem(String displayname) {
		for(ShopItem shopitem : shopitems) {
			if(shopitem.getItemMeta().getDisplayName().equalsIgnoreCase(displayname)) {
				return shopitem;
			}
		}
		
		return null;
	}
	
	public void openInventory(Player p) {
		p.openInventory(inv);
	}
	
	public boolean cooldown = false;
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		
		if(e.getInventory().getTitle().equals(inv.getTitle())) {
			Player p = (Player) e.getWhoClicked();
			
			e.setCancelled(true);
			
			if(cooldown == true) {
				return;
			}
			
			// Searching for the stored "ShopItem" in the arraylist
			ShopItem shopitem = getShopItem(e.getCurrentItem().getItemMeta().getDisplayName());
			
			/* Note: The "amount" parameter in the underneath methods stand for the requested amount of items to be bought / sold, 
			 * not the actual amount, that actually gets given or taken away */
			if(e.getClick() == ClickType.LEFT) {
				shopitem.buyItem(p, shopitem, 1);
			}
			
			if(shopitem.stackable == true) {
				if(e.getClick() == ClickType.SHIFT_LEFT) {
					shopitem.buyItem(p, shopitem, 64);
				}
			}
			
			/* if item is sellable */
			if(shopitem.sellprice != -1) {
				if(e.getClick() == ClickType.RIGHT) {
					shopitem.sellItem(p, shopitem, 1);
				}
				
				/* -1 in amount stands for sell all */
				if(e.getClick() == ClickType.SHIFT_RIGHT) {
					shopitem.sellItem(p, shopitem, -1);
				}
			}
		}
		
		cooldown = true;
		
		/* Implemented a cooldown, since spamming an item can cause duping money */
		Bukkit.getScheduler().scheduleSyncDelayedTask(TeamXShop.getPlugin(TeamXShop.class), new Runnable() {
			
			@Override
			public void run() {
				cooldown = false;
				
			}
		}, 5L);
		
	}
	
}
