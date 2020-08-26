package me.freakzboy.shop.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CARROT extends ShopItem {

	public CARROT() {
		super("Carrot", Material.CARROT_ITEM, 10, 10, true);
		
	}
	
	/* Buy-method for carrots */
	@Override
	public void buyItem(Player p, ShopItem shopitem, int amount) {
		
		super.buyItem(p, shopitem, amount);
		p.getInventory().addItem(new ItemStack(shopitem.getType(), amount));
		
	}
	
	int amountA;
	
	/* Sell-method for carrots */
	@Override
	public void sellItem(Player p, ShopItem shopitem, int amount) {
		
		/* 
		   The difference between amount and amountP:
		   "amount" is the amount of items requested to be sold when right clicking in the shop.
		   "amountA" is the actual amount, that is going to be sold, dependent on the condition (if iron swords aren't damaged)
		*/
		
		getSellAmount(p, amount);
		
		super.sellItem(p, shopitem, amountA);
		p.getInventory().removeItem(new ItemStack(shopitem.getType(), amountA));
			
	}
	
	/* This method determines the amount, than can be sold, regarding how much of the item the player has in his inventory */
	public void getSellAmount(Player p, int amount) {
		
		int i = 0;
		for(ItemStack item : p.getInventory()) {
			
			if(item != null) {
				if(item.getType() == Material.CARROT_ITEM) {
						
					/* Depends, if the player wanted to only sell one item -> break the for-loop after a single execution*/
					if(amount == 1) {
						i++;
						break;
					}
					
					/* Weapon hasn't been damaged -> increase the sell-amount */
					i += item.getAmount();
						
				}
			}
		}
		
		amountA = i;
		
	}
	
}
