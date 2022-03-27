package src.server.packets;

import org.json.JSONObject;
import src.server.ApplicationServer;
import src.server.Match;
import src.server.User;
import src.util.Packet;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class SearchPacket extends Packet {

    public SearchPacket() {
        super("SEAR");
    }

    public void receive(final JSONObject object, final User user) {
        user.setSearchesMatch(object.getBoolean("payload"));
        this.setPayload("halt");

        if (user.searchesMatch()) {
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
                        final Match match = new Match(user, u);
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
