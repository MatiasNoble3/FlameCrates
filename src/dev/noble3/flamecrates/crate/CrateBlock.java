package dev.noble3.flamecrates.crate;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CrateBlock {
    private final Plugin plugin;
    private final Crate crate;
    private final Location location;
    private Hologram hologram;
    private static Map<String, Hologram> hologramMap = new HashMap<>(); // Mapa para almacenar hologramas por nombre

    CrateBlock(final Plugin plugin, final Crate crate, final Location location) {
        this.plugin = plugin;
        this.crate = crate;
        this.location = location;
    }

    private void appendLines(final Hologram hologram) {
        for (final String line : crate.getHologramLines()) {
            DHAPI.addHologramLine(hologram, line);
        }
    }

    public void spawnHologram() {
        new BukkitRunnable() {
            @Override
            public void run() {
                String hologramName = crate.getName();
                
                // Verificar si el holograma con el nombre ya existe
                if (hologramMap.containsKey(hologramName)) {
                    return; // El holograma con este nombre ya existe, no hacemos nada
                }
                
                // Crear el holograma solo si no hay otro con el mismo nombre
                float offset = 1f + 0.25f * crate.getHologramLines().size();
                Location clonedLocation = location.clone().add(0, offset, 0);
                Hologram newHologram = DHAPI.createHologram(hologramName, clonedLocation, new ArrayList<>());
                appendLines(newHologram);
                hologram = newHologram;
                hologramMap.put(hologramName, hologram); // Agregar holograma al mapa
            }
        }.runTask(plugin);
    }     

    public void despawnHologram() {
        String hologramName = crate.getName();
        Hologram existingHologram = hologramMap.get(hologramName);
        
        if (existingHologram != null) {
            existingHologram.delete();
            hologramMap.remove(hologramName); // Eliminar el holograma del mapa
            hologram = null;
        }
    }
}
