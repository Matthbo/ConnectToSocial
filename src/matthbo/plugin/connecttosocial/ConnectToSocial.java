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
					
					YoutubeCMD(args[2], player.getName());
					player.sendMessage(pluginMSG + "Youtube URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Twitch*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitch")){
					
					TwitchCMD(args[2], player.getName());
					player.sendMessage(pluginMSG + "Twitch URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*twitter*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("twitter")){
					
					TwitterCMD(args[2], player.getName());
					player.sendMessage(pluginMSG + "Twitter URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Facebook*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("facebook")){
					
					FacebookCMD(args[2], player.getName());
					player.sendMessage(pluginMSG + "Facebook URL is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}
				
				/*Skype*/
				else if(args.length == 3 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("skype")){
					
					SkypeCMD(args[2], player.getName());
					player.sendMessage(pluginMSG + "Skype Name is set to: " + ChatColor.BLUE + args[2]);
					
					return true;
				}else{
					player.sendMessage(pluginUsage + "Usage: /<command> [playername]");
					player.sendMessage(pluginUsage + "Usage: /<command> set [media] [url]");
					player.sendMessage(pluginUsage + "Usage: /<command> reset");
					return true;
				}
			}else sender.sendMessage(pluginUsage + "Player Command Only!"); return true;
		}
		
		return false;
	}
	
	public void YoutubeCMD(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Youtube: " + url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void TwitchCMD(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Twitch:" + url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void TwitterCMD(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Twitter: " + url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void FacebookCMD(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Facebook: " + url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}
	
	public void SkypeCMD(String url, String playerName){
		try{
			File dataFolder = getDataFolder();
			if(!dataFolder.exists()) dataFolder.mkdir();
		
			File saveTo = new File(dataFolder, playerName + ".dat");
			if(!saveTo.exists()) saveTo.createNewFile();
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("Skype: "  + url);
			pw.flush();
			pw.close();
		}catch(IOException e){e.printStackTrace();}
	}

}
