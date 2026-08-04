package wtf.relore.client.events.impl;

import wtf.relore.client.events.Event;

public class EventBinding extends Event {

    private final int key;
    private final BindType bindType;

    public EventBinding(int key, BindType bindType) {
        this.key = key;
        this.bindType = bindType;
    }

    public int getKey() {
        return key;
    }

    public BindType getBindType() {
        return bindType;
    }

    public boolean isKeyDown(int button) {
        return this.key == button;
    }
    public enum BindType {
        KEYBOARD,
        MOUSE
    }
}
