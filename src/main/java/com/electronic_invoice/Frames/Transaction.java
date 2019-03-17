/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Frames;

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

    private static final long serialVersionUID = 5764815429841719164L;

    public String frame_title = "Electronic Invoice Entry – Transaction";

    //
    private final JLabel jl_name = new JLabel("Name");
    private final JLabel jl_customernumber = new JLabel("Customer Number");
    private final JLabel jl_balance = new JLabel("Balance");

    //
    private final JTextField jtf_name = new JTextField();
    private final JTextField jtf_customernumber = new JTextField();
    private final JTextField jtf_balance = new JTextField();

    //
    public final JButton jbtn_check = new JButton("Check Balance");
    public final JButton jbtn_deposit = new JButton("Deposit");
    public final JButton jbtn_calculate = new JButton("Calculate Payment & Deposit");
    public final JButton jbtn_transaction = new JButton("Transaction");

    public Transaction() {
        initFrame();
    }

    private void initFrame() {
        setTitle(frame_title);
        setSize(500, 500);
        setResizable(false);
        setLocation(200, 200);
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(Color.GREEN);
        getRootPane().setBorder(
                BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN)
        );

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
    }
}
