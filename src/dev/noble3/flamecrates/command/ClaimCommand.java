package dev.noble3.flamecrates.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.player.CratesPlayer;
import dev.noble3.flamecrates.player.CratesPlayerManager;

class ClaimCommand implements CratesCommand {
    private final CratesPlayerManager cratesPlayerManager;
    private final CratesConfig cratesConfig;

    ClaimCommand(final CratesPlayerManager cratesPlayerManager, final CratesConfig cratesConfig) {
        this.cratesPlayerManager = cratesPlayerManager;
        this.cratesConfig = cratesConfig;
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Entity)) {
            sender.sendMessage("This command can only be executed by an entity!");
            return;
        }

        final CratesPlayer cratesPlayer = this.cratesPlayerManager.getPlayer(((Entity) sender).getUniqueId());
        final int pendingKeys = cratesPlayer.getPendingKeys().size();

        if (pendingKeys == 0) {
            sender.sendMessage(cratesConfig.getNoKeysPending());
        } else {
            final int claimedKeys = cratesPlayer.claimKeys();

            if (claimedKeys > 0) {
                sender.sendMessage(cratesConfig.getClaimSuccess(claimedKeys));
            } else {
                sender.sendMessage(cratesConfig.getNoSpace());
            }
        }
    }

    @Override
    public String getName() {
        return "claim";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getClaimDescription();
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
