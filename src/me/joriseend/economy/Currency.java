package me.joriseend.economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Currency extends JavaPlugin {
	
	String Name = "Economy";
	String EconomyPrefix = ChatColor.DARK_GRAY + "Economy>" + ChatColor.RESET + " ";
	String EconomyConsolePrefix = "[" + Name + "]" + " ";
	String EnableSuccess = EconomyConsolePrefix + "Successfully enabled!";
	String DisableSuccess = EconomyConsolePrefix + "Economy system has been disabled!";
	String FewArgs = "Please specify an action!";
	String FewArgsPlayer = "Please specify a player!";
	String FewArgsAmount = "Please specify a amount";
	String IncorrectBalance = EconomyPrefix + ChatColor.RED + "Your account has insufficient funds.";
	String YouGotMoney = EconomyPrefix + ChatColor.GREEN + "You recieved" + " ";
	String YouLostMoney = EconomyPrefix + ChatColor.GRAY + "You sent" + " ";
	String OnlinePlayerFindPrefix = ChatColor.DARK_GRAY + "Online Player Search>" + ChatColor.RESET + " ";
	String OnlinePlayerFindConsolePrefix = "[Online Player Search]" + " ";
	String CannotFindPlayer = ChatColor.RED + "Cannot find player" + ChatColor.RESET;
	String TooManyArgs = ChatColor.RED + "Too many arguments!" + " " + ChatColor.DARK_RED + "DENIED!";
	String NoPermission = EconomyPrefix + ChatColor.RED + "Many permissions, such denied, much no. Wow.";
	String error = ChatColor.DARK_RED + "euhm... something went wrong";
	String ConfigBalance = ".Balance";
	String ConfigInf = ".InfiniteBalance";
	String EconomyManagePermission = "economy.manage";
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		System.out.println(EnableSuccess);
		System.out.println("***" + "THIS BUILD IS EXTREMELY UNSTABLE! DON'T USE THIS VERSION WITHOUT BACKUPS!" + "***");
	}
	public void onDisable(){
		System.out.println(DisableSuccess);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(command.getName().equalsIgnoreCase("pay")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					player.sendMessage(EconomyPrefix + FewArgsPlayer);
					return true;
				}else if(args.length == 1){
					player.sendMessage(EconomyPrefix + FewArgsAmount);
					return true;
				}else if(args.length == 2){
					int amount = Integer.parseInt(args[1]);
					Player target = Bukkit.getPlayer(args[0]);
					if(getConfig().getInt(player.getUniqueId().toString() + ConfigBalance) >= amount){
						getConfig().set(player.getUniqueId().toString() + ConfigBalance, getConfig().getInt(player.getUniqueId().toString() + ConfigBalance) - amount);
						getConfig().set(target.getUniqueId().toString() + ConfigBalance, getConfig().getInt(target.getUniqueId().toString() + ConfigBalance) + amount);
						target.sendMessage(YouGotMoney + ChatColor.GOLD + amount);
						player.sendMessage(YouLostMoney + ChatColor.GOLD + " " + "to" + " " + target.getDisplayName());
						return true;
					}else if(getConfig().getInt(player.getUniqueId().toString() + ConfigBalance) < amount){
						player.sendMessage(IncorrectBalance);
						return true;
					}
				}
			}else{
				//TODO Execute when sender is console
			}
		}
		return false;
	}
}
