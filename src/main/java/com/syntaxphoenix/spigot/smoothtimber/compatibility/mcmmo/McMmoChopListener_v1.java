package com.syntaxphoenix.spigot.smoothtimber.compatibility.mcmmo;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.syntaxphoenix.spigot.smoothtimber.event.AsyncPlayerChopTreeEvent;
import com.syntaxphoenix.spigot.smoothtimber.platform.Platform;

import mcmmo.v1.com.gmail.nossr50.mcMMO;
import mcmmo.v1.com.gmail.nossr50.config.experience.ExperienceConfig;
import mcmmo.v1.com.gmail.nossr50.datatypes.player.McMMOPlayer;
import mcmmo.v1.com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import mcmmo.v1.com.gmail.nossr50.util.player.UserManager;

public class McMmoChopListener_v1 implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChopEvent(final AsyncPlayerChopTreeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final McMMOPlayer player = UserManager.getPlayer(event.getPlayer());
        if (player == null || !PrimarySkillType.WOODCUTTING.getPermissions(event.getPlayer())) {
            return;
        }

        Platform.getPlatform().regionalSyncTask(event.getTreeLocation(), () -> {
            for (final Location location : event.getBlockLocations()) {
                if (!hasWoodcuttingXP(location.getBlock()) || mcMMO.getPlaceStore().isTrue(location.getBlock().getState())) {
                    continue;
                }
                player.getWoodcuttingManager().processWoodcuttingBlockXP(location.getBlock().getState());
                player.getWoodcuttingManager().processHarvestLumber(location.getBlock().getState());
            }
        });
    }

    private boolean hasWoodcuttingXP(final Block block) {
        return ExperienceConfig.getInstance().doesBlockGiveSkillXP(PrimarySkillType.WOODCUTTING, block.getBlockData());
    }

}
