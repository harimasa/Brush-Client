package ru.femboypig.modules;

import ru.femboypig.utils.events.EventBus;

public class Func extends Module {
    public static EventBus eventBus = new EventBus();

    public Func(String name, String description) {
        super(name, description);
    }
}