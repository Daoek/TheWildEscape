package teamdjg.wildescape.worldborderCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import teamdjg.wildescape.main.Main;

public class WorldborderSetupcommand implements CommandExecutor {

	Main mainclass;
	
	public WorldborderSetupcommand(Main plugin)
	{
		this.mainclass = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if(sender instanceof Player)
		{
			Player p = (Player)sender;
			
			//check if there are 4 args
			if(args.length != 4)
			{
				WrongArgsMessage(p);
				return true;
			}
			else
			{
				int minborder;
				int maxborder;
				int distanceborder;
				long speedborder;
				
				//check if all types are correct
				try 
				{
					minborder = Integer.parseInt(args[0]);
					maxborder = Integer.parseInt(args[1]);
					distanceborder = Integer.parseInt(args[2]);
					speedborder = Long.parseLong(args[3]);
				}
				catch(Exception e)
				{
					p.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "You need to put numbers, not letters in the arguments.");
					return true;
				}
				
				//check if amount from the min max and the distance
				if(distanceborder <= 0 || minborder < 0 || maxborder <= minborder || speedborder > -1)
				{
					p.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR:" + ChatColor.GOLD + "Your distance per night can be 0 or under, and your minimal border can't be below 0.");
					p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Also your maximum border can't be below or equal to the minimal border.");
					p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "And your speed for the border to move must be bigger than -1");
					return true;
				}
				
				mainclass.WorldBorderMin = minborder;
				mainclass.WorldBorderMax = maxborder;
				mainclass.WordBorderDistance = distanceborder;
				mainclass.WorldBorderSpeed = speedborder;
				mainclass.ContactPlayerForWorldBorder = p;
				mainclass.WorldBorderWorldName = p.getWorld().getName();
				mainclass.WorldborderSetupCheck = true;
			}
		}
		else
		{
			System.out.print(mainclass.pluginPrefix + ChatColor.DARK_RED + "Only a player can perform this command.");
		}
		
		return true;
	}
	
	void WrongArgsMessage(Player p)
	{
		p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "You don't have the good arguments!");
		p.sendMessage(mainclass.pluginPrefix + ChatColor.GOLD + "Type - /help WorldborderSetup - for more information ");
		
	}
}
