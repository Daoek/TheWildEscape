package teamdjg.wildescape.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCreate implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (sender instanceof Player) {
			player.sendMessage("Works!");
		}
		else
		{
			sender.sendMessage("Works in the console!");
		}
		
		return true;
		
	}
	
}
