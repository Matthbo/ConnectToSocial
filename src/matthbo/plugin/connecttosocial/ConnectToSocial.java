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
	
	public static final Object pluginMSG = ChatColor.DARK_GREEN + "[ConnectToSocial] " + ChatColor.RESET;
	public static final Object pluginUsage = pluginMSG + "" + ChatColor.DARK_RED;
	
	@Override
	public void onEnable() {
		
		getLogger().info("ConnectToSocial has been Activated");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ConnectToSocial is Disconnected from the Internet!");
	}
	
	
	public void resetFile(String playerName){
		File dataFolder = getDataFolder();
		if(!dataFolder.exists()) dataFolder.mkdir();

		File saveTo = new File(dataFolder, playerName + ".dat");
		if(saveTo.exists()) saveTo.delete();
		if(!saveTo.exists()) return;
	}
	
	public void fileToMessage(CommandSender sender, String target){
		try{
			File dataFolder = getDataFolder();
			
			File list = new File(dataFolder, "/" + target + ".dat");
			if(!list.exists()) {sender.sendMessage(pluginMSG + "Nothing!");}
			
			if(list.exists()){
				BufferedReader br = new BufferedReader(new FileReader(dataFolder + "/" + target + ".dat"));
				String str;
				sender.sendMessage(pluginMSG + target + "'s Social Media:");
				while((str = br.readLine()) != null){
					sender.sendMessage(pluginMSG + "" + ChatColor.BLUE + str);
				}
				br.close();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("social")){
			if(sender instanceof Player){
				Player player = (Player)sender;
				if(args.length == 1 && !args[0].equalsIgnoreCase("reset")){
					Player target = player.getServer().getPlayer(args[0]);
					if(target != null){
						fileToMessage(player, target.getName());
						return true;
					}else{
						player.sendMessage(pluginUsage + "Player is not online or does not exist!");
						return true;
					}
				}
				else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
					
					resetFile(player.getName());
					player.sendMessage(pluginMSG + "Your Social Media List has been reset");
					
					return true;
				}
				
				/*Youtube*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("youtube")){
					
					MediaCMD(args[2], player.getName(), "youtube");
					player.sendMessage(pluginMSG + "Youtube URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Twitch*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitch")){
					
					MediaCMD(args[2], player.getName(), "twitch");
					player.sendMessage(pluginMSG + "Twitch URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*twitter*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitter")){
					
					MediaCMD(args[2], player.getName(), "twitter");
					player.sendMessage(pluginMSG + "Twitter URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Facebook*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("facebook")){
					
					MediaCMD(args[2], player.getName(), "facebook");
					player.sendMessage(pluginMSG + "Facebook URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Skype*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("skype")){
					
					MediaCMD(args[2], player.getName(), "skype");
					player.sendMessage(pluginMSG + "Skype Name is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Instagram*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("instagram")){
					
					MediaCMD(args[2], player.getName(), "instagram");
					player.sendMessage(pluginMSG + "Instagram URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}else{
					player.sendMessage(pluginUsage + "Usage: /social [playername]");
					player.sendMessage(pluginUsage + "Usage: /social set [media] [url]");
					player.sendMessage(pluginUsage + "Usage: /social reset");
					player.sendMessage(pluginMSG + "---[Media]---");
					player.sendMessage(pluginMSG + "" + ChatColor.BLUE + "youtube, twitch, twitter, facebook, skype, instagram");
					return true;
				}
			}else sender.sendMessage(pluginUsage + "Player Command Only!"); return true;
		}
		
		return false;
	}
	
	public void MediaCMD(String url, String playerName, String medium){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			switch(medium){
			case "youtube": pw.println("Youtube: " + url); break;
			case "twitch": pw.println("Twitch: " + url); break;
			case "twitter": pw.println("Twitter: " + url); break;
			case "facebook": pw.println("Facebook: " + url); break;
			case "skype": pw.println("Skype: " + url); break;
			case "instagram": pw.println("Instagram: " + url); break;
			}
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
}
