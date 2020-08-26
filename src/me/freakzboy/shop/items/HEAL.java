package me.freakzboy.shop.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class HEAL extends ShopItem {

	/* When the sellprice is -1, this means, that the item can not be sold.*/
	public HEAL() {
		super("Heal", Material.GOLDEN_APPLE, 100, -1, false);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buyItem(Player p, ShopItem shopitem, int amount) {
		
		super.buyItem(p, shopitem, amount);
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "heal " + p.getName());
		
	}

}
