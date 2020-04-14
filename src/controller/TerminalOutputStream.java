package controller;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import util.SOEN6751_GccProcLib;
import util.SOEN6751_TerminalOutput;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TerminalOutputStream extends OutputStream {
    private final TextArea destination;


    public TerminalOutputStream(TextArea destination) {
        if (destination == null) throw new IllegalArgumentException("Destination is null");
        this.destination = destination;
    }


    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        final String text = new String(buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                destination.appendText(text);
            }
        });
    }

    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }


    public static void setTerminalTo(TextArea textArea) {
        TerminalOutputStream out = new TerminalOutputStream(textArea);
        System.setOut(new PrintStream(out));
    }

}
