package src.server.packets;

import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class SearchPacket extends Packet {

    public SearchPacket() {
        super("SEAR");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        parent.setSearchesMatch(getPayloadBoolean());
        this.setPayload("halt");

        if (parent.searchesMatch()) {
            ApplicationServer.INSTANCE.matchQueue.add(parent);
        }
    }

    public void send() {

    }


}
