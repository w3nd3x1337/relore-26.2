package wtf.relore.client.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final ConcurrentHashMap<Class<? extends Event>, List<Invocation>> cache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Object, List<Invocation>> objectMap = new ConcurrentHashMap<>();

    public void register(Object listener) {
        if (objectMap.containsKey(listener)) return;

        List<Invocation> discoveredInvocations = new ArrayList<>();
        Method[] methods = listener.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (!method.isAnnotationPresent(EventHook.class)) continue;

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 1) continue;

            if (!Event.class.isAssignableFrom(parameters[0])) continue;

            @SuppressWarnings("unchecked")
            Class<? extends Event> eventClass = (Class<? extends Event>) parameters[0];
            method.setAccessible(true);

            EventHook annotation = method.getAnnotation(EventHook.class);
            int priorityValue = annotation.priority();

            Invocation invocation = new Invocation(listener, method, priorityValue);

            discoveredInvocations.add(invocation);

            cache.compute(eventClass, (key, list) -> {
                List<Invocation> newList = (list == null) ? new CopyOnWriteArrayList<>() : new CopyOnWriteArrayList<>(list);
                newList.add(invocation);
                newList.sort((a, b) -> Integer.compare(b.priority(), a.priority()));

                return newList;
            });
        }

        if (!discoveredInvocations.isEmpty()) {
            objectMap.put(listener, discoveredInvocations);
        }
    }

    public void unregister(Object listener) {
        List<Invocation> invocations = objectMap.remove(listener);
        if (invocations == null) return;

        for (Invocation invocation : invocations) {
            @SuppressWarnings("unchecked")
            Class<? extends Event> eventClass = (Class<? extends Event>) invocation.method().getParameterTypes()[0];

            cache.computeIfPresent(eventClass, (key, list) -> {
                list.remove(invocation);
                return list.isEmpty() ? null : list;
            });
        }
    }

    public void post(Event event) {
        List<Invocation> invocations = cache.get(event.getClass());

        if (invocations == null) return;

        for (Invocation invocation : invocations) {
            try {
                invocation.method().invoke(invocation.listener(), event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
