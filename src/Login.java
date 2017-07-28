
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends javax.swing.JFrame
{

    public Login()
    {
        initComponents();
        setTitle("Admin Login");
        setVisible(true);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
                
        int x = (int)((screenWidth/2)-(frameWidth/2));
        int y = (int)((screenHeight/2)-(frameHeight/2));
        setLocation(x,y);      
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernamelb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        passwordTf = new javax.swing.JPasswordField();
        usernameTf = new javax.swing.JTextField();
        loginBt = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        showPasswordCb = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usernamelb.setText("Username");

        jLabel1.setText("Password");

        passwordTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTfActionPerformed(evt);
            }
        });

        usernameTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTfActionPerformed(evt);
            }
        });

        loginBt.setText("Login");
        loginBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        jLabel2.setText("jShopZone");

        showPasswordCb.setText("Show Password");
        showPasswordCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPasswordCbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showPasswordCb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addComponent(loginBt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordTf, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(usernameTf))
                        .addGap(10, 10, 10))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernamelb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showPasswordCb)
                    .addComponent(loginBt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtActionPerformed

        String user = usernameTf.getText();
        String password = new String(passwordTf.getPassword());

        if (user.trim().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insufficient Information");
            return;
        }
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos","root","");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from admin where username = '" + user + "' and password = '" +password+ "'");
            
            if (rs.next())
            {
                if(!password.equals(rs.getString("password")))
                    
                {
                    JOptionPane.showMessageDialog(this, "Incorrect Password", "Login", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new POS();
                dispose();
            } else
            {
                JOptionPane.showMessageDialog(this, "Invalid Information", "Login", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_loginBtActionPerformed

    private void usernameTfActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_usernameTfActionPerformed
    {//GEN-HEADEREND:event_usernameTfActionPerformed

        loginBtActionPerformed(evt);
    }//GEN-LAST:event_usernameTfActionPerformed

    private void passwordTfActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_passwordTfActionPerformed
    {//GEN-HEADEREND:event_passwordTfActionPerformed

        loginBtActionPerformed(evt);
    }//GEN-LAST:event_passwordTfActionPerformed

    private void showPasswordCbActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_showPasswordCbActionPerformed
    {//GEN-HEADEREND:event_showPasswordCbActionPerformed

        if(showPasswordCb.isSelected())
            passwordTf.setEchoChar((char)0);
        else
            passwordTf.setEchoChar((char)'\u25cf');
        
    }//GEN-LAST:event_showPasswordCbActionPerformed

    public static void main(String args[])
    {
        try
        {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    new Login().setVisible(true);
                }
            });
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginBt;
    private javax.swing.JPasswordField passwordTf;
    private javax.swing.JCheckBox showPasswordCb;
    private javax.swing.JTextField usernameTf;
    private javax.swing.JLabel usernamelb;
    // End of variables declaration//GEN-END:variables
}
