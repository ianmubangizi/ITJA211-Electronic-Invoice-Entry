package com.electronic_invoice.Utils;

import java.util.Random;

/**
 * Generate
 */
public class Generate {

    public Generate() {

    }
    
    public int customerNumber(){
        return new Random().nextInt(200);
    }
}