
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class AddCategory extends javax.swing.JFrame
{

   
    public AddCategory()
    {
        initComponents();
        setVisible(true);
        setTitle("Add Category");
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nameTf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTa = new javax.swing.JTextArea();
        addBt = new javax.swing.JButton();
        addCancelBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("Description");

        descriptionTa.setColumns(20);
        descriptionTa.setRows(5);
        jScrollPane1.setViewportView(descriptionTa);

        addBt.setText("Add");
        addBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addBtActionPerformed(evt);
            }
        });

        addCancelBt.setText("Cancel");
        addCancelBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addCancelBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(addBt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addCancelBt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addCancelBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBtActionPerformed
    {//GEN-HEADEREND:event_addBtActionPerformed
        if(nameTf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insufficient Information");
            return;
        }
          try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from category");
            while(rs.next())
            {
                if(rs.getString("category_name").equals(nameTf.getText()))
                {
                    JOptionPane.showMessageDialog(this, "Category Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            rs.moveToInsertRow();
            rs.updateString("category_name",nameTf.getText().trim());
            rs.updateString("category_desc",descriptionTa.getText().trim());
            
            rs.insertRow();
            
            rs.close();
            stmt.close();
            conn.close();
            
            dispose();
            new ProductMenu();
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_addBtActionPerformed

    private void addCancelBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addCancelBtActionPerformed
    {//GEN-HEADEREND:event_addCancelBtActionPerformed
        if (nameTf.getText().isEmpty())
        {
            new ProductMenu();
            dispose();
            return;
        }
        Object[] buttons =
        {
            "Save", "Don't Save", "Cancel"
        };

        int n = JOptionPane.showOptionDialog(this, "Do you want to save the changes?", "jShopZone", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        if (n == JOptionPane.YES_OPTION)
        {
            addBtActionPerformed(evt);
        } else if (n == JOptionPane.NO_OPTION)
        {
            new ProductMenu();
            dispose();
        }
    }//GEN-LAST:event_addCancelBtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBt;
    private javax.swing.JButton addCancelBt;
    private javax.swing.JTextArea descriptionTa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameTf;
    // End of variables declaration//GEN-END:variables
}
