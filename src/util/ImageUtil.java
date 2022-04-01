package src.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    public static ImageIcon getImageIcon(final String base64) {
        final byte[] bytes = Base64.getDecoder().decode(base64);
        return new ImageIcon(bytes);
    }

    public static String getImageBase64FromFile(final File file) throws IOException {
        final String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1); //Get file extension
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final BufferedImage readImage = ImageIO.read(file);
        final Image scaledImage = readImage.getScaledInstance(96, 96, Image.SCALE_DEFAULT);


        ImageIO.write(toBufferedImage(scaledImage), fileExtension, byteArrayOutputStream);

        System.out.println("Base64:" + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    /**
     * Converts a given Image into a BufferedImage
     * Taken from: https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
