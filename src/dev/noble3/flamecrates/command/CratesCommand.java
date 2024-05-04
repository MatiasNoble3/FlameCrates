package dev.noble3.flamecrates.command;

import org.bukkit.command.CommandSender;

public interface CratesCommand {
    void execute(CommandSender sender, String label, String[] args);

    String getName();

    String getDescription();

    String getArgs();

    int getArgCount();

    boolean requireAdmin();

    boolean requirePlayer();
}
