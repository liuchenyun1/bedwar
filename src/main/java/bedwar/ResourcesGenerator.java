package bedwar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class ResourcesGenerator {
	private static int Iron = 1, Gold = 4, Emerald = 60, Diamond = 30;
	public static List<Location> IronPoint = new ArrayList<Location>();
	public static List<Location> GoldPoint = new ArrayList<Location>();
	public static List<Location> EmeraldPoint = new ArrayList<Location>();
	public static List<Location> DiamondPoint = new ArrayList<Location>();
	public static String defaut = "world 66 110.6 66 d\r\n" + "world -66 110.6 66 d\r\n" + "world 66 110.6 -66 d\r\n"
			+ "world -66 110.6 -66 d\r\n" + "world 0 109.6 28 e\r\n" + "world 0 109.6 -28 e\r\n"
			+ "world 28 109.6 0 e\r\n" + "world -28 109.6 0 e\r\n" + "world 111 111.6 35 g\r\n"
			+ "world -111 111.6 35 g\r\n" + "world 111 111.6 -35 g\r\n" + "world -111 111.6 -35 g\r\n"
			+ "world 35 111.6 111 g\r\n" + "world -35 111.6 111 g\r\n" + "world 35 111.6 -111 g\r\n"
			+ "world -35 111.6 -111 g\r\n" + "world 111 111.6 35 i\r\n" + "world -111 111.6 35 i\r\n"
			+ "world 111 111.6 -35 i\r\n" + "world -111 111.6 -35 i\r\n" + "world 35 111.6 111 i\r\n"
			+ "world -35 111.6 111 i\r\n" + "world 35 111.6 -111 i\r\n" + "world -35 111.6 -111 i\r\n";
	public static List<BukkitTask> GenTask=new ArrayList<BukkitTask>();

	private static void StartGenIron(Location loc) {
		BukkitTask task=new BukkitRunnable() {
			public void run() {
				int count = 0;
				Collection<Entity> entitys = loc.getWorld().getNearbyEntities(loc, 2, 2, 2);
				for (Entity entity : entitys)
					if (entity.getType() == EntityType.DROPPED_ITEM
							&& ((Item) (entity)).getItemStack().getType() == Material.IRON_INGOT)
						count += ((Item) (entity)).getItemStack().getAmount();
				if (count < 48) {
//					loc.getWorld().dropItem(loc, new ItemStack(Material.IRON_INGOT, 1));
					Item item=((Item)(loc.getWorld().spawnEntity(loc,EntityType.DROPPED_ITEM)));
					item.setItemStack(new ItemStack(Material.IRON_INGOT,1));
					item.setVelocity(new Vector(0,0,0));
				}
			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("bedwar"), 0, Iron * 20);
		GenTask.add(task);
	}

	private static void StartGenGold(Location loc) {
		BukkitTask task=new BukkitRunnable() {
			public void run() {
				int count = 0;
				Collection<Entity> entitys = loc.getWorld().getNearbyEntities(loc, 2, 2, 2);
				for (Entity entity : entitys)
					if (entity.getType() == EntityType.DROPPED_ITEM
							&& ((Item) (entity)).getItemStack().getType() == Material.GOLD_INGOT)
						count += ((Item) (entity)).getItemStack().getAmount();
				if (count < 12) {
//					loc.getWorld().dropItem(loc, new ItemStack(Material.GOLD_INGOT, 1));
					Item item=((Item)(loc.getWorld().spawnEntity(loc,EntityType.DROPPED_ITEM)));
					item.setItemStack(new ItemStack(Material.GOLD_INGOT,1));
					item.setVelocity(new Vector(0,0,0));
				}
			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("bedwar"), 0, Gold * 20);
		GenTask.add(task);
	}

	private static void StartGenEmerald(Location loc) {
		BukkitTask task=new BukkitRunnable() {
			public void run() {
				int count = 0;
				Collection<Entity> entitys = loc.getWorld().getNearbyEntities(loc, 2, 2, 2);
				for (Entity entity : entitys)
					if (entity.getType() == EntityType.DROPPED_ITEM
							&& ((Item) (entity)).getItemStack().getType() == Material.EMERALD)
						count += ((Item) (entity)).getItemStack().getAmount();
				if (count < 2) {
//					loc.getWorld().dropItem(loc, new ItemStack(Material.EMERALD, 1));
					Item item=((Item)(loc.getWorld().spawnEntity(loc,EntityType.DROPPED_ITEM)));
					item.setItemStack(new ItemStack(Material.EMERALD,1));
					item.setVelocity(new Vector(0,0,0));
				}
			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("bedwar"), 0, Emerald * 20);
		GenTask.add(task);
	}

	private static void StartGenDiamond(Location loc) {
		BukkitTask task=new BukkitRunnable() {
			public void run() {
				int count = 0;
				Collection<Entity> entitys = loc.getWorld().getNearbyEntities(loc, 2, 2, 2);
				for (Entity entity : entitys)
					if (entity.getType() == EntityType.DROPPED_ITEM
							&& ((Item) (entity)).getItemStack().getType() == Material.DIAMOND)
						count += ((Item) (entity)).getItemStack().getAmount();
				if (count < 4) {
//					loc.getWorld().dropItem(loc, new ItemStack(Material.DIAMOND, 1));
					Item item=((Item)(loc.getWorld().spawnEntity(loc,EntityType.DROPPED_ITEM)));
					item.setItemStack(new ItemStack(Material.DIAMOND,1));
					item.setVelocity(new Vector(0,0,0));
				}
			}
		}.runTaskTimer(Bukkit.getPluginManager().getPlugin("bedwar"), 0, Diamond * 20);
		GenTask.add(task);
	}
	public static void startGen() {
		IronPoint.forEach(loc -> StartGenIron(loc));
		GoldPoint.forEach(loc -> StartGenGold(loc));
		EmeraldPoint.forEach(loc -> StartGenEmerald(loc));
		DiamondPoint.forEach(loc -> StartGenDiamond(loc));
	}

	public static void stopGen() {
		GenTask.forEach(task -> task.cancel());
	}
}
