package de.nico.invsee.ec.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.nukkitx.fakeinventories.inventory.DoubleChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeInventoryListener;
import com.nukkitx.fakeinventories.inventory.FakeSlotChangeEvent;

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

                if(target.equals(sender)) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Du kannst diesen Command nicht bei dir selber anwenden");
                    return true;
                }

                if(target == null) {
                    sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4Der Spieler ist nicht online oder existiert nicht!");
                } else {
                    DoubleChestFakeInventory inv = new DoubleChestFakeInventory();
                    inv.addListener(this::change);
                    inv.setName("§l§cInventar von " + target.getName());
                    inv.setContents(target.getInventory().getContents());
                    ((Player) sender).addWindow(inv);
                }
            }
        } else {
            sender.sendMessage("§l§cFaiv§l§agames §l§7| §l§4/inv <Spieler>");
        }
        return true;
    }

    public void change(FakeSlotChangeEvent event) {
        if(event.getInventory() instanceof DoubleChestFakeInventory) {
            if(event.getInventory().getName().contains("§l§cInventar von ")) {
                event.setCancelled(true);
            }
        }
    }
}
