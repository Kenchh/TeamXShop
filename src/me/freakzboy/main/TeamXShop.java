package me.freakzboy.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.freakzboy.shop.Shop;

public class TeamXShop extends JavaPlugin implements Listener {
	
	Shop shop;
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		shop = new Shop();
		getServer().getPluginManager().registerEvents(shop, this);
		shop.initShopItems();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		
		if(e.getRightClicked() instanceof Player) {
			Player npc = (Player) e.getRightClicked();
			
			if(npc.getName().equalsIgnoreCase("PvP Shop")) {
				shop.openInventory(p);
			}
		}
	}
}
