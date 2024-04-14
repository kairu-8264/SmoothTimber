package com.syntaxphoenix.spigot.smoothtimber.compatibility.coreprotect;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public interface CoreCompat {

    void logRemoval(String user, Location location, BlockState block);

}
