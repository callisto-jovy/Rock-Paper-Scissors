package src.server.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchStalematePacket extends Packet {

    public MatchStalematePacket() {
        super("MTCS");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        //Not needed
    }

    @Override
    public void send() {
        setPayload("stalemate");
    }
}
