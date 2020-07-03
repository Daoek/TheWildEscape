package teamdjg.wildescape.carepackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import teamdjg.wildescape.main.Main;

public class CarePackageManager {

	Main main;
	public List<CarePackageProfile> carePackageProfiles = new ArrayList<>();
	public HashMap<Location, Material> chestLocations = new HashMap<Location, Material>(); 
	
	public CarePackageManager(Main main)
	{
		this.main = main;
		carePackageProfiles = new ArrayList<>();
	}
	
	public void saveCarepackages(Location location, Material material)
	{
		if(location == null || material == null)
		{
			return;
		}
		
		chestLocations.put(location, material);
		
		String configLocation = location.getWorld().getName() + "/" + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ(); 
		
		main.getConfig().set("carepackages." + configLocation, material.toString());
		main.saveConfig();
	}
	
	public void loadCarepackagesFromConfig()
	{
		if(main.getConfig().contains("carepackages"))
		{
			Set<String> locationsConfig = main.getConfig().getConfigurationSection("carepackages").getKeys(false);
			
			for(String currentLocationConfig: locationsConfig)
			{
				String[] location = currentLocationConfig.split("/");
				Location blockLocation = new Location(main.getServer().getWorld(location[0]),Integer.parseInt(location[1]),Integer.parseInt(location[2]),Integer.parseInt(location[3]));
				Material blockMaterial = Material.valueOf(main.getConfig().getString("carepackages." + currentLocationConfig));
				chestLocations.put(blockLocation, blockMaterial);
			}
		}
	}
	
	public void removeAllCarepackages()
	{
		Set<Location> locations = chestLocations.keySet();
		
		for(Location currentLocation : locations)
		{
			Block block = currentLocation.getBlock();
			
			if(block.getType() == Material.CHEST)
			{
				Chest chest = (Chest) block.getState();
				chest.getBlockInventory().clear();
			}
			
			block.setType(chestLocations.get(currentLocation));
		}
		
		locations.clear();
		chestLocations.clear();
		
		main.getConfig().set("carepackages", null);
		main.saveConfig();
	}
	
	public CarePackageProfile getCarePackageProfileByName(String profileName)
	{
		if(carePackageProfiles.isEmpty() == false)
		{
			for(CarePackageProfile currentProfile : carePackageProfiles)
			{
				if(currentProfile.name.equals(profileName))
				{
					return currentProfile;
				}
			}
		}
		
		return null;
	}
	
	public Material setBlockToACarePackageByProfileName(String profileName, Location blockLocation)
	{
		CarePackageProfile profile = null;
		
		profile = getCarePackageProfileByName(profileName);
		
		if(profile == null)
		{
			return null;
		}
		
		Block block = blockLocation.getBlock();
		Material blockTypeOriginal = block.getType();
		
		block.setType(Material.CHEST);
		
		Chest carePackage = (Chest)block.getState();
		carePackage.setCustomName(profile.name);	
		carePackage.update();
		
		Inventory chestInventory = carePackage.getBlockInventory();	
		
		List<CarePackageItem> profileItems = profile.items;
		
		ItemStack[] itemsInCarepackage = new ItemStack[profile.items.size()];
		
		//fill the list of items for the carepackage
		for(int j = 0; j < itemsInCarepackage.length; j++)
		{
			
			CarePackageItem item = profileItems.get(j);
			int amountInCarepackage = main.getAmountByChance(item.amount, item.amountChance);

			itemsInCarepackage[j] = new ItemStack(item.material,amountInCarepackage);
			
		}
		chestInventory.clear();
		chestInventory.setStorageContents(itemsInCarepackage);
		
		return blockTypeOriginal;
	}
	
	public Material setBlockToACarePackageByProfile(CarePackageProfile profile, Location blockLocation)
	{
		CarePackageProfile carePackageprofile = profile;
	
		if(carePackageprofile == null)
		{
			return null;
		}
		
		Block block = blockLocation.getBlock();
		Material blockTypeOriginal = block.getType();
 		
		block.setType(Material.CHEST);
		
		Chest carePackage = (Chest)block.getState();
		carePackage.setCustomName(carePackageprofile.name);	
		carePackage.update();
		
		Inventory chestInventory = carePackage.getBlockInventory();	
		
		List<CarePackageItem> profileItems = carePackageprofile.items;
		
		ItemStack[] itemsInCarepackage = new ItemStack[carePackageprofile.items.size()];
		
		//fill the list of items for the carepackage
		for(int j = 0; j < itemsInCarepackage.length; j++)
		{
			
			CarePackageItem item = profileItems.get(j);
			int amountInCarepackage = main.getAmountByChance(item.amount, item.amountChance);

			itemsInCarepackage[j] = new ItemStack(item.material,amountInCarepackage);
			
		}
		chestInventory.clear();
		chestInventory.setStorageContents(itemsInCarepackage);
		
		return blockTypeOriginal;
	}
	
