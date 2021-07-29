package bedwar;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Buy {
	public static void buy_chain(String name, PlayerInventory inv, Material mat, int count) {
		if (Shop.chain.get(name))
			Bukkit.getServer().getPlayer(name).sendMessage("You have already purchased this set of armor");
		else if (Shop.iron.get(name) || Shop.diamond.get(name))
			Bukkit.getServer().getPlayer(name).sendMessage("You have already purchased a better set of armor");
		else if (inv.contains(mat, count)) {
			Shop.remove(inv, mat, count);
			ItemStack IS = new ItemStack(Material.CHAINMAIL_LEGGINGS);
			ItemMeta IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setLeggings(IS);
			IS = new ItemStack(Material.CHAINMAIL_BOOTS);
			IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setBoots(IS);
			Shop.chain.put(name, true);
			Shop.iron.put(name, false);
			Shop.diamond.put(name, false);
		}
	}

	public static void buy_iron(String name, PlayerInventory inv, Material mat, int count) {
		if (Shop.iron.get(name))
			Bukkit.getServer().getPlayer(name).sendMessage("You have already purchased this set of armor");
		else if (Shop.diamond.get(name))
			Bukkit.getServer().getPlayer(name).sendMessage("You have already purchased a better set of armor");
		else if (inv.contains(mat, count)) {
			Shop.remove(inv, mat, count);
			ItemStack IS = new ItemStack(Material.IRON_LEGGINGS);
			ItemMeta IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setLeggings(IS);
			IS = new ItemStack(Material.IRON_BOOTS);
			IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setBoots(IS);
			Shop.chain.put(name, false);
			Shop.iron.put(name, true);
			Shop.diamond.put(name, false);
		}
	}

	public static void buy_diamond(String name, PlayerInventory inv, Material mat, int count) {
		if (Shop.diamond.get(name))
			Bukkit.getServer().getPlayer(name).sendMessage("You have already purchased this set of armor");
		else if (inv.contains(mat, count)) {
			Shop.remove(inv, mat, count);
			ItemStack IS = new ItemStack(Material.DIAMOND_LEGGINGS);
			ItemMeta IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setLeggings(IS);
			IS = new ItemStack(Material.DIAMOND_BOOTS);
			IM = IS.getItemMeta();
			IM.setUnbreakable(true);
			IS.setItemMeta(IM);
			inv.setBoots(IS);
			Shop.chain.put(name, false);
			Shop.iron.put(name, false);
			Shop.diamond.put(name, true);
		}
	}

	public static void buy_axe(String name, PlayerInventory inv, Material mat, int count, ItemStack level) {
		if (level.getType() == Material.WOODEN_AXE) {
			Shop.remove(inv, mat, count);
			Shop.axe.replace(name, 1);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.STONE_AXE && inv.contains(Material.WOODEN_AXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.WOODEN_AXE, 1);
			Shop.axe.replace(name, 2);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.IRON_AXE && inv.contains(Material.STONE_AXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.STONE_AXE, 1);
			Shop.axe.replace(name, 3);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.DIAMOND_AXE && inv.contains(Material.IRON_AXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.IRON_AXE, 1);
			Shop.axe.replace(name, 4);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
	}

	public static void buy_pickaxe(String name, PlayerInventory inv, Material mat, int count, ItemStack level) {
		if (level.getType() == Material.WOODEN_PICKAXE) {
			Shop.remove(inv, mat, count);
			Shop.pick_axe.replace(name, 1);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.STONE_PICKAXE && inv.contains(Material.WOODEN_PICKAXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.WOODEN_PICKAXE, 1);
			Shop.pick_axe.replace(name, 2);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.GOLDEN_PICKAXE && inv.contains(Material.STONE_PICKAXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.STONE_PICKAXE, 1);
			Shop.pick_axe.replace(name, 3);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
		if (level.getType() == Material.DIAMOND_PICKAXE && inv.contains(Material.GOLDEN_PICKAXE)) {
			Shop.remove(inv, mat, count);
			Shop.remove(inv, Material.GOLDEN_PICKAXE, 1);
			Shop.pick_axe.replace(name, 4);
			ItemStack IS = level.clone();
			ItemMeta IM = IS.getItemMeta();
			IM.setLore(new ArrayList<String>());
			IS.setItemMeta(IM);
			inv.addItem(IS);
		}
	}
}
