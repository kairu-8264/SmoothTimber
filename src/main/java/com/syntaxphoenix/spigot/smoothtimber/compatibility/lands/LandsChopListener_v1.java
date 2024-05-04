package com.syntaxphoenix.spigot.smoothtimber.compatibility.lands;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.syntaxphoenix.spigot.smoothtimber.SmoothTimber;
import com.syntaxphoenix.spigot.smoothtimber.event.AsyncPlayerChopTreeEvent;
import com.syntaxphoenix.spigot.smoothtimber.event.reason.DefaultReason;

import lands.v1.api.integration.LandsIntegration;
import lands.v1.api.land.Area;
import lands.v1.api.role.enums.RoleSetting;

public final class LandsChopListener_v1 implements Listener {

    private final LandsIntegration integration;

    protected LandsChopListener_v1(final SmoothTimber smoothTimber) {
        this.integration = new LandsIntegration(smoothTimber);
    }

    @EventHandler(ignoreCancelled = true)
    public void onChopEvent(final AsyncPlayerChopTreeEvent event) {
        for (final Location location : event.getBlockLocations()) {
            final Area area = integration.getAreaByLoc(location);
            if (area == null) {
                continue;
            }
            if (!area.canSetting(event.getPlayer(), RoleSetting.BLOCK_BREAK, false)) {
                event.setCancelled(true);
                event.setReason(DefaultReason.LANDS);
                return;
            }
        }
    }
}
