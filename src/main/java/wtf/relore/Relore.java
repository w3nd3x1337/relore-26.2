package wtf.relore;

import net.fabricmc.api.ModInitializer;
import wtf.relore.client.events.EventBus;
import wtf.relore.structure.managers.GlobalManager;

public class Relore implements ModInitializer {

	private static final Relore instance = new Relore();
	private final EventBus eventBus = new EventBus();

	public static Relore getInstance() {
		return instance;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public void onInitialize() {
		init();
	}

	private void init() {
        eventBus.register(this);
		GlobalManager.register();
    }
}
