package teamdjg.wildescape.worldborderCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldborderStartercommand implements CommandExecutor {
	
	// needs in args:
	//1 worldborder min
	//2 worldborder max
	//3 worldborder distance
	//4 worldborder speed
	
	// ook de world en de player toevoegen bij de variables
	// daarna de time de diffuculty veranderen
	// TO-DO de players over de hele map versprijden
	// 
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {		
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
		}
		
		
		
		
		return true;
		
	}

}
