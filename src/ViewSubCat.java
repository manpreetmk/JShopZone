
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

public class ViewSubCat extends javax.swing.JFrame
{

    ArrayList<Data> al;
    SubCategoryTableModel tableModel1;
    int currentSelection = -1;

    public ViewSubCat()
    {
        initComponents();
        setVisible(true);
        setTitle("View Sub Category");
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

        al = new ArrayList<>();
        tableModel1 = new SubCategoryTableModel();
        tblSubCat.setModel(tableModel1);
        getSubCatData();
    }

    void getSubCatData()
    {
        try
        {
            al.clear();
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from sub_category");

            while (rs.next())
            {
                al.add(new Data(rs.getString("category_name"), rs.getString("sub_cat_name"), rs.getString("sub_cat_desc")));
            }
            tableModel1.fireTableDataChanged();
            rs.close();
            stmt.close();
            conn.close();
            currentSelection = -1;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubCat = new javax.swing.JTable();
        editBt = new javax.swing.JButton();
        deleteBt = new javax.swing.JButton();
        backBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSubCat.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblSubCatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSubCat);

        editBt.setText("Edit");
        editBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                editBtActionPerformed(evt);
            }
        });

        deleteBt.setText("Delete");
        deleteBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteBtActionPerformed(evt);
            }
        });

        backBt.setText("Back");
        backBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                backBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(editBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBt)
                    .addComponent(deleteBt)
                    .addComponent(backBt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backBtActionPerformed
    {//GEN-HEADEREND:event_backBtActionPerformed

        new ProductMenu();
        dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void editBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editBtActionPerformed
    {//GEN-HEADEREND:event_editBtActionPerformed

        if (tblSubCat.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        Object[] buttons =
        {
            "Save", "Don't Save"
        };
        JTextArea taDescription = new JTextArea();
        String subCategoryName = al.get(tblSubCat.getSelectedRow()).subCatName;
        taDescription.setColumns(10);
        taDescription.setRows(5);
        JScrollPane sp = new JScrollPane(taDescription);
        int n = JOptionPane.showOptionDialog(this, sp, "Edit SubCategory", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        String desc = taDescription.getText().trim();
        if (n == JOptionPane.YES_OPTION)
        {
            
            if (desc.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Insufficient Information");
                return;
            }
            editSubCategory(subCategoryName, desc);
        }               

    }//GEN-LAST:event_editBtActionPerformed

    private void deleteBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBtActionPerformed
    {//GEN-HEADEREND:event_deleteBtActionPerformed

        if (tblSubCat.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a Row to Delete");
            return;
        }
        String subCategoryName = al.get(tblSubCat.getSelectedRow()).subCatName;
        int n = JOptionPane.showConfirmDialog(this, "Sure To Delete " + subCategoryName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION)
        {
//            deleteCategory(subCategoryName);
            new DatabaseFunctions().deleteRow("sub_category", "sub_cat_name", subCategoryName);
            getSubCatData();
        }

    }//GEN-LAST:event_deleteBtActionPerformed

    private void tblSubCatMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblSubCatMouseClicked
    {//GEN-HEADEREND:event_tblSubCatMouseClicked

        if (tblSubCat.getSelectedRow() == currentSelection)
        {
            tblSubCat.clearSelection();
        }
        currentSelection = tblSubCat.getSelectedRow();

    }//GEN-LAST:event_tblSubCatMouseClicked

    
    void editSubCategory(String subCategoryName, String desc)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from sub_category where sub_cat_name = '" + subCategoryName + "'");

            if (rs.next())
            {
                rs.updateString("sub_cat_desc", desc);
                rs.updateRow();
            }
            rs.close();
            stmt.close();
            conn.close();
            getSubCatData();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBt;
    private javax.swing.JButton deleteBt;
    private javax.swing.JButton editBt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSubCat;
    // End of variables declaration//GEN-END:variables

    private static class Data
    {

        String catName, subCatName, subCatDesc;

        public Data(String catName, String subCatName, String subCatDesc)
        {
            this.catName = catName;
            this.subCatName = subCatName;
            this.subCatDesc = subCatDesc;
        }
    }

    private class SubCategoryTableModel extends AbstractTableModel
    {

        String s[] =
        {
            "Category Name", "SubCategory Name", "SubCategory Desc"
        };

        public String getColumnName(int Index)
        {
            return s[Index];
        }

        @Override
        public int getRowCount()
        {
            return al.size();
        }

        @Override
        public int getColumnCount()
        {
            return s.length;
        }

        @Override
        public Object getValueAt(int row, int col)
        {
            if (col == 0)
            {
                return al.get(row).catName;
            } else if (col == 1)
            {
                return al.get(row).subCatName;
            } else
            {
                return al.get(row).subCatDesc;
            }
        }
    }
}
