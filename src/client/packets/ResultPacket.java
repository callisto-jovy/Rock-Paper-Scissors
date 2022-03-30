package src.client.packets;


import src.server.User;
import src.util.Packet;
import src.util.PacketUtil;

public class ResultPacket extends Packet {

    public ResultPacket() {
        super("RSLT");
    }

    @Override
    public void receive(PacketUtil input, User parent) {

    }

    @Override
    public void send() {

    }
}
