package teamdjg.wildescape.carepackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import teamdjg.wildescape.main.Main;

public class CarePackageManager {

	Main main;
	List<CarePackageProfile> carePackageProfiles;
	
	public CarePackageManager(Main main)
	{
		this.main = main;
		carePackageProfiles = new ArrayList<>();
		loadProfilesFromConfig(main);
	}
	
	public void loadProfilesFromConfig(Main main)
	{
		if(main.getConfig().contains("carepackageprofiles"))
		{
			System.out.println("carepackageprofile found");
			
			Set<String> profileConfigNames = main.getConfig().createSection("carepackageprofiles").getKeys(false);

			for(String profileName: profileConfigNames)
			{
				CarePackageProfile profile = new CarePackageProfile();
				Set<String> itemConfigNames = main.getConfig().createSection("carepackageprofiles." + profileName).getKeys(false);
				
				profile.name = profileName;
				
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".openingrank"))
				{
					profile.openingsRank = PlayerRank.valueOf(main.getConfig().getString("carepackageprofiles." + profileName + ".openingrank"));
				}
				
				if(main.getConfig().contains("carepackageprofiles." + profileName + ".items"))
				{
					for(String itemName: itemConfigNames)
					{
						if(main.getConfig().contains("carepackageprofiles." + profileName + ".items." + itemName + ".material") && main.getConfig().contains("carepackageprofiles." + profileName + ".items." + itemName + ".amount") )
						{
							String materialName = main.getConfig().getString("carepackageprofiles." + profileName + ".items." + itemName + ".material");
							int itemAmount = main.getConfig().getInt("carepackageprofiles." + profileName + ".items." + itemName + ".amount");
							
							profile.items.put(Material.getMaterial(materialName), itemAmount);
						}
					}
				}

				carePackageProfiles.add(profile);
			}
		}
		else
		{
			System.out.println("carepackageprofile created");
			
			main.getConfig().set("carepackageprofiles.defaultprofile.openingrank", PlayerRank.HUNTERS.toString());
			main.getConfig().set("carepackageprofiles.defaultprofile.items.defaultitem.material", Material.COBBLESTONE.toString());
			main.getConfig().set("carepackageprofiles.defaultprofile.items.defaultitem.amount", 10);
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
			System.out.println(p.getName());
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
