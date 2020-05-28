package de.nico.invsee.ec.main;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import de.nico.invsee.ec.commands.ECCommand;
import de.nico.invsee.ec.commands.EnderInvseeCommand;
import de.nico.invsee.ec.commands.InvSeeCommand;

public class Main extends PluginBase {
    @Override
    public void onEnable() {
        this.getServer().getCommandMap().register("ec", new ECCommand());
        this.getServer().getCommandMap().register("inve", new EnderInvseeCommand());
        this.getServer().getCommandMap().register("ivn", new InvSeeCommand());
        this.getServer().getPluginManager().registerEvents(new ECCommand(), this);

        RegisteredServiceProvider<FakeInventories> pro = this.getServer().getServiceManager().getProvider(FakeInventories.class);
        if(pro == null || pro.getProvider() == null) {
            this.getServer().getPluginManager().disablePlugin(this);
        }
    }
}
