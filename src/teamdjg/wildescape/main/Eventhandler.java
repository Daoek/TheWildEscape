package teamdjg.wildescape.main;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import teamdjg.wildescape.carepackage.PlayerRank;

public class Eventhandler implements Listener
{
	Main main;
	
	public Eventhandler(Main main)
	{
		main.getServer().getPluginManager().registerEvents(this, main);
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		if(main.getConfig().contains("PlayerRanks." + e.getPlayer().getUniqueId()))
		{
			main.playerRanks.put(e.getPlayer().getUniqueId(), PlayerRank.valueOf(main.getConfig().getString("PlayerRanks." + e.getPlayer().getUniqueId())));
		}
		else
		{
			main.playerRanks.put(e.getPlayer().getUniqueId(), PlayerRank.HUNTERS);
			main.getConfig().set("PlayerRanks." + e.getPlayer().getUniqueId(), PlayerRank.HUNTERS.toString());
			main.saveConfig();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		if(main.playerRanks.containsKey(e.getPlayer().getUniqueId()))
		{
			main.playerRanks.remove(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		String prefix = null;
		
		if(main.playerRanks.containsKey(e.getPlayer().getUniqueId()))
		{
			switch(main.playerRanks.get(e.getPlayer().getUniqueId()))
			{
			case ALFAWOLF:
				prefix = "[" + ChatColor.DARK_RED + "Alfawolf" + ChatColor.RESET + "]";
				break;
			case HUNTERS:
				prefix = "[" + ChatColor.BLUE + "Hunter" + ChatColor.RESET + "]";
				break;
			
			default:
				prefix = "[NoClass]"; 
				break;
			}
		}
		
		e.setFormat(prefix + " " + e.getPlayer().getDisplayName() + ": " + e.getMessage());
	}
}
