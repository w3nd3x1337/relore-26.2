package wtf.relore.client.events;

import java.lang.reflect.Method;
public record Invocation(Object listener, Method method, int priority) {
}
