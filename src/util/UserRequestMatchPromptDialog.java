package src.util;

import javax.swing.*;
import java.awt.*;

public class UserRequestMatchPromptDialog extends JPanel {

    /**
     * Create the panel.
     */
    public UserRequestMatchPromptDialog(final ImageIcon icon, final String username) {
        setForeground(Color.LIGHT_GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setForeground(new Color(50, 50, 50));


        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(icon);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(username);
        lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(lblNewLabel_1);

    }

}
