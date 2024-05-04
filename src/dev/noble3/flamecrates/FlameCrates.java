package dev.noble3.flamecrates;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import dev.noble3.flamecrates.command.CratesCommandExecutor;
import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.CrateManager;
import dev.noble3.flamecrates.listeners.ListenerInitializer;
import dev.noble3.flamecrates.player.CratesPlayerManager;
import dev.noble3.flamecrates.util.ConfigurationUtil;

public class FlameCrates extends JavaPlugin {
    private final Plugin plugin = this;
    private CrateManager crateManager;
    private CratesPlayerManager cratesPlayerManager;
    private ListenerInitializer listenerInitializer;

    @Override
    public void onEnable() {
        final Server server = getServer();
        final ConfigurationUtil configurationUtil = new ConfigurationUtil(plugin);

        configurationUtil.createConfiguration("%datafolder%/config.yml");

        final CratesConfig cratesConfig = new CratesConfig(configurationUtil.getConfiguration(getDataFolder() + File.separator + "config.yml"));

        crateManager = new CrateManager(configurationUtil, cratesConfig, plugin);
        cratesPlayerManager = new CratesPlayerManager(crateManager, configurationUtil, server);

        getCommand("crates")
                .setExecutor(new CratesCommandExecutor(crateManager, cratesPlayerManager, cratesConfig, this));

        crateManager.loadCrates();
        crateManager.spawnHolograms();
        listenerInitializer = new ListenerInitializer(plugin, crateManager, cratesPlayerManager, cratesConfig);
        listenerInitializer.initialize();
    }

    @Override
    public void onDisable() {
        for (final Player player : getServer().getOnlinePlayers()) {
            cratesPlayerManager.savePlayer(player.getUniqueId(), false);
        }

        if (crateManager != null) {
            crateManager.saveCrates(false);
            crateManager.despawnHolograms();
        }

        if (listenerInitializer != null) {
            listenerInitializer.deinitialize();
        }
    }
}
