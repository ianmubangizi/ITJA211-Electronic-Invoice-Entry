package com.electronic_invoice.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * PrintInvoice
 */
public final class PrintInvoice {
    
    private static PrintInvoice service = null;

    private PrintInvoice() {
        service = this;
    }

    /**
     *
     * @return
     */
    public static PrintInvoice printService() {
        if(service == null)
            new PrintInvoice();
        return service;
    }
    
    /**
     *
     * @param doc
     * @param id
     */
    public void print(String doc, String id) {
        try {
            String filename = "Customer Invoice [" + id + "].txt";
            try (PrintWriter printfile = new PrintWriter(new File(filename))) {
                System.out.println("Printing File: " + filename);
                printfile.print(doc);
            }
        } catch (FileNotFoundException ex) {
        }
    }
}
