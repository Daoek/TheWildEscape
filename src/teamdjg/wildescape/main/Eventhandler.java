package teamdjg.wildescape.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
		main.getConfig().contains("PlayerRanks " + );
		
		/*
		Player mainPlayer = null;
		Player nearestPlayer = null;
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(p != mainPlayer)
			{
				if( mainPlayer.getLocation().distance(nearestPlayer.getLocation())  > mainPlayer.getLocation().distance(p.getLocation()))
				{
					nearestPlayer = p;
				}
			}
		}
		*/
		
	}
}
