import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.*;

import static java.lang.Thread.sleep;

class ScreenShot {
    public static void main(String[] args) {

        // Create Dropbox client
        final String ACCESS_TOKEN = "HuMfQEMPGnEAAAAAAAAAHGWEMXU0NePEFyaXeh4Hvb3ATR-ISWSva1ZpedYxROLJ";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        for (int i = 0; i < 5; i++) {
            try {
                //Create screenshots
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", os);

                // Upload "jpg" to Dropbox
                ByteArrayInputStream in = new ByteArrayInputStream(os.toByteArray());
                ScreenThread screen = new ScreenThread(client, in);
                screen.start();

                //Print Image size
                System.out.println("\n" + image.getWidth() + "x" + image.getHeight());

                sleep(5000);

            } catch (AWTException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}