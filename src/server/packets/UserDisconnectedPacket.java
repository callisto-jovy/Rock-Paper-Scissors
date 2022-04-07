package src.server.packets;

import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class UserDisconnectedPacket extends Packet {

    private User user;

    public UserDisconnectedPacket(User user) {
        super("USDC");
        this.user = user;
    }


    public UserDisconnectedPacket() {
        super("USDC");
    }

    @Override
    public void receive(PacketUtil input, User parent) {

    }

    @Override
    public void send() {
        //the disconnected user
        setPayload(user.getName());
    }
}
