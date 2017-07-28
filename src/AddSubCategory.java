
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AddSubCategory extends javax.swing.JFrame
{

    public AddSubCategory()
    {
        initComponents();
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

        setTitle("Add Sub Category");
        addCategoryCb();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        selectCategoryCb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        subCategoryTf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        subDescTa = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        addBt = new javax.swing.JButton();
        cancelBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText(" Category");

        jLabel2.setText("Sub Category");

        subDescTa.setColumns(20);
        subDescTa.setRows(5);
        jScrollPane1.setViewportView(subDescTa);

        jLabel3.setText("Description");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectCategoryCb, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subCategoryTf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(addBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(cancelBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(selectCategoryCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(subCategoryTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelBt)
                    .addComponent(addBt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBtActionPerformed
    {//GEN-HEADEREND:event_addBtActionPerformed
        if (selectCategoryCb.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(this, "Select a Category");
            return;
        }
        if (subCategoryTf.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Enter a name");
            return;
        }
        addSubCat();
    }//GEN-LAST:event_addBtActionPerformed

    void addSubCat()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from sub_category");
            rs.moveToInsertRow();
            rs.updateString("category_name", selectCategoryCb.getSelectedItem().toString());
            rs.updateString("sub_cat_name", subCategoryTf.getText().trim());
            rs.updateString("sub_cat_desc", subDescTa.getText().trim());
            rs.insertRow();
            rs.close();
            stmt.close();
            conn.close();
            new ProductMenu();
            dispose();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void cancelBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelBtActionPerformed
    {//GEN-HEADEREND:event_cancelBtActionPerformed
        if (subCategoryTf.getText().isEmpty() && selectCategoryCb.getSelectedIndex() == 0)
        {
            new ProductMenu();
            dispose();
            return;
        }

        Object[] buttons =
        {
            "Save", "Don't Save", "Cancel"
        };

        int n = JOptionPane.showOptionDialog(this, "Do you want to save changes?", "SubCategory", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        if (n == JOptionPane.YES_OPTION)
        {
            addBtActionPerformed(evt);
        } else if (n == JOptionPane.NO_OPTION)
        {
            new ProductMenu();
            dispose();
        }
        return;
    }//GEN-LAST:event_cancelBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBt;
    private javax.swing.JButton cancelBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> selectCategoryCb;
    private javax.swing.JTextField subCategoryTf;
    private javax.swing.JTextArea subDescTa;
    // End of variables declaration//GEN-END:variables

    void addCategoryCb()
    {

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select category_name from category");
            selectCategoryCb.addItem("Select category");
            while (rs.next())
            {
                selectCategoryCb.addItem(rs.getString("category_name"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
