package dev.noble3.flamecrates.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;

import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.util.ConfigWrapper;
import dev.noble3.flamecrates.util.Placeholder;
import dev.noble3.flamecrates.util.StringUtil;

public class CratesConfig {
    private final ConfigWrapper configWrapper;
    private final Map<String, List<String>> listMap = new HashMap<>();
    private final Map<String, String> stringMap = new HashMap<>();

    public CratesConfig(final Configuration config) {
        configWrapper = new ConfigWrapper(config);
    }

    public String getString(final String key) {
        if (stringMap.containsKey(key)) {
            return stringMap.get(key);
        } else {
            final String value = configWrapper.getOrDefault(key, "undefined");

            stringMap.put(key, value);

            return value;
        }
    }

    public List<String> getStringList(final String key) {
        if (listMap.containsKey(key)) {
            return listMap.get(key);
        } else {
            final List<String> value = configWrapper.getOrDefault(key, new ArrayList<>());

            listMap.put(key, value);

            return value;
        }
    }

    public List<String> getHologramLines(final String displayName) {
        return StringUtil.replace(getStringList("hologram_lines"), new Placeholder("%displayname%", displayName));
    }

    public String getItemName(final String displayName) {
        return StringUtil.replace(getString("item.name"), new Placeholder("%displayname%", displayName));
    }

    public List<String> getItemLore(final String displayName) {
        return StringUtil.replace(getStringList("item.lore"), new Placeholder("%displayname%", displayName));
    }

    public String getInventoryTitle(final String displayName) {
        return StringUtil.replace(getString("inventory.title"), new Placeholder("%displayname%", displayName));
    }

    public String getCommandUsage(final String label, final String cmd, final String args) {
        String commandUsageMessage = StringUtil.replace(getString("command_usage"), new Placeholder("%label%", label),
                new Placeholder("%cmd%", cmd), new Placeholder("%args%", args));

        return commandUsageMessage;
    }

    public String getError() {
        return ChatColor.GREEN + getString("error");
    }

    public String getNoConsole() {
        return ChatColor.GREEN + getString("no_console");
    }

    public String getNoPermission() {
        return ChatColor.GREEN + getString("no_permission");
    }

    public String getNoBlock() {
        return ChatColor.GREEN + getString("no_block");
    }

    public String getNoKeys() {
        return ChatColor.GREEN + getString("no_keys");
    }

    public String getNoKeysPending() {
        return ChatColor.GREEN + getString("no_keys_pending");
    }

    public String getNoSpace() {
        return ChatColor.GREEN + getString("no_space");
    }

    public String getNoCrate() {
        return ChatColor.GREEN + getString("no_crate");
    }

    public String getNoInteract() {
        return ChatColor.GREEN + getString("no_interact");
    }

    public String getInvalidKey(String crateName, String keyName) {
        return StringUtil.replace(getString("invalid_key"), new Placeholder("%crate_name%", crateName),
                new Placeholder("%key_name%", keyName));
    }

    public String getInvalidNumber() {
        return ChatColor.GREEN + getString("invalid_number");
    }

    public String getValidKey(final String crateName) {
        return StringUtil.replace(getString("valid_key"), new Placeholder("%crate_name%", crateName));
    }

    public String getReceivedKeys(final String giverName, final int amount, final String crateName) {
        return StringUtil.replace(getString("received_keys"), new Placeholder("%giver_name%", giverName),
                new Placeholder("%amount%", amount), new Placeholder("%crate_name%", crateName));
    }

    public String getHelpTitle(final String version) {
        return StringUtil.replace(getString("help.title"), new Placeholder("%version%", version));
    }

    public String getHelpCommand(final String label, final String cmd, final String args, final String description) {
        return StringUtil.replace(getString("help.command"), new Placeholder("%label%", label),
                new Placeholder("%cmd%", cmd), new Placeholder("%args%", args),
                new Placeholder("%description%", description));
    }

    public String getHelpSubtitle() {
        return ChatColor.GREEN + getString("help.subtitle");
    }

    public String getAddLocationSuccess(final String crateName) {
        return StringUtil.replace(getString("add_location.success"), new Placeholder("%crate_name%", crateName));
    }

    public String getAddLocationDescription() {
        return ChatColor.GREEN + getString("add_location.description");
    }

    public String getCheckSuccess(final int amount) {
        return StringUtil.replace(getString("check.success"), new Placeholder("%amount%", amount));
    }

    public String getCheckDescription() {
        return ChatColor.GREEN + getString("check.description");
    }

    public String getClaimSuccess(final int amount) {
        return StringUtil.replace(getString("claim.success"), new Placeholder("%amount%", amount));
    }

