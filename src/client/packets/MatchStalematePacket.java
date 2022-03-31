package src.client.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;
import src.util.eventapi.EventManager;
import src.util.events.MatchStalemateEvent;

public class MatchStalematePacket extends Packet {

    public MatchStalematePacket() {
        super("MTCS");
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        if (input.hasPayload()) {
            EventManager.call(new MatchStalemateEvent());
        }
    }

    @Override
    public void send() {
        //Not needed
    }
}
