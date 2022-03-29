package src.client.packets;
//Client

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class MatchFoundPacket extends Packet {

    private final User enemy;
    
    public MatchFoundPacket(final User enemy) {
        super("MFND");
        this.enemy = enemy;
    }

    @Override
    public void receive(PacketUtil input, User parent) {
        parent.setSearchesMatch(false);
    }

    @Override
    public void send() {        
        
    }
}
