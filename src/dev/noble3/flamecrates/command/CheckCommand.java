package dev.noble3.flamecrates.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.player.CratesPlayer;
import dev.noble3.flamecrates.player.CratesPlayerManager;

import java.util.Map;

class CheckCommand implements CratesCommand {
    private final CratesPlayerManager cratesPlayerManager;
    private final CratesConfig cratesConfig;

    CheckCommand(final CratesPlayerManager cratesPlayerManager, final CratesConfig cratesConfig) {
        this.cratesPlayerManager = cratesPlayerManager;
        this.cratesConfig = cratesConfig;
    }

    public void execute(final CommandSender sender, final String label, final String[] args) {
        if (!(sender instanceof Entity)) {
            sender.sendMessage("This command can only be executed by an entity!");
            return;
        }

        final CratesPlayer cratesPlayer = this.cratesPlayerManager.getPlayer(((Entity) sender).getUniqueId());
        final Map<Crate, Integer> pendingKeys = cratesPlayer.getPendingKeys();
        if (pendingKeys.isEmpty()) {
            sender.sendMessage(cratesConfig.getNoKeysPending());
        } else {
            int amount = pendingKeys.values().stream().mapToInt(Integer::intValue).sum();
            sender.sendMessage(cratesConfig.getCheckSuccess(amount));
        }
    }

    @Override
    public String getName() {
        return "check";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getCheckDescription();
    }

    @Override
    public String getArgs() {
        return "";
    }

    @Override
    public boolean requireAdmin() {
        return false;
    }

    @Override
    public boolean requirePlayer() {
        return true;
    }

    @Override
    public int getArgCount() {
        return 0;
    }
}
