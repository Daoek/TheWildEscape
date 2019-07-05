package teamdjg.wildescape.worldborderCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldborderStartercommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
		}
		
		
		
		
		return true;
		
	}

}
