package de.nico.invsee.ec.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import com.nukkitx.fakeinventories.inventory.DoubleChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import com.nukkitx.fakeinventories.inventory.FakeInventory;

public class InvSeeCommand extends Command {
    public InvSeeCommand() {
        super("inv", "Zeigt dir das inventar eines anderen Spielers an!", "§l§cFaiv§l§agames §l§7| §l§4/inv <Spieler>", new String[]{"invsee"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if(args.length == 1) {
            if(sender.hasPermission("inv.other")) {
                if(!(sender instanceof Player)) {
                    sender.sendMessage("Du musst ein Spieler sein!");
                    return true;
                }
                Player target = Server.getInstance().getPlayer(args[0]);

                if(target == null) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Der Spieler ist nicht online oder existiert nicht!");
                    return true;
                } else if(target.equals(sender)) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Du kannst diesen Command nicht bei dir selber anwenden");
                } else {
                    DoubleChestFakeInventory inv = new FakeInventories().createDoubleChestInventory();
                    inv.setContents(target.getInventory().getContents());
                    inv.addListener(event -> {
                        event.setCancelled(true);
                    });
                    inv.setName("§l§c" + target.getName() + "§l§c`s §l§aInv");
                    ((Player) sender).addWindow(inv);
                }
            }
        } else {
            sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4/inv <Spieler>");
        }
        return true;
    }


}
