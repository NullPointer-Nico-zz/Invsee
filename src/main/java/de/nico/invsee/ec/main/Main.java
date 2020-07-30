package de.nico.invsee.ec.main;

import cn.nukkit.plugin.PluginBase;
import de.nico.invsee.ec.commands.EnderInvseeCommand;
import de.nico.invsee.ec.commands.InvSeeCommand;

public class Main extends PluginBase {
    @Override
    public void onEnable() {
        this.getServer().getCommandMap().register("inve", new EnderInvseeCommand());
        this.getServer().getCommandMap().register("ivn", new InvSeeCommand());
    }
}
