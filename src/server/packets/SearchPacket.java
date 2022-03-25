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
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                ApplicationServer.INSTANCE.userList.toFirst();
                while (ApplicationServer.INSTANCE.userList.hasAccess()) {
                    final User u = ApplicationServer.INSTANCE.userList.getContent();
                    if (u.searchesMatch()) {
                        //new match found
                        final Match match = new Match(parent, u);
                        ApplicationServer.INSTANCE.matchList.append(match);
                        match.start();
                        return;
                    }
                    ApplicationServer.INSTANCE.userList.next();
                }
            });
        }
    }

    public void send() {

    }


}
