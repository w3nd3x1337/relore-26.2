package wtf.relore.structure.utils.interfaces;

import net.minecraft.client.Minecraft;
import wtf.relore.Relore;
import wtf.relore.client.events.EventBus;

public interface IClient {
    Minecraft mc = Minecraft.getInstance();
    Relore relore = Relore.getInstance();
    EventBus eventBus = relore.getEventBus();
}
