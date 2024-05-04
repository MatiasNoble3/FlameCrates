package dev.noble3.flamecrates.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.crate.CrateManager;

class InventoryClickListener implements Listener {
  private final CrateManager crateManager;
  
  InventoryClickListener(CrateManager crateManager) {
    this.crateManager = crateManager;
  }
  
  @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
  public void onInventoryClick(InventoryClickEvent event) {
      HumanEntity humanEntity = event.getWhoClicked();
      Inventory inventory = event.getView().getTopInventory();
  
      if (inventory != null && !humanEntity.hasPermission("crates.admin")) {
          Crate crate = crateManager.getCrate(inventory);
  
          if (crate != null) {
              event.setCancelled(true);
          }
      }
  }
  
  }
