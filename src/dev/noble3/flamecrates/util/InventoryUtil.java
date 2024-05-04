package dev.noble3.flamecrates.util;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;

public class InventoryUtil {
    public static int getValidRow(final int slot) {
        return Math.max(Math.min(slot, 9), 1);
    }

    public static int getValidRow(String input) {
        try {
            int slot = Integer.parseInt(input);
            return getValidRow(slot);
        } catch (NumberFormatException e) {
            // Si el número no se puede analizar, devuelve 3 filas por defecto
            return 3;
        }
    }    

    public static void copy(Inventory source, Inventory destination) {
        int size = Math.min(source.getSize(), destination.getSize());
        for (int i = 0; i < size; i++) {
            destination.setItem(i, source.getItem(i));
        }
    }    

    public static void close(Inventory inventory) {
        for (HumanEntity viewer : inventory.getViewers()) {
            viewer.closeInventory();
        }
    }    

    public static void clear(Inventory inventory) {
        inventory.clear();
    }
}    
