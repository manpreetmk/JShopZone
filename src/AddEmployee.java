
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JOptionPane;

public class AddEmployee extends javax.swing.JFrame
{

    public AddEmployee()
    {
        initComponents();
        setVisible(true);
        setTitle("Add Employee");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();

        int x = (int) ((screenWidth / 2) - (frameWidth / 2));
        int y = (int) ((screenHeight / 2) - (frameHeight / 2));
        setLocation(x, y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        nameTf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        passwordPf = new javax.swing.JPasswordField();
        numberTf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        emailTf = new javax.swing.JTextField();
        addBt = new javax.swing.JButton();
        cancelBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Name");

        jLabel3.setText("Password");

        jLabel4.setText("Contact Number");

        jLabel1.setText("Email ID");

        addBt.setText("Add");
        addBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addBtActionPerformed(evt);
            }
        });

        cancelBt.setText("Cancel");
        cancelBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameTf, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(passwordPf)
                    .addComponent(numberTf)
                    .addComponent(emailTf))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(addBt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(cancelBt, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordPf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(emailTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBt)
                    .addComponent(cancelBt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBtActionPerformed
    {//GEN-HEADEREND:event_addBtActionPerformed
        String phone = numberTf.getText().trim();
        if (nameTf.getText().trim().isEmpty() || passwordPf.getText().trim().isEmpty() || phone.trim().isEmpty() || emailTf.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insufficient Information");
            return;
        }
        for (int i = 0; i < phone.length(); i++)
        {
            if (phone.charAt(i) < '0' || phone.charAt(i) > '9')
            {
                JOptionPane.showMessageDialog(this, "Invalid Phone Number");
                return;
            }
        }
        if (!(emailTf.getText().contains("@") && emailTf.getText().contains(".") && emailTf.getText().indexOf('@') < emailTf.getText().lastIndexOf('.')))
        {
            JOptionPane.showMessageDialog(this, "Invalid Email-ID");
            return;
        }

        addEmployee();
    }//GEN-LAST:event_addBtActionPerformed

    private void cancelBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelBtActionPerformed
    {//GEN-HEADEREND:event_cancelBtActionPerformed

        if (nameTf.getText().isEmpty() && numberTf.getText().isEmpty() && passwordPf.getText().isEmpty() && emailTf.getText().isEmpty())
        {
            new EmployeeMenu();
            dispose();
            return;
        }

        Object[] buttons =
        {
            "Save", "Don't Save", "Cancel"
        };

        int n = JOptionPane.showOptionDialog(this, "Do you want to save changes?", "Add Product", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        if (n == JOptionPane.YES_OPTION)
        {
            addBtActionPerformed(evt);
        } else if (n == JOptionPane.NO_OPTION)
        {
            new EmployeeMenu();
            dispose();
        }
        return;
    }//GEN-LAST:event_cancelBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBt;
    private javax.swing.JButton cancelBt;
    private javax.swing.JTextField emailTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nameTf;
    private javax.swing.JTextField numberTf;
    private javax.swing.JPasswordField passwordPf;
    // End of variables declaration//GEN-END:variables

    private void addEmployee()
    {
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from employee");
            rs.moveToInsertRow();
            rs.updateString("employee_name", nameTf.getText().trim());
            rs.updateString("employee_password", new String(passwordPf.getPassword()));
            rs.updateString("employee_phone", numberTf.getText().trim());
            rs.updateString("employee_email", emailTf.getText().trim());
            rs.insertRow();
            rs.close();
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(this, " Added Successfully");
            new EmployeeMenu();
            dispose();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
