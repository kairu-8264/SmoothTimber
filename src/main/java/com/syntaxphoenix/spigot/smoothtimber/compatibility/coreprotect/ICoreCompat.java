package com.syntaxphoenix.spigot.smoothtimber.compatibility.coreprotect;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public interface ICoreCompat {

    void logRemoval(final String user, final Location location, final BlockState block);
    
    boolean isPlayerPlaced(final BlockState state);

}
