package wtf.relore.client.events;

import wtf.relore.structure.utils.interfaces.IClient;

public abstract class Event implements IClient {
    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }

    public void call() {
        eventBus.post(this);
    }
}
