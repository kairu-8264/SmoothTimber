package com.syntaxphoenix.spigot.smoothtimber.utilities.task;

import static com.syntaxphoenix.spigot.smoothtimber.config.config.CutterConfig.GLOBAL_DEBUG;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.syntaxphoenix.spigot.smoothtimber.config.config.CutterConfig;
import com.syntaxphoenix.spigot.smoothtimber.utilities.PluginUtils;

public abstract class AbstractTask implements Runnable {
    
    private final CountDownLatch latch = new CountDownLatch(1);

    @Override
    public final void run() {
        if (latch.getCount() == 0) {
            return;
        }
        try {
            doRun();
        } catch(Exception exp) {
            if (GLOBAL_DEBUG) {
                PluginUtils.sendConsoleError("Something went wrong while running a object task", exp);
            }
        } finally {
            latch.countDown();
        }
    }
    
    protected abstract void doRun() throws Exception;
    
    protected void await() {
        await(CutterConfig.GLOBAL_SYNC_TIME);
    }
    
    protected void await(long waitingMillis) {
        if (latch.getCount() == 0) {
            return;
        }
        try {
            latch.await(waitingMillis, TimeUnit.MILLISECONDS);
        } catch (final InterruptedException e) {
            throw new IllegalStateException("Thread interrupted", e);
        }
    }

}
