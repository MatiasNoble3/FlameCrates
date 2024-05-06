package dev.noble3.flamecrates.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.crate.CrateManager;
import dev.noble3.flamecrates.util.ConfigurationUtil;

public class CratesPlayerManager {
    private final CrateManager crateManager;
    private final Map<UUID, CratesPlayer> cratesPlayers;
    private final ConfigurationUtil configurationUtil;
    private final Server server;

    public CratesPlayerManager(CrateManager crateManager, ConfigurationUtil configurationUtil, Server server) {
        this.crateManager = crateManager;
        this.cratesPlayers = new HashMap<>();
        this.configurationUtil = configurationUtil;
        this.server = server;
    }

    public CratesPlayer getPlayer(UUID uuid) {
        CratesPlayer cratesPlayer = cratesPlayers.get(uuid);
        if (cratesPlayer == null) {
            cratesPlayer = loadPlayer(uuid);
            cratesPlayers.put(uuid, cratesPlayer);
        }
        return cratesPlayer;
    }

    private CratesPlayer loadPlayer(UUID uuid) {
        Map<Crate, Integer> pendingKeys = new HashMap<>();
        String dataFilePath = "%datafolder%/data/" + uuid + ".yml";
        YamlConfiguration yamlConfiguration = configurationUtil.getConfiguration(dataFilePath);
        if (yamlConfiguration != null) {
            ConfigurationSection pendingKeysSection = yamlConfiguration.getConfigurationSection("pendingkeys");
            if (pendingKeysSection != null) {
                for (String key : pendingKeysSection.getKeys(false)) {
                    int amount = pendingKeysSection.getInt(key);
                    pendingKeys.put(crateManager.getCrate(key), amount);
                }
            }
        }
        return new CratesPlayer(pendingKeys, server, uuid);
    }

    public void savePlayer(UUID uuid, boolean async) {
        CratesPlayer cratesPlayer = cratesPlayers.remove(uuid);
        if (cratesPlayer != null && cratesPlayer.isChanged()) {
            Map<Crate, Integer> pendingKeys = cratesPlayer.getPendingKeys();
            if (!pendingKeys.isEmpty()) {
                YamlConfiguration dataFile = new YamlConfiguration();
                String filePath = "%datafolder%/data/" + uuid + ".yml";
                for (Map.Entry<Crate, Integer> entry : pendingKeys.entrySet()) {
                    Crate crate = entry.getKey();
                    int amount = entry.getValue();
                    dataFile.set("pendingkeys." + crate.getName(), amount);
                }
                configurationUtil.saveConfiguration(dataFile, filePath, async);
            } else {
                String filePath = "%datafolder%/data/" + uuid + ".yml";
                configurationUtil.deleteConfiguration(filePath, async);
            }
        }
    }
}