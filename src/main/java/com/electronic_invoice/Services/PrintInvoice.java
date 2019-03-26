package com.electronic_invoice.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * PrintInvoice
 */
public class PrintInvoice {
    
    public void print(String doc, String id) {
        try {
            String filename = "Customer Invoice [" + id + "].txt";
            System.out.println("Printing File: " + filename);
            File file = new File(filename);
            try (PrintWriter printfile = new PrintWriter(file)) {
                printfile.print(doc);
            }
        } catch (FileNotFoundException ex) {
        }
    }
}
