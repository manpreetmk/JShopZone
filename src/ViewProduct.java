
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

public class ViewProduct extends javax.swing.JFrame
{

    ArrayList<Data> al;
    ProductTableModel tableModel1;
    int currentSelection = -1;

    public ViewProduct()
    {
        initComponents();
        setVisible(true);
        setTitle("View Product");
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

        al = new ArrayList<>();
        tableModel1 = new ProductTableModel();
        tblProduct.setModel(tableModel1);

        getProductData();
    }

    void getProductData()
    {
        try
        {
            al.clear();
            currentSelection = -1;
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from product");

            while (rs.next())
            {
                al.add(new Data(rs.getString("sub_cat_name"), rs.getString("product_id"), rs.getString("product_name"), rs.getString("product_desc"), rs.getDouble("product_price")));
            }
            tableModel1.fireTableDataChanged();
            rs.close();
            stmt.close();
            conn.close();
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
        tblProduct = new javax.swing.JTable();
        editBt = new javax.swing.JButton();
        deleteBt = new javax.swing.JButton();
        backBt = new javax.swing.JButton();
        viewStockBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProduct.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

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

        viewStockBt.setText("View Stock");
        viewStockBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                viewStockBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(editBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteBt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(backBt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewStockBt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBt)
                    .addComponent(deleteBt)
                    .addComponent(backBt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewStockBt)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editBtActionPerformed
    {//GEN-HEADEREND:event_editBtActionPerformed
        if (tblProduct.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        Object[] buttons =
        {
            "Apply Changes", "Discard Changes"
        };
        try
        {
            String productName = al.get(tblProduct.getSelectedRow()).productName;
            String productDesc = al.get(tblProduct.getSelectedRow()).productDesc;
            double productPrice = al.get(tblProduct.getSelectedRow()).price;

            OptionPanel pa = new OptionPanel(productName, productDesc, productPrice);
            int n = JOptionPane.showOptionDialog(this, pa, "Edit Product", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

            if (n == JOptionPane.YES_OPTION)
            {
                String desc = pa.descTa.getText().trim();
                String name = pa.nameTf.getText().trim();
                String priceString = pa.priceTf.getText().trim();
                if (desc.isEmpty() || name.isEmpty() || priceString.isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Insufficient Information");
                    return;
                }
                for (int i = 0; i < priceString.length(); i++)
                {
                    char c = priceString.charAt(i);
                    if( c < '0' || c > '9')
                    {
                        if(c == '.')
                            continue;
                        JOptionPane.showMessageDialog(pa, "Invalid Price");
                        return;
                    }
                }
                Double price = Double.parseDouble(priceString);
                editProduct(productName, name, desc, price);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_editBtActionPerformed

    private void deleteBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBtActionPerformed
    {//GEN-HEADEREND:event_deleteBtActionPerformed
        if (tblProduct.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Select a Row to Delete");
            return;
        }
        String productName = al.get(tblProduct.getSelectedRow()).productName;
        int n = JOptionPane.showConfirmDialog(this, "Sure To Delete " + productName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == JOptionPane.YES_OPTION)
        {
            new DatabaseFunctions().deleteRow("product", "product_name", productName);
            getProductData();

        }
    }//GEN-LAST:event_deleteBtActionPerformed


    private void backBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_backBtActionPerformed
    {//GEN-HEADEREND:event_backBtActionPerformed

        new ProductMenu();
        dispose();
    }//GEN-LAST:event_backBtActionPerformed

    private void viewStockBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_viewStockBtActionPerformed
    {//GEN-HEADEREND:event_viewStockBtActionPerformed
        if (tblProduct.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        String name = al.get(tblProduct.getSelectedRow()).productName;
        String id = al.get(tblProduct.getSelectedRow()).productId;
        new ViewStock(name, id);
        dispose();
    }//GEN-LAST:event_viewStockBtActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblProductMouseClicked
    {//GEN-HEADEREND:event_tblProductMouseClicked
        if (tblProduct.getSelectedRow() == currentSelection)
        {
            tblProduct.clearSelection();
        }
        currentSelection = tblProduct.getSelectedRow();
    }//GEN-LAST:event_tblProductMouseClicked

    void editProduct(String oldName, String productName, String desc, Double price)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from product where product_name = '" + oldName + "'");
            if (rs.next())
            {
                rs.updateString("product_name", productName);
                rs.updateString("product_desc", desc);
                rs.updateDouble("product_price", price);
                rs.updateRow();
            }
            rs.close();
            stmt.close();
            conn.close();
            getProductData();
        } catch (Exception e)
        {
            if (e.toString().contains("For input string"))
            {
                JOptionPane.showMessageDialog(this, "Invalid Price");

            }

            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBt;
    private javax.swing.JButton deleteBt;
    private javax.swing.JButton editBt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProduct;
    private javax.swing.JButton viewStockBt;
    // End of variables declaration//GEN-END:variables

    private class ProductTableModel extends AbstractTableModel
    {

        String s[] =
        {
            "SubCategory Name", "ID", "Name", "Description", "Price"
        };

        public String getColumnName(int index)
        {
            return s[index];
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
                return al.get(row).subCatName;
            } else if (col == 1)
            {
                return al.get(row).productId;
            } else if (col == 2)
            {
                return al.get(row).productName;
            } else if (col == 3)
            {
                return al.get(row).productDesc;
            } else
            {
                return al.get(row).price;
            }

        }
    }

    private static class Data
    {

        String subCatName, productName, productDesc;
        String productId;
        Double price;

        Data(String subCatName, String productId, String productName, String productDesc, Double price)
        {
            this.subCatName = subCatName;
            this.productId = productId;
            this.productName = productName;
            this.productDesc = productDesc;
            this.price = price;
        }
    }

    public static void main(String[] args)
    {
        new ViewProduct();
    }

    class Panel extends JPanel
    {

    }
}
