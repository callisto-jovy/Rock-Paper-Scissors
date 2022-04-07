package src;

import src.client.Player;
import src.util.*;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class MainClient {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        LogUtil.getLogger().setLevel(Level.OFF);
        Player.INSTANCE.displayConnectPage();
    }
}
