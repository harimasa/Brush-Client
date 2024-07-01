package ru.femboypig.utils.events;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.femboypig.utils.interfaces.Cancellable;
import ru.femboypig.utils.interfaces.instance;
import ru.femboypig.utils.listeners.Listener;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static ru.femboypig.BrushClient.LOGGER;

public class EventBus implements instance {

    private final ConcurrentLinkedQueue<Listener> subscribedListeners;

    public EventBus() {
        this.subscribedListeners = new ConcurrentLinkedQueue<>();
    }

    public void subscribe(Listener listener) {
        if (listener == null || subscribedListeners.contains(listener))
            return;
        subscribedListeners.add(listener);
    }

    public void unsubscribe(Listener listener) {
        subscribedListeners.remove(listener);
    }

    public <E extends Event> boolean pass(E event) {
        listeners().forEach(listener -> {
            List<Method> methods = Arrays
                    .stream(listener.getClass().getDeclaredMethods())
                    .filter(Objects::nonNull)
                    .filter(method -> method.isAnnotationPresent(EventHandler.class))
                    .sorted(Comparator.comparing(method -> ((Method) method).getAnnotation(EventHandler.class).priority()).reversed())
                    .toList();

            methods.forEach(method -> {
                this.tryInvoke(method, listener, event);
            });
        });

        return event instanceof Cancellable c && c.isCancelled();
    }

    public <C extends CallbackInfo, E extends Event> void passWithCallbackInfo(C info, E event, Consumer<CallbackInfo> action) {
        pass(event);
        if (event instanceof Cancellable c && c.isCancelled()) {
            action.accept(info);
        }
    }

    public <C extends CallbackInfo, E extends Event> void passWithCallbackInfo(C info, E event) {
        passWithCallbackInfo(info, event, CallbackInfo::cancel);
    }

    private <E extends Event> void tryInvoke(Method method, Listener listener, E event) {
        try {
            if (!isValid(method, event)) return;
            method.setAccessible(true);
            method.invoke(listener, event);
        }
        catch (Exception ex) {
            LOGGER.error(method.getName(), listener.getClass().getSimpleName(), event.getClass().getSimpleName());
        }
    }

    private <E extends Event> boolean isValid(Method method, E event) {
        if (method == null || event == null) return false;
        if (!method.isAnnotationPresent(EventHandler.class)) return false;
        if (method.getReturnType() != void.class) return false;
        if (method.getParameterCount() != 1) return false;
        return method.getParameters()[0].getType() == event.getClass();
    }

    public Set<Listener> listeners() {
        return new HashSet<>(subscribedListeners);
    }
    public void addListener(Listener listener) {
        subscribe(listener);
    }
    public void removeListener(Listener listener) {
        unsubscribe(listener);
    }
}