    public String getClaimDescription() {
        return ChatColor.GREEN + getString("claim.description");
    }

    public String getContentsSuccess(final String crateName) {
        return StringUtil.replace(getString("contents.success"), new Placeholder("%crate_name%", crateName));
    }

    public String getContentsDescription() {
        return ChatColor.GREEN + getString("contents.description");
    }

    public String getCreateSuccess(final String crateName) {
        return StringUtil.replace(getString("create.success"), new Placeholder("%crate_name%", crateName));
    }

    public String getCreateDescription() {
        return ChatColor.GREEN + getString("create.description");
    }

    public String getDisplaynameSuccess(final String crateName, final String newName) {
        return StringUtil.replace(getString("displayname.success"), new Placeholder("%crate_name%", crateName),
                new Placeholder("%new_name%", newName));
    }

    public String getDisplaynameDescription() {
        return ChatColor.GREEN + getString("displayname.description");
    }

    public String getKeyallSuccess(final int amount, final String crateName) {
        return StringUtil.replace(getString("keyall.success"), new Placeholder("%amount%", amount),
                new Placeholder("%crate_name%", crateName));
    }

    public String getKeyallDescription() {
        return ChatColor.GREEN + getString("keyall.description");
    }

    public String getKeySuccess(final int amount, final String crateName, final String playerName) {
        return StringUtil.replace(getString("key.success"),
                new Placeholder("%amount%", amount),
                new Placeholder("%crate_name%", crateName),
                new Placeholder("%player_name%", playerName));
    }    

    public String getKeyDescription() {
        return ChatColor.GREEN + getString("key.description");
    }

    public String getListSuccess(final String cratesList) {
        return StringUtil.replace(getString("list.success"), new Placeholder("%crates_list%", cratesList));
    }

    public String getListDescription() {
        return ChatColor.GREEN + getString("list.description");
    }

    public String getRemoveSuccess(final String crateName) {
        return StringUtil.replace(getString("remove.success"), new Placeholder("%crate_name%", crateName));
    }

    public String getRemoveDescription() {
        return ChatColor.GREEN + getString("remove.description");
    }

    public String getRemoveLocationSuccess(final String crateName) {
        return StringUtil.replace(getString("remove_location.success"), new Placeholder("%crate_name%", crateName));
    }

    public String getRemoveLocationDescription() {
        return ChatColor.GREEN + getString("remove_location.description");
    }

    public String getAddLocationAlreadySet() {
        return ChatColor.GREEN + getString("add_location.already_set");
    }

    public String getRemoveLocationNoCrateAt(String crateName) {
        return StringUtil.replace(getString("remove_location.no_crate_at_location"), new Placeholder("%crate_name%", crateName));
    }

    public String getRowsSuccess(String crateName, int slots) {
        return StringUtil.replace(getString("rows.success"), new Placeholder("%crate_name%", crateName),
                new Placeholder("%rows%", slots));
    }

    public String getRowsDescription() {
        return ChatColor.GREEN + getString("rows.description");
    }

    public String getHelpDescription() {
        return ChatColor.GREEN + getString("help.description");
    }

    public void put(String name, Crate crate) {
        // Almacena una instancia de Crate asociada con su nombre en el mapa.
        // Aquí deberías implementar la lógica para almacenar el Crate en el mapa.
        // Por ejemplo:
        // crates.put(name, crate);
    }

    public boolean containsKey(String crateName) {
        // Verifica si el mapa de Crates contiene una entrada con el nombre dado.
        return Crate.containsKey(crateName);
    }

    public Crate get(String crateName) {
        // Obtiene la instancia de Crate asociada con el nombre dado.
        return Crate.get(crateName);
    }

    public void remove(String crateName) {
        // Elimina la instancia de Crate asociada con el nombre dado del mapa.
        Crate.remove(crateName);
    }

    public Crate[] values() {
        // Devuelve un arreglo de todas las instancias de Crate en el mapa.
        return Crate.values().toArray(new Crate[0]);
    }

    public String[] keySet() {
        // Devuelve un arreglo de todos los nombres de Crate en el mapa.
        return Crate.keySet().toArray(new String[0]);
    }

    public Crate getOrDefault(String crateName, Object object) {
        // Obtiene la instancia de Crate asociada con el nombre dado, o devuelve el valor predeterminado si no está presente.
        return Crate.getOrDefault(crateName, (Crate) object);
    }

    public boolean containsKey(Location location) {
        throw new UnsupportedOperationException("Method 'containsKey' is not implemented");
    }

    public String getKeyallSuccess(int amount) {
        return StringUtil.replace(getString("keyall.success"), new Placeholder("%amount%", amount));
    }
}    