package bedwar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Shop {
	static public List<Inventory> shop = new ArrayList<Inventory>();
	static public Map<String, Boolean> chain = new HashMap<String, Boolean>();
	static public Map<String, Boolean> iron = new HashMap<String, Boolean>();
	static public Map<String, Boolean> diamond = new HashMap<String, Boolean>();
	static public Map<String, Integer> axe = new HashMap<String, Integer>();
	static public Map<String, Integer> pick_axe = new HashMap<String, Integer>();

	static public String defaut = "WHITE_STAINED_GLASS 12 i\r\n" + "TNT 4 g\r\n" + "STONE_AXE 10 i\r\n"
			+ "DIAMOND_PICKAXE 6 g\r\n" + "GOLDEN_APPLE 3 g\r\n" + "IRON_BOOTS 12 g\r\n" + "WATER_BUCKET 3 g\r\n"
			+ "STONE_PICKAXE 10 i\r\n" + "GOLDEN_PICKAXE 3 g\r\n" + "BLACK_WOOL 4 i\r\n"
			+ "GUARDIAN_SPAWN_EGG 120 i\r\n" + "WOODEN_PICKAXE 10 i\r\n" + "CHAINMAIL_BOOTS 40 i\r\n"
			+ "DIAMOND_BOOTS 6 e\r\n" + "LADDER 4 i\r\n" + "IRON_SWORD 7 g\r\n" + "CHEST 24 i\r\n" + "EGG 2 e\r\n"
			+ "DIAMOND_AXE 6 g\r\n" + "DIAMOND_SWORD 4 e\r\n" + "MILK_BUCKET 4 g\r\n" + "ARROW 2 g\r\n"
			+ "SNOWBALL 40 i\r\n" + "ENDER_PEARL 4 e\r\n" + "WOODEN_AXE 10 i\r\n" + "POLAR_BEAR_SPAWN_EGG 120 i\r\n"
			+ "FIRE_CHARGE 40 i\r\n" + "SPRUCE_PLANKS 4 g\r\n" + "BOW 12 g\r\n" + "SPONGE 3 g\r\n" + "TERRACOTTA 12 i\r\n"
			+ "OBSIDIAN 4 e\r\n" + "IRON_AXE 3 g\r\n" + "STONE_SWORD 10 i\r\n" + "END_STONE 24 i\r\n"
			+ "SHEARS 20 i\r\n";

	public static void remove(Inventory inv, Material mat, int count) {
		int now = count;
		while (now > 0) {
			int pos = inv.first(mat);
			ItemStack IS = inv.getItem(pos);
			if (IS.getAmount() <= now) {
				now -= IS.getAmount();
				inv.clear(pos);
			} else {
				inv.setItem(pos, new ItemStack(mat, IS.getAmount() - now));
				now = 0;
			}
		}
	}

	private static Inventory get_tab(int now) {
		Inventory inv = Bukkit.createInventory(null, 54, "shop");
		ItemStack IS;
		ItemMeta IM;
		List<String> lore = new ArrayList<String>();
		for (int i = 0; i < 54; i++) {
			IS=new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
			IM=IS.getItemMeta();
			IM.setDisplayName("");
			IS.setItemMeta(IM);
			inv.setItem(i, IS);
		}
		IS = new ItemStack(Material.NETHER_STAR, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Main page");
		lore.clear();
		lore.add("Main page");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(0, IS);
		IS = new ItemStack(Material.TERRACOTTA, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Blocks");
		lore.clear();
		lore.add("Blocks");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(1, IS);
		IS = new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Armors");
		lore.clear();
		lore.add("Armors");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(2, IS);
		IS = new ItemStack(Material.GOLDEN_SWORD, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Weapons");
		lore.clear();
		lore.add("Weapons");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(3, IS);
		IS = new ItemStack(Material.STONE_PICKAXE, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Tools");
		lore.clear();
		lore.add("Tools");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(4, IS);
		IS = new ItemStack(Material.BOW, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Bow & Arrow");
		lore.clear();
		lore.add("Bow & Arrow");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(5, IS);
		IS = new ItemStack(Material.BREWING_STAND, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Potions");
		lore.clear();
		lore.add("Potion");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(6, IS);
		IS = new ItemStack(Material.TNT, 1);
		IM = IS.getItemMeta();
		IM.setDisplayName("Props");
		lore.clear();
		lore.add("Props");
		IM.setLore(lore);
		IS.setItemMeta(IM);
		inv.setItem(7, IS);
		for (int i = 9; i < 18; i++)
			inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
		inv.setItem(now + 9 - 1, new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
		return inv;
	}
	
	private static ItemStack get_item(Material mat, int cnt) {
		ItemStack IS = new ItemStack(mat, cnt);
		ItemMeta IM = IS.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.clear();
		if(IS.getType().getMaxDurability()>0) IM.setUnbreakable(true);
		if (Bedwar.cost.containsKey(mat)) {
			if (Bedwar.cost.get(mat).get(0) != -1)
				lore.add("Cost: " + String.valueOf(Bedwar.cost.get(mat).get(0)) + " iron");
			if (Bedwar.cost.get(mat).get(1) != -1)
				lore.add("Cost: " + String.valueOf(Bedwar.cost.get(mat).get(1)) + " gold");
			if (Bedwar.cost.get(mat).get(2) != -1)
				lore.add("Cost: " + String.valueOf(Bedwar.cost.get(mat).get(2)) + " emerald");
		}
		IM.setLore(lore);
		IS.setItemMeta(IM);
		return IS;
	}

	public static void init_shop() {
		ItemStack IS;
		PotionMeta PM;
		List<String> lore = new ArrayList<String>();
		lore.clear();
		shop.clear();
		Inventory tab1 = get_tab(1);
		tab1.setItem(19, get_item(Material.BLACK_WOOL, 16));
		tab1.setItem(20, get_item(Material.STONE_SWORD, 1));
		tab1.setItem(21, get_item(Material.CHAINMAIL_BOOTS, 1));
		tab1.setItem(22, get_item(Material.WOODEN_PICKAXE, 1));
		tab1.setItem(23, get_item(Material.GOLDEN_APPLE, 1));
		tab1.setItem(24, get_item(Material.BOW, 1));
		tab1.setItem(28, get_item(Material.SPRUCE_PLANKS, 16));
		tab1.setItem(29, get_item(Material.IRON_SWORD, 1));
		tab1.setItem(30, get_item(Material.IRON_BOOTS, 1));
		tab1.setItem(31, get_item(Material.WOODEN_AXE, 1));
		tab1.setItem(32, get_item(Material.FIRE_CHARGE, 1));
		tab1.setItem(33, get_item(Material.ARROW, 8));
		tab1.setItem(37, get_item(Material.END_STONE, 12));
		tab1.setItem(38, get_item(Material.DIAMOND_SWORD, 1));
		tab1.setItem(39, get_item(Material.DIAMOND_BOOTS, 1));
		tab1.setItem(40, get_item(Material.SHEARS, 1));
		tab1.setItem(41, get_item(Material.TNT, 1));
		tab1.setItem(42, get_item(Material.ENDER_PEARL, 1));
		//
		Inventory tab2 = get_tab(2);
		tab2.setItem(19, get_item(Material.BLACK_WOOL, 16));
		tab2.setItem(20, get_item(Material.TERRACOTTA, 16));
		tab2.setItem(21, get_item(Material.WHITE_STAINED_GLASS, 4));
		tab2.setItem(22, get_item(Material.END_STONE, 12));
		tab2.setItem(23, get_item(Material.LADDER, 16));
		tab2.setItem(24, get_item(Material.SPRUCE_PLANKS, 16));
		tab2.setItem(25, get_item(Material.OBSIDIAN, 4));
		//
		Inventory tab3 = get_tab(3);
		tab3.setItem(19, get_item(Material.CHAINMAIL_BOOTS, 1));
		tab3.setItem(20, get_item(Material.IRON_BOOTS, 1));
		tab3.setItem(21, get_item(Material.DIAMOND_BOOTS, 1));
		//
		Inventory tab4 = get_tab(4);
		tab4.setItem(19, get_item(Material.STONE_SWORD, 1));
		tab4.setItem(20, get_item(Material.IRON_SWORD, 1));
		tab4.setItem(21, get_item(Material.DIAMOND_SWORD, 1));
		//
		Inventory tab5 = get_tab(5);
		tab5.setItem(19, get_item(Material.SHEARS, 1));
		tab5.setItem(20, get_item(Material.WOODEN_PICKAXE, 1));
		tab5.setItem(21, get_item(Material.WOODEN_AXE, 1));
		//
		Inventory tab6 = get_tab(6);
		tab6.setItem(19, get_item(Material.ARROW, 8));
		tab6.setItem(20, get_item(Material.BOW, 1));
		//
		Inventory tab7 = get_tab(7);
		//
		Inventory tab8 = get_tab(8);
		tab8.setItem(19, get_item(Material.GOLDEN_APPLE, 1));
		tab8.setItem(20, get_item(Material.SNOWBALL, 1));
		tab8.setItem(21, get_item(Material.POLAR_BEAR_SPAWN_EGG, 1));
		tab8.setItem(22, get_item(Material.FIRE_CHARGE, 1));
		tab8.setItem(23, get_item(Material.TNT, 1));
		tab8.setItem(24, get_item(Material.ENDER_PEARL, 1));
		tab8.setItem(25, get_item(Material.WATER_BUCKET, 1));
		tab8.setItem(28, get_item(Material.EGG, 1));
		tab8.setItem(29, get_item(Material.MILK_BUCKET, 1));
		tab8.setItem(30, get_item(Material.SPONGE, 4));
//		tab8.setItem(30, get_item(Material.CHEST, 1));
		//
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.INVISIBILITY));
//		PM.removeCustomEffect(PotionEffectType.INVISIBILITY);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 30, 0), true);
		lore.add("Cost: 2 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab7.setItem(19, IS);
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.JUMP));
//		PM.removeCustomEffect(PotionEffectType.JUMP);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 45, 4), true);
		lore.add("Cost: 1 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab7.setItem(20, IS);
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.SPEED));
//		PM.removeCustomEffect(PotionEffectType.SPEED);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 45, 1), true);
		lore.add("Cost: 1 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab7.setItem(21, IS);
		//
		IS = get_item(Material.STICK, 1);
		ItemMeta IM = IS.getItemMeta();
		IM.setDisplayName("Knockback Stick");
		lore.add("Cost: 5 gold");
		IM.setLore(lore);
		lore.clear();
		IS.setItemMeta(IM);
		IS.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		tab4.setItem(22, IS);
		//
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.SPEED));
//		PM.removeCustomEffect(PotionEffectType.SPEED);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 45, 1), true);
		lore.add("Cost: 1 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab1.setItem(25, IS);
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.JUMP));
//		PM.removeCustomEffect(PotionEffectType.JUMP);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 45, 4), true);
		lore.add("Cost: 1 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab1.setItem(34, IS);
		IS = get_item(Material.POTION, 1);
		PM = (PotionMeta) IS.getItemMeta();
//		PM.setBasePotionData(new PotionData(PotionType.INVISIBILITY));
//		PM.removeCustomEffect(PotionEffectType.INVISIBILITY);
		PM.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 30, 0), true);
		lore.add("Cost: 2 emerald");
		PM.setLore(lore);
		lore.clear();
		IS.setItemMeta(PM);
		tab1.setItem(43, IS);
		//
		IS = get_item(Material.BOW, 1);
		IS.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		IM = IS.getItemMeta();
		lore.add("Cost: 24 gold");
		IM.setLore(lore);
		lore.clear();
		IS.setItemMeta(IM);
		tab6.setItem(21, IS);
		IS = get_item(Material.BOW, 1);
		IS.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		IS.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		IM = IS.getItemMeta();
		lore.add("Cost: 6 emerald");
		IM.setLore(lore);
		lore.clear();
		IS.setItemMeta(IM);
		tab6.setItem(22, IS);
		//
		shop.add(tab1);
		shop.add(tab2);
		shop.add(tab3);
		shop.add(tab4);
		shop.add(tab5);
		shop.add(tab6);
		shop.add(tab7);
		shop.add(tab8);
	}

	public static void init() {
		init_shop();
		chain.clear();
		iron.clear();
		diamond.clear();
		axe.clear();
		pick_axe.clear();
	}

	public static Inventory GetMarch(int page, String name) {
		Inventory inv = Bukkit.createInventory(null, 54);
		inv.setContents(shop.get(page).getContents());
		if (inv.contains(Material.WOODEN_AXE)) {
			int x = inv.first(Material.WOODEN_AXE);
			if (!axe.containsKey(name))
				axe.put(name, 0);
			if (axe.get(name) == 0) {
				ItemStack IS = get_item(Material.WOODEN_AXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				inv.setItem(x, IS);
			} else if (axe.get(name) == 1) {
				ItemStack IS = get_item(Material.STONE_AXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				inv.setItem(x, IS);
			} else if (axe.get(name) == 2) {
				ItemStack IS = get_item(Material.IRON_AXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
				inv.setItem(x, IS);
			} else if (axe.get(name) == 3) {
				ItemStack IS = get_item(Material.DIAMOND_AXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
				inv.setItem(x, IS);
			} else
				inv.setItem(x, new ItemStack(Material.AIR));
		}
		if (inv.contains(Material.WOODEN_PICKAXE)) {
			int x = inv.first(Material.WOODEN_PICKAXE);
			if (!pick_axe.containsKey(name))
				pick_axe.put(name, 0);
			if (pick_axe.get(name) == 0) {
				ItemStack IS = get_item(Material.WOODEN_PICKAXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
				inv.setItem(x, IS);
			} else if (pick_axe.get(name) == 1) {
				ItemStack IS = get_item(Material.STONE_PICKAXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 2);
				inv.setItem(x, IS);
			} else if (pick_axe.get(name) == 2) {
				ItemStack IS = get_item(Material.GOLDEN_PICKAXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
				IS.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
				inv.setItem(x, IS);
			} else if (pick_axe.get(name) == 3) {
				ItemStack IS = get_item(Material.DIAMOND_PICKAXE, 1);
				IS.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
				inv.setItem(x, IS);
			} else
				inv.setItem(x, new ItemStack(Material.AIR));
		}
		return inv;
	}
}
