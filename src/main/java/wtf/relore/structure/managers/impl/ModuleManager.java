package wtf.relore.structure.managers.impl;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import wtf.relore.client.modules.Module;
import wtf.relore.client.modules.impl.movement.*;
import wtf.relore.client.modules.impl.player.*;
import wtf.relore.structure.utils.interfaces.IClient;

import java.util.List;

public class ModuleManager implements IClient {
    public static ObjectArrayList<Module> modules = new ObjectArrayList<>();

    public NoDelay noDelay = new NoDelay();
    public Sprint sprint = new Sprint();

    public ObjectArrayList<Module> getModules() {
        return modules;
    }

    public NoDelay getNoDelay() {
        return noDelay;
    }

    public void setNoDelay(NoDelay noDelay) {
        this.noDelay = noDelay;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }


    public void initialize() {
        this.add(
                sprint,
                noDelay
        );
    }

    private void add(final Module... mod) {
        this.modules.addAll(List.of(mod));
    }
}
