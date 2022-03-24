package src;

import src.client.Connection;
import src.server.ApplicationServer;

/**
 * Basic unit tests; for now just to make sure that the packet system is working.
 */
public class TestUnit {

    public static void main(String[] args) {
        final ApplicationServer applicationServer = new ApplicationServer();
        final Connection connection = new Connection("localhost", 80);
        connection.send("{\"status_code\":200,\"id\":\"LIST\"}");
        
        
        System.out.println(connection.receive());
    }

}
