package com.syntaxphoenix.spigot.smoothtimber.compatibility.coreprotect;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.plugin.Plugin;

import net.coreprotect.CoreProtectAPI;

public class CoreCompat_v1_13_x implements ICoreCompat {

    private final CoreProtectAPI api;

    protected CoreCompat_v1_13_x(final Plugin plugin) {
        api = ((net.coreprotect.CoreProtect) plugin).getAPI();
    }

    @Override
    public void logRemoval(final String user, final Location location, final BlockState block) {
        api.logRemoval(user, location, block.getType(), block.getBlockData());
    }

}
