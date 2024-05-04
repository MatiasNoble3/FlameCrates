package dev.noble3.flamecrates.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.crate.CrateManager;

class DisplaynameCommand implements CratesCommand {
    private final CrateManager crateManager;
    private final CratesConfig cratesConfig;

    DisplaynameCommand(CrateManager crateManager, final CratesConfig cratesConfig) {
        this.crateManager = crateManager;
        this.cratesConfig = cratesConfig;
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(cratesConfig.getCommandUsage(label, getName(), getArgs()));
            return;
        }

        String crateName = args[1];
        Crate crate = this.crateManager.getCrate(crateName);
        if (crate != null) {
            StringBuilder displayNameBuilder = new StringBuilder();
            for (int i = 2; i < args.length; i++) {
                String arg = ChatColor.translateAlternateColorCodes('&', args[i]);
                displayNameBuilder.append(arg).append(" ");
            }
            String displayName = displayNameBuilder.toString().trim();
            crate.setDisplayName(displayName);
            crate.spawnHolograms();
            sender.sendMessage(cratesConfig.getDisplaynameSuccess(crateName, displayName));
        } else {
            sender.sendMessage(cratesConfig.getNoCrate());
        }
    }

    @Override
    public String getName() {
        return "displayname";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getDisplaynameDescription();
    }

    @Override
    public String getArgs() {
        return "<crate> <name>";
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
