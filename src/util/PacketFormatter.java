package src.util;

public class PacketFormatter {

    public static String formatPacket(final Packet packet) {
        return packet.getData().toString() + "\r\n";
    }
}
