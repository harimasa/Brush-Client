package ru.femboypig.modules;

import ru.femboypig.utils.events.EventBus;
import ru.femboypig.utils.interfaces.instance;

public class Func extends Module implements instance {
    public static EventBus eventBus = new EventBus();

    public Func(String name, String description) {
        super(name, description);
    }
}