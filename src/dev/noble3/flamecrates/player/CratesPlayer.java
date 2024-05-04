package dev.noble3.flamecrates.player;

import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import dev.noble3.flamecrates.crate.Crate;

public class CratesPlayer {
  private final Map<Crate, Integer> pendingKeys;
  
  private final Server server;
  
  private final UUID uuid;
  
  private boolean changed;
  
  public CratesPlayer(Map<Crate, Integer> pendingKeys, Server server, UUID uuid) {
    this.pendingKeys = pendingKeys;
    this.server = server;
    this.uuid = uuid;
  }
  
  public Map<Crate, Integer> getPendingKeys() {
    return this.pendingKeys;
  }
  
  public void setChanged(boolean changed) {
    this.changed = changed;
  }
  
  public boolean isChanged() {
    return this.changed;
  }
  
  public void giveKeys(Crate crate, int amount) {
    Player player = this.server.getPlayer(this.uuid);
    if (player != null && player.isOnline()) {
        PlayerInventory playerInventory = player.getInventory();
        ItemStack key = crate.getKey();
        key.setAmount(64); // Establecer la cantidad máxima por adelantado
        while (amount > 0) {
            ItemStack keyCopy = key.clone(); // Clonar el ItemStack original
            int amountToAdd = Math.min(amount, 64); // Determinar la cantidad a agregar en esta iteración
            keyCopy.setAmount(amountToAdd); // Establecer la cantidad en el ItemStack clonado
            if (playerInventory.firstEmpty() == -1) {
                addPendingKeys(crate, amountToAdd);
            } else {
                playerInventory.addItem(keyCopy); // Agregar el ItemStack al inventario del jugador
            }
            amount -= amountToAdd; // Restar la cantidad agregada
        }
    }
}
  
  public void addPendingKeys(Crate crate, int amount) {
    this.pendingKeys.put(crate, Integer.valueOf(((Integer)this.pendingKeys.getOrDefault(crate, Integer.valueOf(0))).intValue() + amount));
    setChanged(true);
  }
  
  public int claimKeys() {
    Player player = this.server.getPlayer(this.uuid);
    int total = 0;
    if (player != null && player.isOnline()) {
      PlayerInventory playerInventory = player.getInventory();
      if (!this.pendingKeys.isEmpty() && playerInventory.firstEmpty() != -1) {
        for (Entry<Crate, Integer> entry : new HashSet<>(this.pendingKeys.entrySet())) {
          Crate crate = entry.getKey();
          int amount = entry.getValue();

          while (amount > 0) {
            ItemStack key = crate.getKey().clone();
            if (amount > 64) {
              key.setAmount(64);
            } else {
              key.setAmount(amount);
            } 
            if (playerInventory.firstEmpty() == -1) {
              key.setAmount(amount);
              break;
            } 
            ItemMeta keyItemMeta = key.getItemMeta();
            total += key.getAmount();
            keyItemMeta.addEnchant(Enchantment.DURABILITY, 0, false);
            key.setItemMeta(keyItemMeta);
            playerInventory.addItem(new ItemStack[] { key });
            amount -= 64;
          } 
          if (amount < 1) {
            this.pendingKeys.remove(crate);
            continue;
          } 
          this.pendingKeys.put(crate, Integer.valueOf(amount));
        } 
        setChanged(true);
      } 
    } 
    return total;
  }
}      
