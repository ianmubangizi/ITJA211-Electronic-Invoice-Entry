package com.electronic_invoice.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * MessagePane
 */
class MessagePane extends JOptionPane {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param parentComponent
     * @param message
     * @param title
     * @param type
     */
    MessagePane(Component parentComponent, String message, String title, EMessage type) {
        switch (type) {
            case ERROR:
                showMessageDialog(parentComponent, message, title, ERROR_MESSAGE);
                break;
            case SUCCESS:
                showMessageDialog(parentComponent, message, title, ERROR_MESSAGE);
                break;
            case INFO:
                showMessageDialog(parentComponent, message, title, INFORMATION_MESSAGE);
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    public enum EMessage {

        /**
         *
         */
        ERROR,

        /**
         *
         */
        SUCCESS,

        /**
         *
         */
        INFO
    }
}
