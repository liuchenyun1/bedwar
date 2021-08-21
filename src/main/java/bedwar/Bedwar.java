package bedwar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Bed.Part;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Bedwar extends JavaPlugin {
	File file1 = new File(this.getDataFolder(), "cost.txt");
	File file2 = new File(this.getDataFolder(), "spawn.txt");
	File file3 = new File(this.getDataFolder(), "death.txt");
	File file4 = new File(this.getDataFolder(), "gen.txt");
	static public Map<String,Boolean> Invincible = new HashMap<String,Boolean>();
	private Boolean ForceReload = true;
	static public Map<Material, ArrayList<Integer>> cost = new HashMap<Material, ArrayList<Integer>>();
	static public Scoreboard board;
	static public ArrayList<Team> Teamlist = new ArrayList<Team>();
	static public ArrayList<Location> StartPoint = new ArrayList<Location>();
	static public ArrayList<Location> DeathPoint = new ArrayList<Location>();
	static public boolean start = false;
	static public int in_use = 0;
	static public Map<String, Integer> PlayerTeam = new HashMap<String, Integer>();
	static public ChatColor[] color = { ChatColor.LIGHT_PURPLE, ChatColor.GRAY, ChatColor.RED, ChatColor.BLUE,
			ChatColor.GREEN, ChatColor.YELLOW, ChatColor.AQUA, ChatColor.WHITE };
	static public Color[] Leathercolor = { Color.FUCHSIA, Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
			Color.AQUA, Color.WHITE };
	static public ArrayList<Material> BedType = new ArrayList<Material>();
	static public Objective health;
	static public Objective last;
	static public Map<String, Boolean> Bed = new HashMap<String, Boolean>();
	static public Map<String, Boolean> over = new HashMap<String, Boolean>();
	static public ArrayList<Material> CanBreak = new ArrayList<Material>();
	static public ArrayList<Location> PlaceBlock = new ArrayList<Location>();
	static public Map<String, Boolean> FireChargeCold = new HashMap<String, Boolean>();

	private String defaut1 = "world 107 112 -35 90 0\r\n" + 
			"world 107 112 35 90 0\r\n" + 
			"world 35 112 107 180 0\r\n" + 
			"world -35 112 107 180 0\r\n" + 
			"world -107 112 35 -90 0\r\n" + 
			"world -107 112 -35 -90 0\r\n" + 
			"world -35 112 -107 0 0\r\n" + 
			"world 35 112 -107 0 0";
	private String defaut2 = "world 0 200 0\r\n" + "world 0 200 0\r\n" + "world 0 200 0\r\n" + "world 0 200 0\r\n"
			+ "world 0 200 0\r\n" + "world 0 200 0\r\n" + "world 0 200 0\r\n" + "world 0 200 0\r\n";

	private void PlaceBed(int x1, int y1, int z1, int x2, int y2, int z2, BlockFace BF, Material material) {
		Block B;
		B = new Location(Bukkit.getWorld("world"), x1, y1, z1).getBlock();
		B.setBlockData(Bukkit.createBlockData(material, (data) -> {
			((Bed) data).setPart(Part.FOOT);
			((Bed) data).setFacing(BF);
		}));
		B = B.getRelative(BF.getOppositeFace());
		B = new Location(Bukkit.getWorld("world"), x2, y2, z2).getBlock();
		B.setBlockData(Bukkit.createBlockData(material, (data) -> {
			((Bed) data).setPart(Part.HEAD);
			((Bed) data).setFacing(BF);
		}));
		B = B.getRelative(BF.getOppositeFace());
	}

	private void ResetMap() {
		PlaceBlock.forEach(loc -> {
			loc.getBlock().setType(Material.AIR);
		});
		PlaceBlock.clear();
		PlaceBed(93, 111, -35, 94, 111, -35, BlockFace.EAST, Material.PINK_BED);
		PlaceBed(93, 111, 35, 94, 111, 35, BlockFace.EAST, Material.GRAY_BED);
		PlaceBed(35, 111, 93, 35, 111, 94, BlockFace.SOUTH, Material.RED_BED);
		PlaceBed(-35, 111, 93, -35, 111, 94, BlockFace.SOUTH, Material.BLUE_BED);
		PlaceBed(-93, 111, 35, -94, 111, 35, BlockFace.WEST, Material.LIME_BED);
		PlaceBed(-93, 111, -35, -94, 111, -35, BlockFace.WEST, Material.YELLOW_BED);
		PlaceBed(-35, 111, -93, -35, 111, -94, BlockFace.NORTH, Material.CYAN_BED);
		PlaceBed(35, 111, -93, 35, 111, -94, BlockFace.NORTH, Material.WHITE_BED);
//		System.out.println(((Chest)(new Location(Bukkit.getWorld("world"),104,112,-41).getBlock())).getBlockInventory());
//		new Location(Bukkit.getWorld("world"),104,112,-41).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),104,112,41).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),41,112,104).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),-41,112,104).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),-104,112,41).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),-104,112,-41).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),-41,112,-104).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
//		new Location(Bukkit.getWorld("world"),41,112,-104).getBlock().setBlockData(Bukkit.createBlockData(Material.CHEST));
		((Chest) (new Location(Bukkit.getWorld("world"), 104, 112, -41).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), 104, 112, 41).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), 41, 112, 104).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), -41, 112, 104).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), -104, 112, 41).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), -104, 112, -41).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), -41, 112, -104).getBlock().getState())).getBlockInventory()
				.clear();
		((Chest) (new Location(Bukkit.getWorld("world"), 41, 112, -104).getBlock().getState())).getBlockInventory()
				.clear();
	}

	private void init() {
		over.clear();
		Bed.clear();
		FireChargeCold.clear();
		ResetMap();
		Bukkit.getServer().getWorld("world").setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
		Bukkit.getServer().getWorld("world").setGameRule(GameRule.KEEP_INVENTORY, false);
		Shop.init();
		board.clearSlot(DisplaySlot.PLAYER_LIST);
		board.clearSlot(DisplaySlot.SIDEBAR);
		board.getTeams().forEach(team -> team.unregister());
		board.getObjectives().forEach(obj -> obj.unregister());
		Bed.clear();
		over.clear();
		PlayerTeam.clear();
		in_use = 0;
		Teamlist.clear();
		for (int i = 0; i < 8; i++) {
			String teamname = color[i].name().toLowerCase();
			if (teamname.contains("purple"))
				teamname = "pink";
			this.getLogger().info("Team " + teamname);
			this.getLogger().info(String.valueOf(Teamlist == null));
			Teamlist.add(board.registerNewTeam("Team " + teamname));
		}
		health = board.registerNewObjective("Health", "health", "Health");
		last = board.registerNewObjective("survive", "dummy", "Survive");
		Bukkit.getServer().getOnlinePlayers().forEach(player -> {
			player.getInventory().clear();
			String name = player.getName();
			if (!Shop.axe.containsKey(name))
				Shop.axe.put(name, 0);
			if (!Shop.pick_axe.containsKey(name))
				Shop.pick_axe.put(name, 0);
			if (!Shop.chain.containsKey(name))
				Shop.chain.put(name, false);
			if (!Shop.iron.containsKey(name))
				Shop.iron.put(name, false);
			if (!Shop.diamond.containsKey(name))
				Shop.diamond.put(name, false);
			if (!PlayerTeam.containsKey(name)) {
				in_use++;
				PlayerTeam.put(name, in_use - 1);
				Teamlist.get(in_use - 1).addEntry(player.getName());
				Teamlist.get(in_use - 1).setAllowFriendlyFire(false);
				String teamname = color[in_use - 1].name().toLowerCase();
				if (teamname.contains("purple"))
					teamname = "pink";
				Teamlist.get(in_use - 1).setColor(color[in_use - 1]);
				Teamlist.get(in_use - 1).setPrefix("Team " + teamname + " - ");
				player.teleport(StartPoint.get(in_use - 1));
				player.setBedSpawnLocation(StartPoint.get(in_use - 1), true);
				ItemStack IS = new ItemStack(Material.LEATHER_HELMET);
				LeatherArmorMeta LAM = (LeatherArmorMeta) IS.getItemMeta();
				LAM.setUnbreakable(true);
				LAM.setColor(Leathercolor[in_use - 1]);
				IS.setItemMeta(LAM);
				player.getInventory().setHelmet(IS);
				IS = new ItemStack(Material.LEATHER_CHESTPLATE);
				LAM = (LeatherArmorMeta) IS.getItemMeta();
				LAM.setUnbreakable(true);
				LAM.setColor(Leathercolor[in_use - 1]);
				IS.setItemMeta(LAM);
				player.getInventory().setChestplate(IS);
				player.getEnderChest().clear();
			}
			Invincible.put(player.getName(),true);
			FireChargeCold.put(player.getName(),false);
			new BukkitRunnable() {
				public void run() {
					Bedwar.Invincible.put(player.getName(),false);
				}
			}.runTaskLater(Bukkit.getPluginManager().getPlugin("bedwar"), 20 * 3);
			player.getEnderChest().clear();
			ItemStack IS=new ItemStack(Material.WOODEN_SWORD);
			ItemMeta IM=IS.getItemMeta();
			IM.setUnbreakable(true);;
			IS.setItemMeta(IM);
			player.getInventory().addItem(IS);
			player.setScoreboard(board);
			player.setHealth(20);
			player.setGameMode(GameMode.SURVIVAL);
			last.getScore(player.getName()).setScore(1);
			Bed.put(player.getName(), true);
			over.put(player.getName(), false);
			health.setDisplaySlot(DisplaySlot.PLAYER_LIST);
			last.setDisplaySlot(DisplaySlot.SIDEBAR);
			ResourcesGenerator.stopGen();
			Bukkit.getWorld("world").getEntities().forEach(entity -> {
				if (entity.getType() == EntityType.DROPPED_ITEM)
					entity.remove();
			});
			ResourcesGenerator.startGen();
		});
	}

	@Override
	public void onEnable() {
		board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
		getServer().getPluginManager().registerEvents(new Shop_Listener(), this);
		getServer().getPluginManager().registerEvents(new Battle_Listener(), this);
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		if (!file1.exists() || ForceReload) {
			try {
				file1.createNewFile();
			} catch (IOException E) {
				E.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(file1);
				fw.write(Shop.defaut);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!file2.exists() || ForceReload) {
			try {
				file2.createNewFile();
			} catch (IOException E) {
				E.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(file2);
				fw.write(defaut1);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!file3.exists() || ForceReload) {
			try {
				file3.createNewFile();
			} catch (IOException E) {
				E.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(file3);
				fw.write(defaut2);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!file4.exists() || ForceReload) {
			try {
				file4.createNewFile();
			} catch (IOException E) {
				E.printStackTrace();
			}
			try {
				FileWriter fw = new FileWriter(file4);
				fw.write(ResourcesGenerator.defaut);
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file1));
			String text = br.readLine();
			while (text != null) {
				String[] text_split = text.split(" ");
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				if (text_split[2].equalsIgnoreCase("i")) {
					tmp.add(Integer.parseInt(text_split[1]));
					tmp.add(-1);
					tmp.add(-1);
				} else if (text_split[2].equalsIgnoreCase("g")) {
					tmp.add(-1);
					tmp.add(Integer.parseInt(text_split[1]));
					tmp.add(-1);
				} else if (text_split[2].equalsIgnoreCase("e")) {
					tmp.add(-1);
					tmp.add(-1);
					tmp.add(Integer.parseInt(text_split[1]));
				}
				if (!cost.containsKey(Material.getMaterial(text_split[0])))
					cost.put(Material.getMaterial(text_split[0]), tmp);
				text = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file2));
			String text = br.readLine();
			while (text != null) {
				String[] text_split = text.split(" ");
				Location loc = new Location(this.getServer().getWorld(text_split[0]),
						Integer.parseInt(text_split[1]) + 0.5, Integer.parseInt(text_split[2]),
						Integer.parseInt(text_split[3]) + 0.5,Integer.parseInt(text_split[4]),Integer.parseInt(text_split[5]));
				StartPoint.add(loc);
				text = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file3));
			String text = br.readLine();
			while (text != null) {
				String[] text_split = text.split(" ");
				Location loc = new Location(this.getServer().getWorld(text_split[0]),
						Integer.parseInt(text_split[1]) + 0.5, Integer.parseInt(text_split[2]),
						Integer.parseInt(text_split[3]) + 0.5);
				DeathPoint.add(loc);
				text = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(file4));
			String text = br.readLine();
			while (text != null) {
				String[] text_split = text.split(" ");
				Location loc = new Location(Bukkit.getWorld(text_split[0]), Integer.parseInt(text_split[1]) + 0.5,
						Double.parseDouble(text_split[2]), Integer.parseInt(text_split[3]) + 0.5);
				if (text_split[4].equalsIgnoreCase("i")) {
					ResourcesGenerator.IronPoint.add(loc);
				} else if (text_split[4].equalsIgnoreCase("g")) {
					ResourcesGenerator.GoldPoint.add(loc);
				} else if (text_split[4].equalsIgnoreCase("e")) {
					ResourcesGenerator.EmeraldPoint.add(loc);
				} else if (text_split[4].equalsIgnoreCase("d")) {
					ResourcesGenerator.DiamondPoint.add(loc);
				}
				text = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(cost);
		System.out.println(StartPoint);
		System.out.println(DeathPoint);
		System.out.println(ResourcesGenerator.IronPoint);
		System.out.println(ResourcesGenerator.GoldPoint);
		System.out.println(ResourcesGenerator.EmeraldPoint);
		System.out.println(ResourcesGenerator.DiamondPoint);
		start = false;
		new BukkitRunnable() {
			public void run() {
				Bukkit.getServer().getOnlinePlayers().forEach(player -> {
					player.getInventory().remove(Material.BUCKET);
					player.getInventory().remove(Material.GLASS_BOTTLE);
					if (player.getLocation().getY() <= 10) {
						player.damage(1000);
						System.out.println(player.getName());
					}
					if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 16);
						Bukkit.getServer().getOnlinePlayers().forEach(other -> {
							if (PlayerTeam.get(player.getName()) != PlayerTeam.get(other.getName()))
								other.hidePlayer(Bukkit.getPluginManager().getPlugin("bedwar"), player);
							else
								other.showPlayer(Bukkit.getPluginManager().getPlugin("bedwar"), player);
						});
					} else {
						Bukkit.getServer().getOnlinePlayers().forEach(other -> {
							other.showPlayer(Bukkit.getPluginManager().getPlugin("bedwar"), player);
						});
					}
					player.setExhaustion(0);
				});
			}
		}.runTaskTimer(this, 0, 1);
		BedType.add(Material.PINK_BED);
		BedType.add(Material.GRAY_BED);
		BedType.add(Material.RED_BED);
		BedType.add(Material.BLUE_BED);
		BedType.add(Material.LIME_BED);
		BedType.add(Material.YELLOW_BED);
		BedType.add(Material.CYAN_BED);
		BedType.add(Material.WHITE_BED);
		CanBreak.addAll(BedType);
		CanBreak.add(Material.BLACK_WOOL);
		CanBreak.add(Material.SPRUCE_PLANKS);
		CanBreak.add(Material.END_STONE);
		CanBreak.add(Material.WHITE_STAINED_GLASS);
		CanBreak.add(Material.LADDER);
		CanBreak.add(Material.TERRACOTTA);
		CanBreak.add(Material.OBSIDIAN);
		CanBreak.add(Material.SPONGE);
		CanBreak.add(Material.FIRE);
	}

	@Override
	public void onDisable() {
		ResetMap();
		try {
			FileWriter fw = new FileWriter(file1);
			for (Map.Entry<Material, ArrayList<Integer>> entry : cost.entrySet()) {
				if (entry.getValue().size() < 3)
					continue;
				if (entry.getValue().get(0) != -1)
					fw.write(entry.getKey().name() + " " + String.valueOf(entry.getValue().get(0)) + " i\r\n");
				if (entry.getValue().get(1) != -1)
					fw.write(entry.getKey().name() + " " + String.valueOf(entry.getValue().get(1)) + " g\r\n");
				if (entry.getValue().get(2) != -1)
					fw.write(entry.getKey().name() + " " + String.valueOf(entry.getValue().get(2)) + " e\r\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(file2);
			StartPoint.forEach(pos -> {
				try {
					fw.write(pos.getWorld().getName() + " " + String.valueOf(pos.getBlockX()) + " "
							+ String.valueOf(pos.getBlockY()) + " " + String.valueOf(pos.getBlockZ()) + "\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(file3);
			DeathPoint.forEach(pos -> {
				try {
					fw.write(pos.getWorld().getName() + " " + String.valueOf(pos.getBlockX()) + " "
							+ String.valueOf(pos.getBlockY()) + " " + String.valueOf(pos.getBlockZ()) + "\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("open")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.openInventory(Shop.GetMarch(0, player.getName()));
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("init")) {
			if (sender.isOp()) {
				start = true;
				init();
			}
		}
		if (cmd.getName().equalsIgnoreCase("reset")) {
			if (sender.isOp()) {
				ResetMap();
			}
		}
		if (cmd.getName().equalsIgnoreCase("setCost")) {
			if (sender.isOp()) {
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				if (args[2].equalsIgnoreCase("i")) {
					tmp.add(Integer.parseInt(args[1]));
					tmp.add(-1);
					tmp.add(-1);
				} else if (args[2].equalsIgnoreCase("g")) {
					tmp.add(-1);
					tmp.add(Integer.parseInt(args[1]));
					tmp.add(-1);
				} else if (args[2].equalsIgnoreCase("e")) {
					tmp.add(-1);
					tmp.add(-1);
					tmp.add(Integer.parseInt(args[1]));
				}
				if (!cost.containsKey(Material.getMaterial(args[0])))
					cost.put(Material.getMaterial(args[0]), tmp);
				else
					cost.replace(Material.getMaterial(args[0]), tmp);
				Shop.init_shop();
				return true;
			}
		}
		return false;
	}
}
