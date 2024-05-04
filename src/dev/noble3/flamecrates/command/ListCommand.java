package dev.noble3.flamecrates.command;

import org.bukkit.command.CommandSender;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.CrateManager;

class ListCommand implements CratesCommand {
    private final CrateManager crateManager;
    private final CratesConfig cratesConfig;

    ListCommand(CrateManager crateManager, CratesConfig cratesConfig) {
        this.crateManager = crateManager;
        this.cratesConfig = cratesConfig;
    }

    public void execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage(cratesConfig.getListSuccess(crateManager.getCratesNames()));
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getListDescription();
    }

    @Override
    public String getArgs() {
        return "";
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
        return 0;
    }
}
