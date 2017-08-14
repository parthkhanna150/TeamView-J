import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ServerFrame extends javax.swing.JFrame {

    public ServerFrame() {
        initComponents();
        setSize(500, 500);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        start.setText("Start Server");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start);
        start.setBounds(120, 50, 140, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        MyServer job = new MyServer();
        Thread t1 = new Thread(job);
        t1.start();        // TODO add your handling code here:
    }//GEN-LAST:event_startActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton start;
    // End of variables declaration//GEN-END:variables
class MyServer implements Runnable {

        ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(4200);
                System.out.println("Server started");
                while (true) {
                    Socket socket = serverSocket.accept();
                    clientHandler ch = new clientHandler(socket);
                    Thread tt = new Thread(ch);
                    tt.start();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        class clientHandler implements Runnable {

            Socket sock1;
            DataOutputStream dos;
            DataInputStream dis;

            clientHandler(Socket sock) {
                sock1 = sock;
            }

            @Override
            public void run() {
                try {
                    dis = new DataInputStream(sock1.getInputStream());
                    dos = new DataOutputStream(sock1.getOutputStream());
                    while (true) {
                        String msg = dis.readLine();
                        if (msg.equals("firstmessage")) {
                            break;
                        } else if (msg.equals("Send Screen Size")) {
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            int width = (int) screenSize.getWidth();
                            int height = (int) screenSize.getHeight();
                            dos.writeBytes("Receive Screen Size\r\n");
                            dos.writeInt(width);
                            dos.writeInt(height);

                            SendPhotos sendPhotos = new SendPhotos(sock1);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        class SendPhotos implements Runnable {

            Socket socket;
            DataOutputStream dos;
            DataInputStream dis;

            public SendPhotos(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try {
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                    Robot rob = new Robot();
                    int count = 0;
                    while (true) {
                        String msg = dis.readLine();
                        if (msg.equals("sendscreen")) {

                            Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                            BufferedImage bi = rob.createScreenCapture(rect);
                            File f = new File("e://Science Society//" + count + ".jpg");
                            ImageIO.write(bi, "jpg", f);

                            dos.writeBytes("receivescreen\r\n");

                            long filesize = f.length();
                            
                            dos.writeLong(filesize);

                            count++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
