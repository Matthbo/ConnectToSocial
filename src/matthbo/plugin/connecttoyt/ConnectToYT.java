package matthbo.plugin.connecttoyt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ConnectToYT extends JavaPlugin{
	
	@Override
	public void onEnable() {
		
		getLogger().info("ConnectToYT has been Activated");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ConnectToYT is Disconnected!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return false;
	}

}
