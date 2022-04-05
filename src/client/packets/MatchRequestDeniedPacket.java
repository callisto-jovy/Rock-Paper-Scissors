package src.client.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchRequestDeniedPacket extends Packet {

    public MatchRequestDeniedPacket() {
        super("MTRQDN");
    }


    @Override
    public void receive(PacketUtil input, User user) {
        //Noch zu implementieren.
    }

    @Override
    public void send() {
        //Not needed.
    }
}
