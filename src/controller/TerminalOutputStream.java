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

/**
 *
 * This is the page for terminal output stream
 *
 * @author Runsen Tian
 * @version v1.0.0
 */

public class TerminalOutputStream extends OutputStream {
    private final TextArea destination;


    /**
     *<p>
     * This method of Terminal Output Stream.
     *</p>
     * @param destination TextArea
     */
    public TerminalOutputStream(TextArea destination) {
        if (destination == null) throw new IllegalArgumentException("Destination is null");
        this.destination = destination;
    }


    /**
     *<p>
     * This method for writing data
     *</p>
     * @param buffer byte[]
     * @param offset int
     * @param length int
     * @throws IOException Input out exception
     */
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

    /**
     *<p>
     * This method for writing data
     *</p>
     * @param b int
     * @throws IOException Input out exception
     */
    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }

    /**
     *<p>
     * This method of printing.
     *</p>
     * @param textArea TextArea
     */

    public static void setTerminalTo(TextArea textArea) {
        TerminalOutputStream out = new TerminalOutputStream(textArea);
        System.setOut(new PrintStream(out));
    }

    /**
     *<p>
     * This method for testing
     *</p>
     * @throws IOException Input out exception
     * @throws InterruptedException
     */
    public static void testSomeOutput() throws IOException, InterruptedException {
        SOEN6751_GccProcLib.run("echo 'testing one command'"); //windows
        SOEN6751_GccProcLib.run("cat 'testing one command'"); //unix
        SOEN6751_GccProcLib.run("cd c:\\ && dir && echo 'testing 3 commands at once' "); //windows
        SOEN6751_GccProcLib.run("cd \\ ; ls -lah ; cat 'testing 3 commands at once' "); //unix
    }

}
