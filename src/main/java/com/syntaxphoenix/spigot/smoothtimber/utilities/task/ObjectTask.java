package com.syntaxphoenix.spigot.smoothtimber.utilities.task;

import java.util.function.Supplier;

public class ObjectTask<T> extends AbstractTask {

    private final Supplier<T> supplier;
    private volatile T value;

    public ObjectTask(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected void doRun() {
        value = supplier.get();
    }

    public T get() {
        await();
        return value;
    }

    public T get(long waitingMillis) {
        await(waitingMillis);
        return value;
    }

}
