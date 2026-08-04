package wtf.relore.client.modules;

import wtf.relore.structure.managers.GlobalManager;
import wtf.relore.structure.utils.interfaces.IClient;

public abstract class Module implements IClient {
    private String name;
    private String desc;
    private Group group;
    private int key;
    private boolean enabled;

    public Module(String name, String desc, Group group) {
        this.name = name;
        this.desc = desc;
        this.group = group;
        this.key = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void setEnabled(final boolean state) {
        if (this.enabled == state) return;
        this.enabled = state;

        try {
            if (state) {
                onEnable();
            } else {
                onDisable();
            }
        } catch (Exception e) {
            this.enabled = false;
        }
    }

    public void onEnable() {
        eventBus.register(this);
        this.enabled = true;
    }

    public void onDisable() {
        eventBus.unregister(this);
        this.enabled = false;
    }

    public enum Group{
        COMBAT("group.combat", "a"),
        MOVEMENT("group.movement", "b"),
        RENDER("group.render", "c"),
        PLAYER("group.player", "d"),
        MISC("group.misc", "f");
        private final String langKey, icons;

        Group(String langKey, String icons) {
            this.langKey = langKey;
            this.icons = icons;
        }

        public String getLangKey() {
            return langKey;
        }

        public String getIcons() {
            return icons;
        }

        public String getName() {
            return GlobalManager.getLanguageManager().translate(this.langKey);
        }
    }
}
