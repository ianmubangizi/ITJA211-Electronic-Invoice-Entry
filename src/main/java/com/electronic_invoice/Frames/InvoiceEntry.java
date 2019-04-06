package com.electronic_invoice.Frames;

import com.electronic_invoice.Entities.Product;
import com.electronic_invoice.Utils.ClientAction;
import com.electronic_invoice.Utils.Helpers;

import javax.swing.*;
import java.awt.*;

import static com.electronic_invoice.Services.Finders.FindProduct.findProduct;

/**
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class InvoiceEntry extends JFrame {

    //
    private static final long serialVersionUID = 2720710036607580794L;
    private static InvoiceEntry frame = null;
    //
    public final JTextField jtf_name = new JTextField();
    public final JTextField jtf_address = new JTextField();
    public final JTextField jtf_city = new JTextField();
    public final JTextField jtf_description = new JTextField();
    public final JTextField jtf_province = new JTextField();
    public final JTextField jtf_zip = new JTextField();
    public final JTextField jtf_productcode = new JTextField();
    public final JTextField jtf_invoicenumber = new JTextField();
    public final JTextField jtf_customernumber = new JTextField();
    public final JTextField jtf_payment = new JTextField();
    public final JTextField jtf_price = new JTextField();
    public final JTextField jtf_deposit = new JTextField();
    //
    public final JTextArea jtxta_products = new JTextArea("List of Products e.g:\n116-456 x 2");
    public final JComboBox<String> jcbx_allproducts = new JComboBox<>();
    private final JTextField jtf_quantity = new JTextField();
    private final ClientAction action = new ClientAction();
    //
    private final JLabel jl_name = new JLabel("Name");
    private final JLabel jl_address = new JLabel("Address");
    private final JLabel jl_city = new JLabel("City");
    private final JLabel jl_description = new JLabel("Description");
    private final JLabel jl_province = new JLabel("Province");
    private final JLabel jl_zip = new JLabel("Zip");
    private final JLabel jl_productcode = new JLabel("Product Code");
    private final JLabel jl_invoicenumber = new JLabel("Invoice Number");
    private final JLabel jl_customernumber = new JLabel("Customer Number");
    private final JLabel jl_productbought = new JLabel("Product Bought");
    private final JLabel jl_quantity = new JLabel("Quantity");
    private final JLabel jl_payment = new JLabel("Payment");
    private final JLabel jl_price = new JLabel("Price");
    private final JLabel jl_deposit = new JLabel("Deposit");
    private final JLabel jl_allproducts = new JLabel("All Products:");
    //
    private final JButton jbtn_addcustomer = new JButton("Add Customer");
    private final JButton jbtn_findproduct = new JButton("Find Product");
    private final JButton jbtn_listproduct = new JButton("List Product");
    private final JButton jbtn_addinvoice = new JButton("Add Invoice");
    private final JButton jbtn_showinvoice = new JButton("Show Invoice");
    private final JButton jbtn_exit = new JButton("Exit");
    private final JButton jbtn_writeinvoice = new JButton("Write Invoice");
    private final JButton jbtn_next = new JButton("Next");
    private final JScrollPane jslp_productbought = new JScrollPane(jtxta_products);

    private InvoiceEntry() {
        frame = this;
        initFrame();
    }

    public static void main(String[] args) {
        new InvoiceEntry();
        new Transaction();
    }

    public static InvoiceEntry getFrame() {
        return frame;
    }

    //
    private void initFrame() {
        String frame_title = "Electronic Invoice Entry";
        setTitle(frame_title);
        setSize(500, 600);
        setVisible(true);
        setResizable(false);
        setLocation(200, 200);
        setLayout(new GridLayout(19, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.cyan);
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, Color.cyan));

        jtxta_products.setEditable(false);
        jtxta_products.setName("jtxta_productsbought");

        add(jl_name).setName("jl_name");
        add(jtf_name).setName("jtf_name");
        add(jl_address).setName("jl_address");
        add(jtf_address).setName("jtf_address");
        add(jl_city).setName("jl_city");
        add(jtf_city).setName("jtf_city");
        add(jl_description).setName("jl_description");
        add(jtf_description).setName("jtf_description");
        add(jl_province).setName("jl_province");
        add(jtf_province).setName("jtf_province");
        add(jl_zip).setName("jl_zip");
        add(jtf_zip).setName("jtf_zip");
        add(jl_productcode).setName("jl_productcode");
        add(jtf_productcode).setName("jtf_productcode");
        add(jl_invoicenumber).setName("jl_invoicenumber");
        add(jtf_invoicenumber).setName("jtf_invoicenumber");
        add(jl_customernumber).setName("jl_customernumber");
        add(jtf_customernumber).setName("jtf_customernumber");
        add(jl_productbought).setName("jl_productbought");
        add(jslp_productbought).setName("jslp_productbought");
        add(jl_quantity).setName("jl_quantity");
        add(jtf_quantity).setName("jtf_quantity");
        add(jl_payment).setName("jl_payment");
        add(jtf_payment).setName("jtf_payment");
        add(jl_price).setName("jl_price");
        add(jtf_price).setName("jtf_price");
        add(jl_deposit).setName("jl_deposit");
        add(jtf_deposit).setName("jtf_deposit");
        add(jl_allproducts).setName("jl_allproducts");
        add(jcbx_allproducts).setName("jcbx_allproducts");
        add(jbtn_addcustomer).setName("jbtn_addcustomer");
        add(jbtn_findproduct).setName("jbtn_findproduct");
        add(jbtn_listproduct).setName("jbtn_listproduct");
        add(jbtn_addinvoice).setName("jbtn_addinvoice");
        add(jbtn_showinvoice).setName("jbtn_showinvoice");
        add(jbtn_exit).setName("jbtn_exit");
        add(jbtn_writeinvoice).setName("jbtn_writeinvoice");
        add(jbtn_next).setName("jbtn_next");

        jbtn_next.addActionListener((e) -> Transaction.getFrame().setVisible(true));
        jbtn_exit.addActionListener((e) -> {
            getFrame().dispose();
            Transaction.getFrame().dispose();
        });
        jbtn_addinvoice.addActionListener((e) -> action.createInvoice(this));
        jbtn_addcustomer.addActionListener((e) -> action.addCustomer(this));
        jbtn_findproduct.addActionListener((e) -> action.findProductAction(this));
        jbtn_listproduct.addActionListener((e) -> new Helpers().displayProductList(this));
        jbtn_showinvoice.addActionListener((e) -> action.showInvoice(this));
        jbtn_writeinvoice.addActionListener((e) -> action.printInvoice(this));
        jcbx_allproducts.addActionListener((e) -> {
            Product p = findProduct().allProducts().get(jcbx_allproducts.getSelectedIndex());
            jtf_productcode.setText(p.getProduct_code());
        });
    }
}
