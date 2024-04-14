package com.syntaxphoenix.spigot.smoothtimber.utilities.task;

public class Task extends AbstractTask {

    private final Runnable runnable;

    public Task(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected void doRun() {
        runnable.run();
    }

    @Override
    public void await() {
        super.await();
    }

    @Override
    public void await(long waitingMillis) {
        super.await(waitingMillis);
    }

}
