package teamdjg.wildescape.main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener		// A CORE OF SOMETHING BIG!
{
	public String pluginPrefix = "[DJG TWE] ";
	
	@Override
	public void onEnable() {		// Wanneer de server start.
		System.out.println(pluginPrefix + "PLUGIN ENABLED!");
	}
	
	@Override
	public void onDisable() {		// Wanneer de server stopt
		System.out.println(pluginPrefix + "PLUGIN DISABLED!");
	}
}

// Einde van de Main class!!!! Kijk gerust verder, maars je zult niks vinden.