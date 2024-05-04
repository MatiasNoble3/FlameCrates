package dev.noble3.flamecrates.util;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;

public class StringUtil {
  public static String replace(String string, final Placeholder... placeholders) {
    for (final Placeholder placeholder : placeholders) {
      string = string.replace(placeholder.getReplaced(), placeholder.getReplacement());
    }

    return string;
  }

  public static List<String> replace(final List<String> strings, final Placeholder... placeholders) {
    return strings.stream()
                  .map(s -> replace(s, placeholders))
                  .collect(Collectors.toList());
}

public static List<String> translateColorCodes(final List<String> strings) {
    return strings.stream()
                  .map(s -> ChatColor.translateAlternateColorCodes('&', s))
                  .collect(Collectors.toList());
}

  public static String translateColorCodes(final String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
}
