package com.electronic_invoice.Frames;

import com.electronic_invoice.Utils.ClientAction;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class Transaction extends JFrame {

    //
    private static final long serialVersionUID = 5764815429841719164L;
    private static Transaction frame = null;
    private final ClientAction action = new ClientAction();
    public String frame_title = "Electronic Invoice Entry – Transaction";

    //
    private final JLabel jl_name = new JLabel("Name");
    private final JLabel jl_customernumber = new JLabel("Customer Number");
    private final JLabel jl_balance = new JLabel("Balance");

    //
    public final JTextField jtf_name = new JTextField();
    public final JTextField jtf_customernumber = new JTextField();
    public final JTextField jtf_balance = new JTextField();

    //
    public final JButton jbtn_check = new JButton("Check Balance");
    public final JButton jbtn_deposit = new JButton("Deposit");
    public final JButton jbtn_calculate = new JButton("Calculate Payment & Deposit");
    public final JButton jbtn_transaction = new JButton("Transaction");

    public Transaction() {
        frame = this;
        initFrame();
    }

    private void initFrame() {
        setTitle(frame_title);
        setSize(500, 500);
        setResizable(false);
        setLocation(700, 200);
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(Color.GREEN);
        getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 1, 2, Color.GREEN));

        add(jl_name).setName("jl_name");
        add(jtf_name).setName("jtf_name");
        add(jl_customernumber).setName("jl_customernumber");
        add(jtf_customernumber).setName("jtf_customernumber");
        add(jl_balance).setName("jl_balance");
        add(jtf_balance).setName("jtf_balance");
        add(jbtn_check).setName("jbtn_check");
        add(jbtn_deposit).setName("jbtn_deposit");
        add(jbtn_calculate).setName("jbtn_calculate");
        add(jbtn_transaction).setName("jbtn_transaction");

        //
        jbtn_check.addActionListener((e) -> {
            action.checkAccount(this, InvoiceEntry.getFrame());
        });
        jbtn_deposit.addActionListener((e) -> {
            action.addAccount(this);
        });
        jbtn_calculate.addActionListener((e) -> {
            action.calculateAndDeposit();
        });
        jbtn_transaction.addActionListener((e) -> {
            action.transaction();
        });
    }

    /**
     *
     * @return
     */
    public static Transaction getFrame() {
        return frame;
    }
}
