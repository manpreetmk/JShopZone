
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class splashScreen extends javax.swing.JFrame
{

    public splashScreen()
    {
        initComponents();
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = 400;
        int frameHeight = 300;
        int x = (int) ((screenWidth / 2) - (frameWidth / 2));
        int y = (int) ((screenHeight / 2) - (frameHeight / 2));
        setLocation(x, y);
        setSize(400, 300);
        pbSplash.setMaximum(100);
        Thread thread = new Thread(new SplashAnimation());
        thread.start();
    }
    
    class SplashAnimation implements Runnable
    {
        public void run()
        {
            for (int i = 1; i <= 100; i++)
            {
                try
                {
                    if(i == 1)
                    {
                        lbStatus.setText("Starting Server...");
                    }
                    else if(i == 30)
                    {
                        lbStatus.setText("Loading Modules...");
                    }
                    else if(i == 50)
                    {
                        lbStatus.setText("Starting Modules...");
                    }
                    else if(i == 70)
                    {
                        lbStatus.setText("Loading GUI...");
                    }
                    Thread.sleep(50);
                    pbSplash.setValue(i);
                    if(i == 100)
                    {
                        new Login();
                        splashScreen.this.dispose();
                    }
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lbStatus = new javax.swing.JLabel();
        lbBackground = new javax.swing.JLabel();
        pbSplash = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(255, 255, 255));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbStatus);
        lbStatus.setBounds(0, 260, 400, 30);

        lbBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splashScreen.jpg"))); // NOI18N
        getContentPane().add(lbBackground);
        lbBackground.setBounds(0, 0, 400, 290);
        getContentPane().add(pbSplash);
        pbSplash.setBounds(-10, 261, 420, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
//            {
//                if ("Nimbus".equals(info.getName()))
//                {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(splashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(splashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(splashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(splashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new splashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbBackground;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JProgressBar pbSplash;
    // End of variables declaration//GEN-END:variables
}
