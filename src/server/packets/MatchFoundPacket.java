package src.server.packets;


import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchFoundPacket extends Packet {

    public MatchFoundPacket() {
        super("MFND");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
    }

    @Override
    public void send() {
        setPayload("match found");
    }
}
