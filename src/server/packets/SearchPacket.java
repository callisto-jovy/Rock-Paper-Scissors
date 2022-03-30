package src.server.packets;

import src.server.ApplicationServer;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;


public class SearchPacket extends Packet {

    public SearchPacket() {
        super("SEAR");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if (input.hasPayload()) {
            parent.setSearchesMatch(input.basicData.getBoolean("payload")); //Idk what kind of fuckery is going on, but input.getPayloadBoolean() will remove the key, somehow.
            if (parent.searchesMatch()) {
                ApplicationServer.INSTANCE.matchQueue.add(parent);
            }
            this.setPayload("halt");
        }
    }

    public void send() {
        //Empty, the server does not need to send any search packets
    }

}
