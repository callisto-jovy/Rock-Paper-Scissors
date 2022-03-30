package src.client.packets;

import src.client.Player;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class SearchPacket extends Packet {

    public SearchPacket() {
        super("SEAR");
    }

    @Override
    public void receive(PacketUtil input, User parent) {

    }

    @Override
    public void send() {
        setPayload(Player.INSTANCE.searchesMatch());
    }
}
