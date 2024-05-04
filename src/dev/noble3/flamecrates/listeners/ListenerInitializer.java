package dev.noble3.flamecrates.listeners;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.CrateManager;
import dev.noble3.flamecrates.player.CratesPlayerManager;

public class ListenerInitializer {
  private final Plugin plugin;
  private final CrateManager crateManager;
  private final CratesPlayerManager cratesPlayerManager;
  private final CratesConfig cratesConfig;
  private boolean initialized;
  
  public ListenerInitializer(Plugin plugin, CrateManager crateManager, CratesPlayerManager cratesPlayerManager, CratesConfig cratesConfig) {
    this.plugin = plugin;
    this.crateManager = crateManager;
    this.cratesPlayerManager = cratesPlayerManager;
    this.cratesConfig = cratesConfig;
    this.initialized = false;
}
  
  public void initialize() {
    if (!this.initialized) {
        this.initialized = true;
        PluginManager pluginManager = this.plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new InventoryClickListener(this.crateManager), this.plugin);
        pluginManager.registerEvents(new PlayerInteractListener(this.crateManager, this.cratesConfig), this.plugin);
        pluginManager.registerEvents(new PlayerQuitListener(this.cratesPlayerManager), this.plugin);
    }
}
  
  public void deinitialize() {
    if (this.initialized) {
        this.initialized = false;
        HandlerList.unregisterAll(this.plugin);
    }
}

  }
