
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PingSingleIP implements Runnable {

    String ip;

    PingSingleIP(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec("ping " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader inputStream = new BufferedReader(isr);
            String s = "";
            // reading output stream of the command 
            while (true) {
                s = inputStream.readLine();
                if (s == null) {
                    break;
                }

                if (s.contains("TTL")) {
                    System.out.println(s);
                    break;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Robot rob = new Robot();
            for (int i = 0; i < 10; i++) {
                Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                BufferedImage bi = rob.createScreenCapture(rect);
                File f = new File("e://Science Society//" + i + ".jpg");
                ImageIO.write(bi, "jpg", f);
                Thread.sleep(1000);
            }

        } catch (Exception ex) {
            Logger.getLogger(PingSingleIP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
