package de.nico.invsee.ec.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;

public class ECCommand extends Command implements Listener {
    public ECCommand() {
        super("ec", "Zeigt dir deine EnderChest", "ec\nenderchest", new String[]{"enderchest"});
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        return false;
    }
}
