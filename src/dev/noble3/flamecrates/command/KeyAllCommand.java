package dev.noble3.flamecrates.command;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.crate.CrateManager;
import dev.noble3.flamecrates.player.CratesPlayer;
import dev.noble3.flamecrates.player.CratesPlayerManager;

class KeyAllCommand implements CratesCommand {
    private final CrateManager crateManager;
    private final CratesPlayerManager cratesPlayerManager;
    private final CratesConfig cratesConfig;
    private final Server server;

    KeyAllCommand(CrateManager crateManager, CratesPlayerManager cratesPlayerManager, CratesConfig cratesConfig,
                  Server server) {
        this.crateManager = crateManager;
        this.cratesPlayerManager = cratesPlayerManager;
        this.cratesConfig = cratesConfig;
        this.server = server;
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(cratesConfig.getCommandUsage(label, getName(), getArgs()));
            return;
        }

        try {
            String crateName = args[1];
            Crate crate = this.crateManager.getCrate(crateName);
            if (crate == null) {
                sender.sendMessage(cratesConfig.getNoCrate());
            } else {
                int amount = Integer.parseInt(args[2]);
                for (Player player : this.server.getOnlinePlayers()) {
                    CratesPlayer cratesPlayer = this.cratesPlayerManager.getPlayer(player.getUniqueId());
                    cratesPlayer.giveKeys(crate, amount);
                    player.sendMessage(cratesConfig.getReceivedKeys(sender.getName(), amount, crate.getDisplayName()));
                }
                sender.sendMessage(cratesConfig.getKeyallSuccess(amount, crateName));
            }
        } catch (NumberFormatException exception) {
            sender.sendMessage(cratesConfig.getInvalidNumber());
        }
    }

    @Override
    public String getName() {
        return "keyall";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getKeyallDescription();
    }

    @Override
    public String getArgs() {
        return "<crate> <amount>";
    }

    @Override
    public boolean requireAdmin() {
        return true;
    }

    @Override
    public boolean requirePlayer() {
        return false;
    }

    @Override
    public int getArgCount() {
        return 3;
    }
}
