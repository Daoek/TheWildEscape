package teamdjg.wildescape.worldborderCommands;

import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
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
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if(sender instanceof Player)
		{
			if(!sender.hasPermission(mainclass.borderstartPermission))
			{
				sender.sendMessage(mainclass.pluginPrefix + ChatColor.DARK_RED + "ERROR: you don't have permission for this plugin.");
				return true;
			}
		}
		
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
		
		World gameWorld = mainclass.getServer().getWorld(mainclass.WorldBorderWorldName);
		
		//start game :
		
		mainclass.GameRunning = true;
		
		//change difficulty
		gameWorld.setDifficulty(Difficulty.getByValue(mainclass.gameDifficulty));
		
		//set game time
		gameWorld.setTime(mainclass.gameStartTime);
		
		
		//start border clock
		mainclass.BorderTimer(gameWorld);
		mainclass._WorldborderMechanics.BorderResumeMoving();
		
		//start message
		mainclass.getServer().broadcastMessage(mainclass.pluginPrefix + ChatColor.GOLD + "The game has begon. The last team alive will win.");
		
		//TODO clear inventory from all team members
		
		//TODO teleport teams

		//TODO change gamemode for the players
		
		//TODO maybe a starter kit for the players?
		
		return true;
		
	}

}
