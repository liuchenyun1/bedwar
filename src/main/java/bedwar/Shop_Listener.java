package bedwar;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Shop_Listener implements Listener {
	@EventHandler
	void hurt(EntityDamageEvent event) {
		if(event.getEntityType()==EntityType.VILLAGER) event.setCancelled(true);
	}
	
	@EventHandler
	void click(InventoryClickEvent event) {
		if(!Bedwar.start) return;
		Inventory inv = event.getClickedInventory();
		if(event.getCurrentItem()==null) return;
		System.out.println(event.getCurrentItem().getItemMeta().getLore());
		if(event.getCurrentItem().getType() != Material.BLACK_STAINED_GLASS_PANE
				&& event.getCurrentItem().getType() != Material.YELLOW_STAINED_GLASS_PANE
				&& event.getCurrentItem().getType() != Material.LIGHT_GRAY_STAINED_GLASS_PANE
				&& event.getCurrentItem().getItemMeta().getLore()==null) return;
		if (inv.getHolder() == null) {
			event.setCancelled(true);
			if (event.getSlot() < 8) {
				event.getWhoClicked().openInventory(Shop.GetMarch(event.getSlot(), event.getWhoClicked().getName()));
				Bukkit.getLogger().info("click tab " + String.valueOf(event.getSlot()));
			} else if (event.getCurrentItem() != null) {
				if (event.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE
						|| event.getCurrentItem().getType() == Material.YELLOW_STAINED_GLASS_PANE
						|| event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE)
					return;
				String cost = event.getCurrentItem().getItemMeta().getLore().get(0).substring(6);
				Bukkit.getLogger().info("buy item " + event.getCurrentItem().getType().name() + " cost " + cost);
				Player player = (Player) event.getWhoClicked();
				if (event.getCurrentItem().getType() == Material.CHAINMAIL_BOOTS) {
					if (cost.split(" ")[1].charAt(0) == 'i' && player.getInventory().contains(Material.IRON_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_chain(player.getName(), player.getInventory(), Material.IRON_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'g' && player.getInventory().contains(Material.GOLD_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_chain(player.getName(), player.getInventory(), Material.GOLD_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'e'
							&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_chain(player.getName(), player.getInventory(), Material.EMERALD,
								Integer.parseInt(cost.split(" ")[0]));
				} else if (event.getCurrentItem().getType() == Material.IRON_BOOTS) {
					if (cost.split(" ")[1].charAt(0) == 'i' && player.getInventory().contains(Material.IRON_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_iron(player.getName(), player.getInventory(), Material.IRON_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'g' && player.getInventory().contains(Material.GOLD_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_iron(player.getName(), player.getInventory(), Material.GOLD_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'e'
							&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_iron(player.getName(), player.getInventory(), Material.EMERALD,
								Integer.parseInt(cost.split(" ")[0]));
				} else if (event.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
					if (cost.split(" ")[1].charAt(0) == 'i' && player.getInventory().contains(Material.IRON_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_diamond(player.getName(), player.getInventory(), Material.IRON_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'g' && player.getInventory().contains(Material.GOLD_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_diamond(player.getName(), player.getInventory(), Material.GOLD_INGOT,
								Integer.parseInt(cost.split(" ")[0]));
					if (cost.split(" ")[1].charAt(0) == 'e'
							&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_diamond(player.getName(), player.getInventory(), Material.EMERALD,
								Integer.parseInt(cost.split(" ")[0]));
				} else if (event.getCurrentItem().getType() == Material.WOODEN_AXE
						|| event.getCurrentItem().getType() == Material.STONE_AXE
						|| event.getCurrentItem().getType() == Material.IRON_AXE
						|| event.getCurrentItem().getType() == Material.DIAMOND_AXE) {
					if (cost.split(" ")[1].charAt(0) == 'i' && player.getInventory().contains(Material.IRON_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_axe(player.getName(), player.getInventory(), Material.IRON_INGOT,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					if (cost.split(" ")[1].charAt(0) == 'g' && player.getInventory().contains(Material.GOLD_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_axe(player.getName(), player.getInventory(), Material.GOLD_INGOT,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					if (cost.split(" ")[1].charAt(0) == 'e'
							&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_axe(player.getName(), player.getInventory(), Material.EMERALD,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					player.openInventory(
							Shop.GetMarch(event.getClickedInventory().first(Material.YELLOW_STAINED_GLASS_PANE) - 9,
									player.getName()));
				} else if (event.getCurrentItem().getType() == Material.WOODEN_PICKAXE
						|| event.getCurrentItem().getType() == Material.STONE_PICKAXE
						|| event.getCurrentItem().getType() == Material.GOLDEN_PICKAXE
						|| event.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
					if (cost.split(" ")[1].charAt(0) == 'i' && player.getInventory().contains(Material.IRON_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_pickaxe(player.getName(), player.getInventory(), Material.IRON_INGOT,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					if (cost.split(" ")[1].charAt(0) == 'g' && player.getInventory().contains(Material.GOLD_INGOT,
							Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_pickaxe(player.getName(), player.getInventory(), Material.GOLD_INGOT,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					if (cost.split(" ")[1].charAt(0) == 'e'
							&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0])))
						Buy.buy_pickaxe(player.getName(), player.getInventory(), Material.EMERALD,
								Integer.parseInt(cost.split(" ")[0]), event.getCurrentItem());
					player.openInventory(
							Shop.GetMarch(event.getClickedInventory().first(Material.YELLOW_STAINED_GLASS_PANE) - 9,
									player.getName()));
				} else if (cost.split(" ")[1].charAt(0) == 'i'
						&& player.getInventory().contains(Material.IRON_INGOT, Integer.parseInt(cost.split(" ")[0]))) {
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("you didn't have any empty backpack!");
						return;
					}
					ItemStack IS = event.getCurrentItem().clone();
					ItemMeta IM = IS.getItemMeta();
					IM.setLore(new ArrayList<String>());
					IS.setItemMeta(IM);
					player.getInventory().addItem(IS);
					Shop.remove(player.getInventory(), Material.IRON_INGOT, Integer.parseInt(cost.split(" ")[0]));
				} else if (cost.split(" ")[1].charAt(0) == 'g'
						&& player.getInventory().contains(Material.GOLD_INGOT, Integer.parseInt(cost.split(" ")[0]))) {
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("you didn't have any empty backpack!");
						return;
					}
					ItemStack IS = event.getCurrentItem().clone();
					ItemMeta IM = IS.getItemMeta();
					IM.setLore(new ArrayList<String>());
					IS.setItemMeta(IM);
					player.getInventory().addItem(IS);
					Shop.remove(player.getInventory(), Material.GOLD_INGOT, Integer.parseInt(cost.split(" ")[0]));
				} else if (cost.split(" ")[1].charAt(0) == 'e'
						&& player.getInventory().contains(Material.EMERALD, Integer.parseInt(cost.split(" ")[0]))) {
					if (player.getInventory().firstEmpty() == -1) {
						player.sendMessage("you didn't have any empty backpack!");
						return;
					}
					ItemStack IS = event.getCurrentItem().clone();
					ItemMeta IM = IS.getItemMeta();
					IM.setLore(new ArrayList<String>());
					IS.setItemMeta(IM);
					player.getInventory().addItem(IS);
					Shop.remove(player.getInventory(), Material.EMERALD, Integer.parseInt(cost.split(" ")[0]));
				}
			}
		}
	}

	@EventHandler
	void open_shop(PlayerInteractEntityEvent event) {
		if(!Bedwar.start) return;
		if (event.getRightClicked().getType() == EntityType.VILLAGER) {
			event.setCancelled(true);
			event.getPlayer().openInventory(Shop.GetMarch(0, event.getPlayer().getName()));
		}
	}
}
