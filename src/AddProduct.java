
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddProduct extends javax.swing.JFrame
{
    
    public static void main(String[] args)
    {
        new AddProduct();
    }
    
    public AddProduct()
    {
        initComponents();
        setVisible(true);
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
        
        setTitle("Add Product");
        addCategoryCb();
    }
    
    private void addSubCategory(String category)
    {
        try
        {
            subCategoryCb.removeAllItems();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root","");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select sub_cat_name from sub_category where category_name = '" + category + "'");
            subCategoryCb.addItem("Select category");
            while (rs.next())
            {
                subCategoryCb.addItem(rs.getString("sub_cat_name"));
            }
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

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        subCategoryCb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        productNameTf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productDescTa = new javax.swing.JTextArea();
        addBt = new javax.swing.JButton();
        cancelBt = new javax.swing.JButton();
        categorySelectionCb = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        priceTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        productIdTf = new javax.swing.JTextField();
        browseTf = new javax.swing.JTextField();
        browseBt = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Category");

        jLabel2.setText("Product Name");

        jLabel4.setText("Description");

        productDescTa.setColumns(20);
        productDescTa.setRows(5);
        jScrollPane1.setViewportView(productDescTa);

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

        categorySelectionCb.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                categorySelectionCbItemStateChanged(evt);
            }
        });

        jLabel5.setText("Sub Category");

        priceTf.setForeground(new java.awt.Color(204, 204, 204));
        priceTf.setText("Enter price in Rs");
        priceTf.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                priceTfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                priceTfFocusLost(evt);
            }
        });

        jLabel6.setText("Price");

        jLabel7.setText("Product ID");

        browseBt.setText("Browse");
        browseBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                browseBtActionPerformed(evt);
            }
        });

        jLabel8.setText("Select Image");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelBt, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(priceTf, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(subCategoryCb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(categorySelectionCb, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(browseTf, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(browseBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(20, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(categorySelectionCb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subCategoryCb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(productIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productNameTf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(browseTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseBt)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBt)
                    .addComponent(cancelBt))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBtActionPerformed
    {//GEN-HEADEREND:event_addBtActionPerformed
        if (categorySelectionCb.getSelectedIndex() == 0 || subCategoryCb.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(this, "Select a Category");
            return;
        }
        if (productNameTf.getText().trim().isEmpty() || productIdTf.getText().trim().isEmpty() || browseTf.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insuffiecient Information");
            return;
        }        

//        new Thread(new CopyFunc()).start();
        new CopyFunc().run();
        
        addProduct();
    }//GEN-LAST:event_addBtActionPerformed

    private void cancelBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelBtActionPerformed
    {//GEN-HEADEREND:event_cancelBtActionPerformed
        if (productNameTf.getText().isEmpty() && categorySelectionCb.getSelectedIndex() == 0 && subCategoryCb.getSelectedIndex() == 0)
        {
            new ProductMenu();
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
            new ProductMenu();
            dispose();
        }
        return;
    }//GEN-LAST:event_cancelBtActionPerformed

    private void categorySelectionCbItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_categorySelectionCbItemStateChanged
    {//GEN-HEADEREND:event_categorySelectionCbItemStateChanged
        String categoryName = categorySelectionCb.getSelectedItem().toString();
        addSubCategory(categoryName);
    }//GEN-LAST:event_categorySelectionCbItemStateChanged

    private void priceTfFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_priceTfFocusGained
    {//GEN-HEADEREND:event_priceTfFocusGained
        if (priceTf.getText().equals("Enter price in Rs"))
        {
            priceTf.setText(null);
        }
        priceTf.setForeground(Color.BLACK);

    }//GEN-LAST:event_priceTfFocusGained

    private void priceTfFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_priceTfFocusLost
    {//GEN-HEADEREND:event_priceTfFocusLost
        
        if (priceTf.getText().trim().isEmpty())
        {
            Color c = new Color(204, 204, 204);
            priceTf.setForeground(c);
            priceTf.setText("Enter price in Rs");
        }
    }//GEN-LAST:event_priceTfFocusLost

    private void browseBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_browseBtActionPerformed
    {//GEN-HEADEREND:event_browseBtActionPerformed
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Images", "jpg", "png");
        JFileChooser jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(extensionFilter);
        int r = jfc.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            File f = jfc.getSelectedFile();
            String path = f.getPath();
            browseTf.setText(path);
        }
    }//GEN-LAST:event_browseBtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBt;
    private javax.swing.JButton browseBt;
    private javax.swing.JTextField browseTf;
    private javax.swing.JButton cancelBt;
    private javax.swing.JComboBox<String> categorySelectionCb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField priceTf;
    private javax.swing.JTextArea productDescTa;
    private javax.swing.JTextField productIdTf;
    private javax.swing.JTextField productNameTf;
    private javax.swing.JComboBox<String> subCategoryCb;
    // End of variables declaration//GEN-END:variables

    private void addProduct()
    {
        try
        {
            double price = Double.parseDouble(priceTf.getText().trim());
            if (price < 0)
            {
                JOptionPane.showMessageDialog(this, "Price Invalid");
                return;
            }
            String folder = null;
            String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                folder = "Documents" + File.separator;
            } else if (os.contains("Linux") || os.contains("Unix"))
            {
                folder = "";
            }
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "system");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from product");
            while(rs.next())
            {
                if(rs.getString("product_id").equals(productIdTf))
                {
                    JOptionPane.showMessageDialog(this, "Product ID Already Exists");
                    return;
                }
            }
            rs.moveToInsertRow();
            rs.updateString("product_id", productIdTf.getText() + "");
            rs.updateString("product_name", productNameTf.getText());
            rs.updateString("product_desc", productDescTa.getText());
            rs.updateDouble("product_price", price);
            rs.updateString("sub_cat_name", subCategoryCb.getSelectedItem().toString());
            rs.updateString("product_image", System.getProperty("user.home") + File.separator + folder + File.separator + "POS Images" + File.separator + productIdTf.getText() + ".png");
            rs.insertRow();
            rs.close();
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, productNameTf.getText() + " Added Successfully");
            new ProductMenu();
            dispose();
        } catch (Exception e)
        {
            if (e.toString().contains("For input string") || e.toString().contains("empty String"))
            {
                JOptionPane.showMessageDialog(this, "Invalid Price");
            }
            e.printStackTrace();
        }
    }
    
    private void addCategoryCb()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "root", "");
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select category_name from category");
            categorySelectionCb.addItem("Select category");
            while (rs.next())
            {
                categorySelectionCb.addItem(rs.getString("category_name"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    class CopyFunc implements Runnable
    {
        
        @Override
        public void run()
        {
            try
            {
                FileInputStream fis = new FileInputStream(browseTf.getText());
                File f = new File(browseTf.getText());
                
                String folder = null;
                String os = System.getProperty("os.name");
                if (os.contains("Windows"))
                {
                    folder = "Documents" + File.separator;
                } else if (os.contains("Linux") || os.contains("Unix"))
                {
                    folder = "";
                }
                String path = System.getProperty("user.home") + File.separator + folder + File.separator + "POS Images";
                if (!(new File(path).isDirectory()))
                {
                    new File(System.getProperty("user.home") + File.separator + folder + File.separator + "POS Images").mkdir();
                }
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.home") + File.separator + folder + File.separator + "POS Images" + File.separator + productIdTf.getText() + ".png");
                System.out.println(f.length());
                byte b[] = new byte[10000];
                while (true)
                {
                    int r = fis.read(b, 0, 10000);
                    if (r == -1)
                    {
                        break;
                    }
                    fos.write(b, 0, r);
                }
                fis.close();
                fos.close();
                System.out.println("File Copied");
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
