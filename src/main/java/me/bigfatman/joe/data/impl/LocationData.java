package me.bigfatman.joe.data.impl;

import me.bigfatman.joe.packet.impl.client.WrappedPlayInFlyingPacket;

public class LocationData {

    public double currentX, currentY, currentZ, pastX, pastY, pastZ;
    public float currentYaw, currentPitch, pastYaw, pastPitch;


    public void handleLocation(WrappedPlayInFlyingPacket flyingPacket) {
        if (flyingPacket.pos) {
            this.pastX = this.currentX;
            this.pastY = this.currentY;
            this.pastZ = this.currentZ;

            this.currentX = flyingPacket.x;
            this.currentZ = flyingPacket.z;
            this.currentY = flyingPacket.y;
        }

        if (flyingPacket.look) {
            this.pastYaw = this.currentYaw;
            this.pastPitch = this.currentPitch;

            this.currentYaw = flyingPacket.yaw;
            this.currentPitch = flyingPacket.pitch;
        }
    }
}
