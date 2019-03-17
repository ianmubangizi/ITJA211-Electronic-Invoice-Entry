package com.electronic_invoice.Services;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * PrintInvoice
 */
public class PrintInvoice {

    public void print(String doc, String id) {
        String filename = "Customer Invoice [" + id + "].txt";

        try {
            System.out.println("Printing File: " + filename);
            new ObjectOutputStream(Files.newOutputStream(
                    Paths.get(filename))
            ).writeObject(doc);
        } catch (IOException e) {
        }
    }
}
