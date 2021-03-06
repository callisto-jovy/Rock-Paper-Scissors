package src;
import src.client.Player;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.logging.*;

public class MainClient {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        LogManager.getLogManager().reset();
        Player.INSTANCE.displayConnectPage();
    }
}
