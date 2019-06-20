package teamdjg.wildescape.main;

import org.bukkit.event.Listener;

public class Eventhandler implements Listener
{
	Main plugin;
	
	public Eventhandler(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
}
