package me.freakzboy.shop.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FEED extends ShopItem {

	public FEED() {
		super("Feed", Material.COOKED_BEEF, 50, -1, false);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buyItem(Player p, ShopItem shopitem, int amount) {
		
		super.buyItem(p, shopitem, amount);
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "feed " + p.getName());
		
	}
	
	
}
