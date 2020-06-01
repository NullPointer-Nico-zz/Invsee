package de.nico.invsee.ec.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.nukkitx.fakeinventories.inventory.ChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeSlotChangeEvent;

public class EnderInvseeCommand extends Command {
    byte chunck = 0;

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
                    ChestFakeInventory ec = new ChestFakeInventory();
                    ec.addListener(event -> {
                        event.setCancelled(true);
                    });
                    ec.setName("§l§cEnderChest von §l§a" + target.getName());
                    ec.setContents(target.getEnderChestInventory().getContents());
                    ((Player) sender).addWindow(ec);
                }
            }
        } else {
            sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4/inve <Spieler>");
        }
        return false;
    }

}
