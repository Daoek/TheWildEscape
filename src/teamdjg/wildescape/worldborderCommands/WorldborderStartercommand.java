package teamdjg.wildescape.worldborderCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import teamdjg.wildescape.main.Main;

public class WorldborderStartercommand implements CommandExecutor {
	
	Main mainclass;
	
	public WorldborderStartercommand(Main plugin)
	{
		this.mainclass = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if(mainclass.WorldborderCenterCheck == false)
		{
			if (sender instanceof Player) 
			{
				mainclass.ClearChat((Player)sender);
				sender.sendMessage(mainclass.ChatLine());
				sender.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You first need to use the  - /bordercenter - before you can start the game");
				sender.sendMessage(mainclass.ChatLine());
			}
			else
			{
				System.out.println(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You first need to use the  - /bordercenter - before you can start the game");
			}
			return true;
		}
		
		//start game :
		
		//change difficulty
		
		//set day time
		
		//make world border move
		
		//start border clock
		
		//start message
		
		//TODO teleport teams

		return true;
		
	}

}
