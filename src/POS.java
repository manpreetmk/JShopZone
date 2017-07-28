
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class POS extends javax.swing.JFrame
{

    ArrayList<BillSheet> al;

    public POS()
    {
        initComponents();
        setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
        setResizable(false);
        int x = (int) ((screenWidth / 2) - (frameWidth / 2));
        int y = (int) ((screenHeight / 2) - (frameHeight / 2));
        setLocation(x, y);

        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        POSServer server = new POSServer();
//        Thread thread = new Thread(server);
//        thread.start();
        new Thread(new POSServer()).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton1 = new javax.swing.JButton();
        productMenuBt = new javax.swing.JButton();
        employeeMenuBt = new javax.swing.JButton();
        biilSheetBt = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        productMenuBt.setText("Product Menu");
        productMenuBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                productMenuBtActionPerformed(evt);
            }
        });

        employeeMenuBt.setText("Employee Menu");
        employeeMenuBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                employeeMenuBtActionPerformed(evt);
            }
        });

        biilSheetBt.setText("Generate Bill Sheet");
        biilSheetBt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                biilSheetBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productMenuBt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(employeeMenuBt, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(biilSheetBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productMenuBt)
                .addGap(29, 29, 29)
                .addComponent(employeeMenuBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(biilSheetBt)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productMenuBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_productMenuBtActionPerformed
    {//GEN-HEADEREND:event_productMenuBtActionPerformed
        new ProductMenu();
    }//GEN-LAST:event_productMenuBtActionPerformed

    private void employeeMenuBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_employeeMenuBtActionPerformed
    {//GEN-HEADEREND:event_employeeMenuBtActionPerformed
        new EmployeeMenu();
    }//GEN-LAST:event_employeeMenuBtActionPerformed

    private void biilSheetBtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_biilSheetBtActionPerformed
    {//GEN-HEADEREND:event_biilSheetBtActionPerformed
        al = new ArrayList<>();
        int billId = 0, totalItems = 0;
        double grandTotal = 0;
        Date date = null;
        String time = null;
        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Date currentDate = new Date(System.currentTimeMillis());
            ResultSet rs = stmt.executeQuery("select * from bill where date = '" + currentDate + "'");
            while (rs.next())
            {
                billId = rs.getInt("bill_id");
                grandTotal = rs.getDouble("total_amount");
                date = rs.getDate("date");
                time = rs.getString("time");
                Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                Statement stmt1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs1 = stmt1.executeQuery("select * from bill_detail where bill_id = " + billId);
                System.out.println("select * from bill_detail where bill_id = " + billId);
                while (rs1.next())
                {
                    totalItems++;
                    System.out.println(totalItems);
                }
                rs1.close();
                stmt1.close();
                con1.close();
            }
            al.add(new BillSheet(billId, totalItems, grandTotal, date, time));
            new GenerateBillSheet(al);
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        al.add(new BillSheet(billId, totalItems, grandTotal, date, time));
    }//GEN-LAST:event_biilSheetBtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton biilSheetBt;
    private javax.swing.JButton employeeMenuBt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton productMenuBt;
    // End of variables declaration//GEN-END:variables

    private class POSServer implements Runnable
    {

        public void run()
        {
            try
            {
                ServerSocket serverSocket = new ServerSocket(9000);
                System.out.println("Server Running");
                while (true)
                {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client Accepted");
//                    ClientHandler clientHandler = new ClientHandler(socket);
//                    Thread threadClientHandler = new Thread(clientHandler);
//                    threadClientHandler.start();
                    new Thread(new ClientHandler(socket)).start();
                }
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(POS.this, "Server Already Running");
                POS.this.dispose();
            }
        }
    }

    private class ClientHandler implements Runnable
    {

        String request;
        Socket socket;
        DataInputStream dis;
        DataOutputStream dos;

        public ClientHandler(Socket socket)
        {
            try
            {
                this.socket = socket;
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("New Client Here...");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void run()
        {
            try
            {
                while (true)
                {
                    request = dis.readLine();
                    if (request.equals("emp_login"))
                    {
                        String phoneNumber = dis.readLine();
                        String password = dis.readLine();
                        try
                        {
                            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "");
                            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from employee where employee_phone = '" + phoneNumber + "' and employee_password = '" + password + "'");
                            if (rs.next())
                            {
                                dos.writeBytes("emp_login_true\r\n");
                            } else
                            {
                                dos.writeBytes("emp_login_false\r\n");
                            }
                            rs.close();
                            stmt.close();
                            con.close();
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    } else if (request.equals("forgot_password"))
                    {
                        String email = dis.readLine();
                        try
                        {
                            String password = System.currentTimeMillis() + "";
                            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from employee where employee_email = '" + email + "'");
                            if (rs.next())
                            {
                                new SimpleMailDemo(email, "Password Reset - jShopZone", "Your Account Password is - " + password);
                                rs.updateString("employee_password", password);
                                rs.updateRow();
                            } else
                            {
                                dos.writeBytes("email_not_exist\r\n");
                                continue;
                            }
                            rs.close();
                            stmt.close();
                            con.close();
                            dos.writeBytes("email_sent\r\n");
                        } catch (Exception e)
                        {
                            dos.writeBytes("email_not_sent\r\n");
                            e.printStackTrace();
                        }
                    } else if (request.equals("product_id"))
                    {
                        String productId = dis.readLine();
                        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from product where product_id = '" + productId + "'");
                        if (rs.next())
                        {
                            String imagePath = rs.getString("product_image");
                            dos.writeBytes("product_id_true\r\n");
                            dos.writeBytes(rs.getString("product_name") + "\r\n");
                            dos.writeBytes(rs.getDouble("product_price") + "\r\n");
                            String subCatName = rs.getString("sub_cat_name");
                            dos.writeBytes(subCatName + "\r\n");
                            rs.close();
                            stmt.close();
                            con.close();

                            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                            Statement stmt1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs1 = stmt1.executeQuery("select * from sub_category where sub_cat_name = '" + subCatName + "'");
                            if (rs1.next())
                            {
                                dos.writeBytes(rs1.getString("category_name") + "\r\n");
                            }
                            rs1.close();
                            stmt1.close();
                            con1.close();

                            File img = new File(imagePath);
                            FileInputStream fis = new FileInputStream(img);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            byte[] buffer = new byte[1024 * 1024];
                            while (true)
                            {
                                int r = fis.read(buffer, 0, buffer.length);
                                if (r == -1)
                                {
                                    break;
                                }
                                baos.write(buffer, 0, r);
                            }
                            byte[] rawImage = baos.toByteArray();
                            dos.writeBytes("read_image\r\n");
                            dos.writeInt(rawImage.length);
                            dos.write(rawImage);
                        } else
                        {
                            dos.writeBytes("product_id_false\r\n");
                        }
                    } else if (request.equals("phone_number"))
                    {
                        String phone = dis.readLine();
                        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from customer where phone_number = '" + phone + "'");
                        if (rs.next())
                        {
                            dos.writeBytes("phone_number_true\r\n");
                            dos.writeBytes(rs.getString("customer_name") + "\r\n");
                            dos.writeBytes(rs.getString("email") + "\r\n");
                            dos.writeInt(Integer.parseInt(rs.getString("points")));
                        } else
                        {
                            dos.writeBytes("phone_number_false\r\n");
                        }
                        rs.close();
                        stmt.close();
                        con.close();
                    } else if (request.equals("add_customer"))
                    {
                        System.out.println("something");
                        String name = dis.readLine();
                        String phone = dis.readLine();
                        String email = dis.readLine();
                        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from customer");
                        rs.moveToInsertRow();
                        rs.updateString("customer_name", name);
                        rs.updateString("phone_number", phone);
                        rs.updateString("email", email);
                        rs.insertRow();
                        rs.close();
                        stmt.close();
                        con.close();
                    } else if (request.equals("bill_generate"))
                    {
                        ArrayList<ProductDetail> temp = new ArrayList<>();
                        int currentBillID = 0;
                        String phoneNumber = dis.readLine();
                        double totalAmount = dis.readDouble();
                        int newPoints = dis.readInt();
                        int listSize = dis.readInt();
                        System.out.println("here");
                        try
                        {
                            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from customer where phone_number = '" + phoneNumber + "'");
                            System.out.println("select * from customer where phone_number = '" + phoneNumber + "'");
                            if (rs.next())
                            {                                
                                rs.updateInt("points", newPoints);
                                rs.updateRow();
                            }
                            rs.close();
                            stmt.close();
                            con.close();
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < listSize; i++)
                        {
                            String productID = dis.readLine();
                            int productQuantity = dis.readInt();
                            temp.add(new ProductDetail(productID, productQuantity));
                        }

                        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from bill where bill_id = 0");
                        rs.moveToInsertRow();
                        rs.updateString("phone", phoneNumber);
                        rs.updateDouble("total_amount", totalAmount);
                        rs.updateDate("date", new Date(System.currentTimeMillis()));
                        rs.updateString("time", new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
                        rs.insertRow();
                        if (rs.next())
                        {
                            currentBillID = rs.getInt("bill_id");
                        }
                        rs.close();
                        stmt.close();
                        con.close();

                        for (int i = 0; i < temp.size(); i++)
                        {
                            Connection con1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pos", "root", "system");
                            Statement stmt1 = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs1 = stmt1.executeQuery("select * from bill_detail");
                            rs1.moveToInsertRow();
                            rs1.updateInt("bill_id", currentBillID);
                            rs1.updateString("product_id", temp.get(i).productID + "");
                            rs1.updateInt("quantity", temp.get(i).productQuantity);
                            rs1.insertRow();
                            rs1.close();
                            stmt1.close();
                            con1.close();
                        }
                        dos.writeBytes("bill_generate_true\r\n");
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        new POS();
    }

    class ProductDetail
    {

        String productID; 
        int productQuantity;

        public ProductDetail(String productID, int productQuantity)
        {
            this.productID = productID;
            this.productQuantity = productQuantity;
        }

    }

}
