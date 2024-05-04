package com.syntaxphoenix.spigot.smoothtimber.compatibility.mcmmo;

import org.bukkit.block.BlockState;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.gmail.nossr50.skills.woodcutting.WoodcuttingManager;
import com.syntaxphoenix.spigot.smoothtimber.SmoothTimber;
import com.syntaxphoenix.spigot.smoothtimber.compatibility.CompatibilityAddon;
import com.syntaxphoenix.spigot.smoothtimber.utilities.plugin.PluginPackage;

public class McMmo extends CompatibilityAddon {

    private Listener chopListener;

    @Override
    public void onEnable(final PluginPackage pluginPackage, final SmoothTimber smoothTimber) throws Exception {
        smoothTimber.getServer().getPluginManager().registerEvents(chopListener = createChopListener(pluginPackage), smoothTimber);
    }

    @Override
    public void onDisable(final SmoothTimber smoothTimber) throws Exception {
        if (chopListener != null) {
            HandlerList.unregisterAll(chopListener);
        }
    }
    
    private Listener createChopListener(PluginPackage pluginPackage) {
        try {
            WoodcuttingManager.class.getMethod("processBonusDropCheck", BlockState.class);
            return new McMmoChopListener_v1();
        } catch (NoSuchMethodException | RuntimeException e) {
            return new McMmoChopListener_v2(pluginPackage.getPlugin());
        }
        
    }

}