package com.notheif.events;

import org.bukkit.event.Listener;

public abstract class SubEvent implements Listener{

    public abstract String name();
    public abstract String description();

}
