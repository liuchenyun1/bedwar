package bedwar;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Battle_Listener implements Listener {
	@EventHandler
	void death(EntityDeathEvent event) {
		if (!Bedwar.start)
			return;
		if (event.getEntityType() == EntityType.PLAYER) {
			List<ItemStack> remove = new ArrayList<ItemStack>();
			event.getDrops().forEach(item -> {
				if (item.getType() == Material.WOODEN_AXE)
					remove.add(item);
				if (item.getType() == Material.STONE_AXE)
					remove.add(item);
				if (item.getType() == Material.IRON_AXE)
					remove.add(item);
				if (item.getType() == Material.DIAMOND_AXE)
					remove.add(item);
				if (item.getType() == Material.WOODEN_PICKAXE)
					remove.add(item);
				if (item.getType() == Material.STONE_PICKAXE)
					remove.add(item);
				if (item.getType() == Material.GOLDEN_PICKAXE)
					remove.add(item);
				if (item.getType() == Material.DIAMOND_PICKAXE)
					remove.add(item);
				if (item.getType() == Material.CHAINMAIL_LEGGINGS)
					remove.add(item);
				if (item.getType() == Material.CHAINMAIL_BOOTS)
					remove.add(item);
				if (item.getType() == Material.IRON_LEGGINGS)
					remove.add(item);
				if (item.getType() == Material.IRON_BOOTS)
					remove.add(item);
				if (item.getType() == Material.DIAMOND_LEGGINGS)
					remove.add(item);
				if (item.getType() == Material.DIAMOND_BOOTS)
					remove.add(item);
				if (item.getType() == Material.LEATHER_HELMET)
					remove.add(item);
				if (item.getType() == Material.LEATHER_CHESTPLATE)
					remove.add(item);
				if (item.getType() == Material.WOODEN_SWORD)
					remove.add(item);
			});
			event.getDrops().removeAll(remove);
			if (event.getEntity().getKiller() != null)
				event.getDrops().forEach(item -> event.getEntity().getKiller().getInventory().addItem(item));
			event.getDrops().clear();
			String name = event.getEntity().getName();
			if (!Shop.axe.containsKey(name))
				Shop.axe.put(name, 0);
			if (Shop.axe.get(name) > 0)
				Shop.axe.replace(name, Shop.axe.get(name) - 1);
			if (!Shop.pick_axe.containsKey(name))
				Shop.pick_axe.put(name, 0);
			if (Shop.pick_axe.get(name) > 0)
				Shop.pick_axe.replace(name, Shop.pick_axe.get(name) - 1);
			if (!Bedwar.Bed.get(name)) {
				Bedwar.over.put(name, true);
				Bedwar.last.getScore(name).setScore(0);
			}
		}
	}

	@EventHandler
	void respawn(PlayerRespawnEvent event) {
		if (!Bedwar.start)
			return;
		String name = event.getPlayer().getName();
		event.getPlayer().setGameMode(GameMode.SPECTATOR);
		if (Bedwar.over.get(name)) {
			event.getPlayer().getEnderChest().forEach(item->{
				Bedwar.StartPoint.get(Bedwar.PlayerTeam.get(name)).getWorld().dropItem(Bedwar.StartPoint.get(Bedwar.PlayerTeam.get(name)),item);
			});
			event.getPlayer().getEnderChest().clear();
			Bukkit.broadcastMessage("Contents of "+name+"'s Ender Chest have been dropped into their fountain");
			return;
		}
		PlayerInventory inv = event.getPlayer().getInventory();
		new BukkitRunnable() {
			public void run() {
				event.getPlayer().setGameMode(GameMode.SURVIVAL);
				event.getPlayer().teleport(Bedwar.StartPoint.get(Bedwar.PlayerTeam.get(event.getPlayer().getName())));
				Bukkit.getLogger().info(String.valueOf(Shop.chain.get(name)));
				Bukkit.getLogger().info(String.valueOf(Shop.iron.get(name)));
				Bukkit.getLogger().info(String.valueOf(Shop.diamond.get(name)));
				Bukkit.getLogger().info(String.valueOf(Shop.axe.get(name)));
				Bukkit.getLogger().info(String.valueOf(Shop.pick_axe.get(name)));
				if (Shop.chain.get(name)) {
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
				}
				if (Shop.iron.get(name)) {
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
				}
				if (Shop.diamond.get(name)) {
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
				}

				if (Shop.axe.get(name) == 1) {
					ItemStack IS = new ItemStack(Material.WOODEN_AXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
					inv.addItem(IS);
				} else if (Shop.axe.get(name) == 2) {
					ItemStack IS = new ItemStack(Material.STONE_AXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
					inv.addItem(IS);
				} else if (Shop.axe.get(name) == 3) {
					ItemStack IS = new ItemStack(Material.IRON_AXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
					inv.addItem(IS);
				} else if (Shop.axe.get(name) == 4) {
					ItemStack IS = new ItemStack(Material.DIAMOND_AXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
					inv.addItem(IS);
				}

				if (Shop.pick_axe.get(name) == 1) {
					ItemStack IS = new ItemStack(Material.WOODEN_PICKAXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
					inv.addItem(IS);
				} else if (Shop.pick_axe.get(name) == 2) {
					ItemStack IS = new ItemStack(Material.STONE_PICKAXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
					inv.addItem(IS);
				} else if (Shop.pick_axe.get(name) == 3) {
					ItemStack IS = new ItemStack(Material.GOLDEN_PICKAXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
					IS.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
					inv.addItem(IS);
				} else if (Shop.pick_axe.get(name) == 4) {
					ItemStack IS = new ItemStack(Material.DIAMOND_PICKAXE, 1);
					ItemMeta IM = IS.getItemMeta();
					IM.setUnbreakable(true);
					IS.setItemMeta(IM);
					IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
					inv.addItem(IS);
				}
				ItemStack IS=new ItemStack(Material.WOODEN_SWORD);
				ItemMeta IM=IS.getItemMeta();
				IM.setUnbreakable(true);;
				IS.setItemMeta(IM);
				inv.addItem(IS);
				IS = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta LAM = (LeatherArmorMeta) IS.getItemMeta();
				LAM.setColor(Bedwar.Leathercolor[Bedwar.PlayerTeam.get(event.getPlayer().getName())]);
				LAM.setUnbreakable(true);
				IS.setItemMeta(LAM);
				inv.setHelmet(IS);
				IS = new ItemStack(Material.LEATHER_CHESTPLATE);
				LAM = (LeatherArmorMeta) IS.getItemMeta();
				LAM.setColor(Bedwar.Leathercolor[Bedwar.PlayerTeam.get(event.getPlayer().getName())]);
				LAM.setUnbreakable(true);
				IS.setItemMeta(LAM);
				inv.setChestplate(IS);
				Bedwar.Invincible.put(event.getPlayer().getName(), true);
				new BukkitRunnable() {
					public void run() {
						Bedwar.Invincible.put(event.getPlayer().getName(), false);
					}
				}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 3);
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 5);
		event.getPlayer().sendMessage("5");
		new BukkitRunnable() {
			public void run() {
				event.getPlayer().sendMessage("4");
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 1);
		new BukkitRunnable() {
			public void run() {
				event.getPlayer().sendMessage("3");
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 2);
		new BukkitRunnable() {
			public void run() {
				event.getPlayer().sendMessage("2");
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 3);
		new BukkitRunnable() {
			public void run() {
				event.getPlayer().sendMessage("1");
			}
		}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 4);
		event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.5, 200, 0.5));
	}

	@EventHandler
	void drop(PlayerDropItemEvent event) {
		if (!Bedwar.start)
			return;
		Item item = event.getItemDrop();
		if (item.getItemStack().getType() == Material.WOODEN_AXE || item.getItemStack().getType() == Material.STONE_AXE
				|| item.getItemStack().getType() == Material.IRON_AXE
				|| item.getItemStack().getType() == Material.DIAMOND_AXE
				|| item.getItemStack().getType() == Material.WOODEN_PICKAXE
				|| item.getItemStack().getType() == Material.STONE_PICKAXE
				|| item.getItemStack().getType() == Material.GOLDEN_PICKAXE
				|| item.getItemStack().getType() == Material.DIAMOND_PICKAXE
				|| item.getItemStack().getType() == Material.CHAINMAIL_LEGGINGS
				|| item.getItemStack().getType() == Material.CHAINMAIL_BOOTS
				|| item.getItemStack().getType() == Material.IRON_LEGGINGS
				|| item.getItemStack().getType() == Material.IRON_BOOTS
				|| item.getItemStack().getType() == Material.DIAMOND_LEGGINGS
				|| item.getItemStack().getType() == Material.DIAMOND_BOOTS
				|| item.getItemStack().getType() == Material.LEATHER_HELMET
				|| item.getItemStack().getType() == Material.LEATHER_CHESTPLATE) {
			event.getPlayer().sendMessage("You can't drop that!");
			event.setCancelled(true);
		}
	}

	@EventHandler
	void click(InventoryClickEvent event) {
		if (!Bedwar.start)
			return;
		if (event.getInventory().getType() == InventoryType.PLAYER && event.getSlotType() == SlotType.ARMOR) {
			event.setCancelled(true);
			event.getWhoClicked().sendMessage("You can't drop that!");
		}
	}

	@EventHandler
	void tnt(BlockPlaceEvent event) {
		if (!Bedwar.start)
			return;
		if (event.getBlock().getType() == Material.TNT) {
			event.getBlock().setType(Material.AIR);
			event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation().add(0, 1, 0), EntityType.PRIMED_TNT);
		}
	}

	@EventHandler
	void explode(EntityExplodeEvent event) {
		if (!Bedwar.start)
			return;
		ArrayList<Block> qwq = new ArrayList<Block>();
		event.blockList().forEach(block -> {
			if (block.getType() == Material.WHITE_STAINED_GLASS || !Bedwar.CanBreak.contains(block.getType())
					|| Bedwar.BedType.contains(block.getType()))
				qwq.add(block);
		});
		event.blockList().removeAll(qwq);
	}

	@EventHandler
	void Break(BlockBreakEvent event) {
		if (!Bedwar.CanBreak.contains(event.getBlock().getType())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("Do not break the map!!!");
			return;
		}
		if (!Bedwar.start)
			return;
		if (Bedwar.BedType.contains(event.getBlock().getType())) {
			int index = Bedwar.BedType.indexOf(event.getBlock().getType());
			if (index == Bedwar.PlayerTeam.get(event.getPlayer().getName())) {
				event.getPlayer().sendMessage("You can't break your own bed!");
				event.setCancelled(true);
				return;
			}
			Bedwar.Teamlist.get(index).getEntries().forEach(player -> {
				Bedwar.Bed.put(player, false);
			});
			Bukkit.getServer().getOnlinePlayers().forEach(player -> {
				String teamname = Bedwar.color[index].name().toLowerCase();
				if (teamname.contains("purple"))
					teamname = "pink";
				player.sendMessage("Bed for Team " + teamname + " has been destroy");
			});
			event.setDropItems(false);
		}
	}

	@EventHandler
	void damage(EntityDamageByEntityEvent event) {
		if (!Bedwar.start)
			return;
		if (event.getDamager().getType() == EntityType.PLAYER && event.getEntity().getType() == EntityType.PLAYER)
			((Player) (event.getEntity())).removePotionEffect(PotionEffectType.INVISIBILITY);
		if (event.getEntity().getType() == EntityType.PLAYER && Bedwar.Invincible.get(event.getEntity().getName()))
			event.setCancelled(true);
	}

	@EventHandler
	void place(BlockPlaceEvent event) {
		Bedwar.PlaceBlock.add(event.getBlock().getLocation());
	}

	@EventHandler
	public void onRightClickFireBall(PlayerInteractEvent e) {
		if (!Bedwar.start)
			return;
		try {
			if(Bedwar.FireChargeCold.get(e.getPlayer().getName())) {
				e.getPlayer().sendMessage("You have to wait 3 seconds after you use a fireball");
				e.setCancelled(true);
				return;
			}
			if (e.getAction() == Action.RIGHT_CLICK_AIR
					&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.FIRE_CHARGE) {
				Bukkit.getLogger().info(String.valueOf("fireball!"));
				Vector direction = e.getPlayer().getEyeLocation().getDirection();
				Projectile projectile = (Projectile) e.getPlayer().getWorld().spawn(
						e.getPlayer().getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()),
						Fireball.class);
				projectile.setShooter((LivingEntity) e.getPlayer());
				projectile.setVelocity(direction);
				if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
					e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.FIRE_CHARGE,
							e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1));
				} else {
					e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				}
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 1));
				Bedwar.FireChargeCold.put(e.getPlayer().getName(),true);
				new BukkitRunnable() {
					public void run() {
						Bedwar.FireChargeCold.put(e.getPlayer().getName(),false);
					}
				}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"),3*20);
			}
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK
					&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.FIRE_CHARGE) {
				Bukkit.getLogger().info(String.valueOf("fireball!"));
				Vector direction = e.getPlayer().getEyeLocation().getDirection();
				Projectile projectile = (Projectile) e.getPlayer().getWorld().spawn(
						e.getPlayer().getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()),
						Fireball.class);
				projectile.setShooter((LivingEntity) e.getPlayer());
				projectile.setVelocity(direction);
				if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) {
					e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.FIRE_CHARGE,
							e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1));
				} else {
					e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				}
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 1));
				Bedwar.FireChargeCold.put(e.getPlayer().getName(),true);
				new BukkitRunnable() {
					public void run() {
						Bedwar.FireChargeCold.put(e.getPlayer().getName(),false);
					}
				}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"),3*20);
				e.setCancelled(true);
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}
