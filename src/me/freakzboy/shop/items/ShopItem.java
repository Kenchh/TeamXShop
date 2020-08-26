package me.freakzboy.shop.items;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopItem extends ItemStack {
	
	/* This class is used to easily create a new item for the shop */
	
	public int buyprice;
	public int sellprice;
	
	public boolean stackable;
	
	public ShopItem(String itemname, Material material, int buyprice, int sellprice, boolean stackable) {
		
		// Defines, if an item in the shop is stackable / not an actual minecraft item
		this.stackable = stackable;
		
		setType(material);
		setAmount(1);
		
		ItemMeta itemmeta = getItemMeta();
		itemmeta.setDisplayName(itemname);
		
		this.buyprice = buyprice;
		
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(0, "§r§2§lLeft-Click §rto §2§lBuy 1");
		lore.add(1, "§r§lPrice: §6" + buyprice);
		
		if(stackable == true) {

			lore.add(2, "");
			lore.add(3, "§r§2§lShift-Left-Click §rto §2§lBuy 64");
			lore.add(4, "§r§lPrice: §6" + (buyprice * 64));
			
			/* If the sellprice is -1, this means, that the item can not be sold.*/
			if(sellprice != -1) {
				
				this.sellprice = sellprice;
				
				lore.add(5, "");
				lore.add(6, "§r§2§lRight-Click §rto §2§lSell 1");
				lore.add(7, "§r§lPrice: §6" + sellprice);
				lore.add(8, "");
				lore.add(9, "§r§2§lShift-Right-Click §rto §2§lSell all");
				lore.add(10, "§r§lPrice: §6" + (sellprice * 64));
			} 
			
		} else {
		
			/* If the sellprice is -1, this means, that the item can not be sold.*/
			if(sellprice != -1) {
				
				this.sellprice = sellprice;
				
				lore.add(2, "");
				lore.add(3, "§r§2§lRight-Click §rto §2§lSell 1");
				lore.add(4, "§r§lPrice: §6" + sellprice);
				lore.add(5, "");
				lore.add(6, "§r§2§lShift-Right-Click §rto §2§lSell all");
				lore.add(7, "§r§lPrice: §6" + (sellprice * 64));
			} 
		}
		
		itemmeta.setLore(lore);
		
		setItemMeta(itemmeta);
		
	}
	
	// Executed, when the item is bought
	public void buyItem(Player p, ShopItem shopitem, int amount) {
		
		int buyprice = shopitem.buyprice * amount;
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco take " + p.getName() + " " + buyprice);
		p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
		p.sendMessage("§4[TXG] §7You bought " + amount + " " + shopitem.getItemMeta().getDisplayName() + "!");
		
	}
	
	// Executed, when the item is sold
	public void sellItem(Player p, ShopItem shopitem, int amount) {
		
		int sellprice = shopitem.sellprice * amount;
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + p.getName() + " " + sellprice);
		p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
		p.sendMessage("§4[TXG] §7You sold " + amount + " " + shopitem.getItemMeta().getDisplayName() + "!");
		
	}
	
}
