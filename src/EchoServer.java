package src;

import src.server.Server;

/**
 * @author MEL
 * @version 2012-05-02
 */
public class EchoServer extends Server {
    private final String ENDE = "*bye*"; // final bedeutet, dass das eine Konstante ist, nicht mehr ver�nderbar

    /**
     * Constructor for objects of class src.EchoServer
     */
    public EchoServer() {
        super(2000);
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Verbindungs-Anfrage von einem src.client.Client kommt.
     * wenn die Methode durchgef�hrt wurde, steht die verbindung.
     * die methode ist in server abstract und muss daher �berschrieben werden.
     *
     */
    public void processNewConnection(String pClientIP, int pClientPort) {
        this.send(pClientIP, pClientPort, "Willkommen " + pClientIP + "auf Port " + pClientPort + " beim Echo-src.server.Server!");  // sendet Nachricht an src.client.Client
        System.out.println("Willkommen " + pClientIP + "auf Port " + pClientPort + " beim Echo-src.server.Server!"); // gibt alles auf der src.server.Server-Konsole aus
    }

    /**
     * Diese Methode wird aufgerufen, wenn eine Anfrage von einem src.client.Client kommt
     * Auch sie �berschreibt die Methode der abtrakten Serverklasse und gibt (weil ich ja ein Echo-src.server.Server bin)
     * einfach die Nachricht, die ich bekommen habe, wieder zur�ck
     */
    public void processMessage(String pClientIP, int pClientPort, String pMessage){
        if (pMessage.equals(ENDE)){
            this.closeConnection(pClientIP, pClientPort);   // hier wird die Verbindung beendet. Methode aus der Oberklasse.
        } else{
            this.send(pClientIP, pClientPort, pClientIP + " " + pClientPort + ": " + pMessage); // sendet Nachricht an src.client.Client
            System.out.println(" " + pClientPort + ": " + pMessage); // gibt alles auf der src.server.Server-Konsole aus
        }

    }


    /**
     * Diese Methode wird aufgerufen, wenn die Verbindung beendet wird.
     */
    public void processClosingConnection (String pClientIP, int pClientPort){
        this.send(pClientIP, pClientPort, pClientIP + " " + pClientPort + " auf Wiedersehen. ");
        System.out.println(pClientIP + " " + pClientPort + " aus Wiedersehen. ");
        this.send(pClientIP, pClientPort, ENDE);
    }
}
