package matthbo.plugin.connecttosocial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ConnectToSocial extends JavaPlugin{
	
	public static final Object pluginMSG = ChatColor.DARK_GREEN + "[ConnectToYT] " + ChatColor.RESET;
	public static final Object pluginUsage = pluginMSG + "" + ChatColor.DARK_RED;
	
	@Override
	public void onEnable() {
		
		getLogger().info("ConnectToYT has been Activated");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ConnectToYT is Disconnected from the Internet!");
	}
	
	public void logToFile(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(saveTo.exists()) saveTo.delete(); saveTo.createNewFile();
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void fileToMessage(CommandSender sender, String target){
		try{
			File dataFolder = getDataFolder();
			
			File list = new File(dataFolder, "/" + target + ".dat");
			if(!list.exists()) {sender.sendMessage(pluginMSG + "Nothing!");}
			
			if(list.exists()){
				BufferedReader br = new BufferedReader(new FileReader(dataFolder + "/" + target + ".dat"));
				String str = br.readLine();
				sender.sendMessage(pluginMSG + target + "'s Channel: " + ChatColor.BLUE + str);
				br.close();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("youtube")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				if(args.length == 2 && args[0].equalsIgnoreCase("set")){
					
					logToFile(args[1].toLowerCase(), player.getName());
					
					player.sendMessage(pluginMSG + "Youtube URL is set to: " + ChatColor.BLUE + args[1]);
					
					return true;
				}
				else if(args.length == 1){
					Player target = player.getServer().getPlayer(args[0]);
					if(target != null){
						fileToMessage(player, target.getName());
						return true;
					}else{
						player.sendMessage(pluginUsage + "Player is not online or does not exist!");
						return true;
					}
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
