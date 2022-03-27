package src.client.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;


public class MatchPacket extends Packet {

    public MatchPacket() {
        super("MATC");
    }

    @Override
    public void send() {

    }

    @Override
    public void receive(PacketUtil input, User user) {

    }

}
