package me.freakzboy.shop.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IRON_SWORD extends ShopItem {

	/* Here I create a new item in the shop and set its material, buy- & sellprice */
	
	public IRON_SWORD() {
		super("Iron Sword", Material.IRON_SWORD, 50, 50, false);
		
	}
	
	/* Buy-method for iron swords */
	@Override
	public void buyItem(Player p, ShopItem shopitem, int amount) {
		
		super.buyItem(p, shopitem, amount);
		p.getInventory().addItem(new ItemStack(shopitem.getType(), amount));
		
	}
	
	private int amountA;
	
	/* Sell-method for the iron swords */
	@Override
	public void sellItem(Player p, ShopItem shopitem, int amount) {
		
		/* 
		   The difference between amount and amountP:
		   "amount" is the amount of items requested to be sold when right clicking in the shop.
		   "amountA" is the actual amount, that is going to be sold, dependent on the condition (if iron swords aren't damaged)
		*/
		
		if(sellCondition(p, amount) == true) {
			
			super.sellItem(p, shopitem, amountA);
			p.getInventory().removeItem(new ItemStack(shopitem.getType(), amountA));
			
		} else {
			p.sendMessage("§4[TXT] §cYou don't have any undamaged iron swords!");
		}
		
	}
	
	
	/* Method for the special condition, in which iron sword musn't be damaged */
	public boolean sellCondition(Player p, int amountrequested) {
		
		int i = 0;
		/* Looping through each inventory slot */
		for(ItemStack item : p.getInventory()) {
			
			if(item != null) {
				if(item.getType() == Material.IRON_SWORD) {
					/* Checking if weapon has not been damaged*/
					if(item.getDurability() == 0) {
						
						/* Depends, if the player wanted to only sell one item -> break the for-loop after a single execution*/
						if(amountrequested == 1) {
							i++;
							break;
						}
						
						/* Weapon hasn't been damaged -> increase the sell-amount */
						i += item.getAmount();
						
					}
				}
			}
		}
		
		if(i == 0) {
			return false;
		}
		
		amountA = i;
		return true;
	}
	
}