	public void loadProfilesFromConfig(Main main)
	{
		
		carePackageProfiles.clear();
		
		if(main.getConfig().contains("carepackageprofiles"))
		{
			
			Set<String> profileConfigNames = main.getConfig().getConfigurationSection("carepackageprofiles").getKeys(false);

			for(String profileName: profileConfigNames)
			{
				CarePackageProfile profile = new CarePackageProfile();
				Set<String> itemConfigNames;
				
				profile.name = profileName;
				
				//openings rank
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".openingrank"))
				{

					profile.openingsRank = PlayerRank.valueOf(main.getConfig().getString("carepackageprofiles." + profileName + ".openingrank"));
				}
				
				//dropping timings
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".droppingstimings"))
				{
					List<Integer> droppingsTimings = main.getConfig().getIntegerList("carepackageprofiles." + profileName + ".droppingstimings");
					profile.droppingsTimings = droppingsTimings;
				}
				
				//dropping amount
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".droppingsamount"))
				{
					int droppingsAmount = main.getConfig().getInt("carepackageprofiles." + profileName + ".droppingsamount");
					profile.droppingsAmount = droppingsAmount;
				}
				
				//droppping amount chance
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".droppingsamountchance"))
				{
					Double droppingsAmountChance = main.getConfig().getDouble("carepackageprofiles." + profileName + ".droppingsamountchance");
					profile.droppingsAmountChance = droppingsAmountChance;
				}
				
				
				//loot items
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".itemslist"))
				{
					itemConfigNames = main.getConfig().getConfigurationSection("carepackageprofiles." + profileName + ".itemslist").getKeys(false);
					for(String itemName: itemConfigNames)
					{
						if(main.getConfig().contains("carepackageprofiles." + profileName + ".itemslist." + itemName + ".material") && main.getConfig().contains("carepackageprofiles." + profileName + ".itemslist." + itemName + ".amount") && main.getConfig().contains("carepackageprofiles." + profileName + ".itemslist." + itemName + ".amountchance"))
						{
							Material material = Material.valueOf(main.getConfig().getString("carepackageprofiles." + profileName + ".itemslist." + itemName + ".material"));
							int itemAmount = main.getConfig().getInt("carepackageprofiles." + profileName + ".itemslist." + itemName + ".amount");
							double amountChance = main.getConfig().getDouble("carepackageprofiles." + profileName + ".itemslist." + itemName + ".amountchance");
							
							if(material != null && itemAmount > 0)
							{
								profile.items.add(new CarePackageItem(material, itemAmount, amountChance));
							}

						}
					}
				}
				carePackageProfiles.add(profile);
			}
		}
		else
		{
			System.out.println(main.pluginPrefix + ChatColor.RED + "No carepackageprofiles directory found in config.\n made a default carepackageprofile");
			main.getConfig().set("carepackageprofiles.defaultprofile.openingrank", PlayerRank.HUNTERS.toString());
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.material", Material.COBBLESTONE.toString());
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.amount", 10);
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.amountchance", 0.5D);
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.material", Material.COBBLESTONE.toString());
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.amount", 10);
			main.getConfig().set("carepackageprofiles.defaultprofile.itemslist.itemnameplaceholder.amountchance", 0.5D);
			
			main.getConfig().set("carepackageprofiles.defaultprofile.droppingstimings", "droppingTimings");
			main.getConfig().set("carepackageprofiles.defaultprofile.droppingsamount", 5);
			main.getConfig().set("carepackageprofiles.defaultprofile.droppingsamountchance", 0.5);
			
			main.saveConfig();
		}
	}
	
	public static void reloadPlayerRanks(Main main, HashMap<UUID,PlayerRank> playerRankVar)
	{
		if(playerRankVar.isEmpty() == false)
		{
			playerRankVar.clear();
		}
		
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(main.getConfig().contains("PlayerRanks." + p.getUniqueId()))
			{
				playerRankVar.put(p.getPlayer().getUniqueId(), PlayerRank.valueOf(main.getConfig().getString("PlayerRanks." + p.getUniqueId())));
			}
			else
			{
				playerRankVar.put(p.getUniqueId(), PlayerRank.HUNTERS);
				main.getConfig().set("PlayerRanks." + p.getUniqueId(), PlayerRank.HUNTERS.toString());
				main.saveConfig();
			}
		}
	}
	
	
	
	
}
