package me.bigfatman.joe.packet.impl;

public abstract class WrappedPacket implements IWrappedPacket{

    public final Class<?> object;

    protected WrappedPacket(Class<?> object) {
        this.object = object;
    }
}
