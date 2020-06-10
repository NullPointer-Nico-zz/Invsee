package de.nico.invsee.ec.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.nukkitx.fakeinventories.inventory.ChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeInventories;

public class EnderInvseeCommand extends Command {

    public EnderInvseeCommand() {
        super("inve", "Zeigt dir die Ec eines Spielers an!", "§l§cFaiv§l§agames §l§7| §l§4/inve <Spieler>");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {

        if(args.length == 1) {
            if(sender.hasPermission("ec.other")) {
                if(!(sender instanceof Player)) {
                    sender.sendMessage("Du musst ein spieler sein!");
                    return true;
                }
                Player target = Server.getInstance().getPlayer(args[0]);

                if(target == null) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Der Spieler ist nicht Online oder Existiert nicht!");
                } else if(target.equals(sender)) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Du kannst diesen Command nicht auf dir selbst anwenden!");
                } else {
                    ChestFakeInventory ec = new FakeInventories().createChestInventory();
                    ec.setName("§l§c" + target.getName() + "§l§c`s §l§a Ec");
                    ec.setContents(target.getEnderChestInventory().getContents());
                    ec.addListener(event -> {
                        event.setCancelled(true);
                    });
                    ((Player)sender).addWindow(ec);
                }
            }
        } else {
            sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4/inve <Spieler>");
        }
        return false;
    }

}
