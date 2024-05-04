package dev.noble3.flamecrates.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import dev.noble3.flamecrates.player.CratesPlayerManager;

class PlayerQuitListener implements Listener {
  private final CratesPlayerManager cratesPlayerManager;
  
  PlayerQuitListener(CratesPlayerManager cratesPlayerManager) {
    this.cratesPlayerManager = cratesPlayerManager;
  }
  
  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onPlayerQuit(PlayerQuitEvent event) {
      cratesPlayerManager.savePlayer(event.getPlayer().getUniqueId(), true);
  }  
}
