package src.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class ImageUtil {
    /**
     * Converts a base64 string (which must contain image data) to an imageicon which is automatically resized
     * to a desired size
     *
     * @param base64 base64 string to be decoded with image data
     * @return an imageicon constructed with the data obtained from the base64 encoded string
     */
    public static ImageIcon getImageIcon(final String base64, final int width, final int height) {
        final byte[] bytes = Base64.getDecoder().decode(base64);
        final ImageIcon icon = new ImageIcon(bytes);
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    /**
     * Converts an image (via file) to a base64 string
     *
     * @param file image file to be encoded
     * @return a string with the image's data encoded in base64
     * @throws IOException IORead/IOWrite
     */
    public static String getImageBase64FromFile(final File file) throws IOException {
        final InputStream is = file.toURI().toURL().openStream(); //Open inputstream to the file
        final String encoded = Base64.getEncoder().encodeToString(is.readAllBytes()); //Encode all bytes in base64
        is.close(); //Close the inputstream
        return encoded;
    }

    public static ImageIcon getImageIconFromID(final int id) {
        return new ImageIcon(ImageUtil.class.getResource("/src/client/icons/96/" + id + ".png"));
    }
}
