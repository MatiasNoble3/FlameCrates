package dev.noble3.flamecrates.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;

import dev.noble3.flamecrates.config.CratesConfig;
import dev.noble3.flamecrates.crate.Crate;
import dev.noble3.flamecrates.crate.CrateManager;

class ContentsCommand implements CratesCommand {
    private final CrateManager crateManager;
    private final CratesConfig cratesConfig;

    ContentsCommand(CrateManager crateManager, CratesConfig cratesConfig) {
        this.crateManager = crateManager;
        this.cratesConfig = cratesConfig;
    }

    public void execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof HumanEntity)) {
            sender.sendMessage("This command can only be executed by a human entity!");
            return;
        }

        if (args.length < 2) {
            sender.sendMessage("Usage: /" + label + " <crate>");
            return;
        }

        String crateName = args[1];
        Crate crate = this.crateManager.getCrate(crateName);
        if (crate != null) {
            ((HumanEntity) sender).openInventory(crate.getInventory());
            sender.sendMessage(cratesConfig.getContentsSuccess(crateName));
        } else {
            sender.sendMessage(cratesConfig.getNoCrate());
        }
    }

    @Override
    public String getName() {
        return "contents";
    }

    @Override
    public String getDescription() {
        return cratesConfig.getContentsDescription();
    }

    @Override
    public String getArgs() {
        return "<crate>";
    }

    @Override
    public boolean requireAdmin() {
        return true;
    }

    @Override
    public boolean requirePlayer() {
        return true;
    }

    @Override
    public int getArgCount() {
        return 2;
    }
}
