package src.server.packets;


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
    }

    @Override
    public void send() {        
        setPayload(enemy.toJSON());
    }
}
