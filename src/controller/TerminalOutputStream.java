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
    

    public static void testSomeOutput() throws IOException, InterruptedException {
        SOEN6751_GccProcLib.run("echo 'testing one command'"); //windows
        SOEN6751_GccProcLib.run("cat 'testing one command'"); //unix
        SOEN6751_GccProcLib.run("cd c:\\ && dir && echo 'testing 3 commands at once' "); //windows
        SOEN6751_GccProcLib.run("cd \\ ; ls -lah ; cat 'testing 3 commands at once' "); //unix
    }

}
