package matthbo.plugin.connecttoyt;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConnectToYT extends JavaPlugin{
	
	public static final Object pluginMSG = ChatColor.DARK_GREEN + "[ConnectToYT] " + ChatColor.RESET;
	public static final Object pluginUsage = pluginMSG + "" + ChatColor.DARK_RED;
	
	@Override
	public void onEnable() {
		
		getLogger().info("ConnectToYT has been Activated");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ConnectToYT is Disconnected!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("youtube")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				if(args.length > 0 && args[0].equalsIgnoreCase("set")){
					//TODO if /command player
					player.sendMessage(pluginUsage + "Usage: /<command> set [url]");
					return true;
				}else{
					player.sendMessage(pluginUsage + "Usage: /<command> [playername]");
					player.sendMessage(pluginUsage + "Usage: /<command> set [url]");
					return true;
				}
			}else{
				sender.sendMessage("Player Command Only!");
				return true;
			}
		}
		return false;
	}

}
