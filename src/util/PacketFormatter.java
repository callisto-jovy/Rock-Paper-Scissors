package src.util;

import src.server.Packet;

public class PacketFormatter {

    public static String formatPacket(final Packet packet) {
        return packet.getData().toString() + "\r\n";
    }
}
