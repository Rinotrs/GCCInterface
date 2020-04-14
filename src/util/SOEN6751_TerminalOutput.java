package util;

import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class SOEN6751_TerminalOutput extends OutputStream {
    private final JTextArea destination;


    public SOEN6751_TerminalOutput(JTextArea destination) {
        if (destination == null) throw new IllegalArgumentException("Destination is null");
        this.destination = destination;
    }


    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException {
        final String text = new String(buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                destination.append(text);
            }
        });
    }

    @Override
    public void write(int b) throws IOException {
        write(new byte[]{(byte) b}, 0, 1);
    }

    public static void main(String[] args) throws Exception {
        runTerminalGUI();

        testSomeOutput();
    }

    private static void runTerminalGUI() {
        JFrame frame = new JFrame("GCC Terminal Output");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getTerminalContainer();
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static Container getTerminalContainer() {//how to capture the system.out stream: https://gist.github.com/rubenhorn/491fce66a90ec8fc1bfef975dda3fed7
        JTextArea textArea = setupJTextArea(25, 80, "Verdana", Font.BOLD, 14, Color.GREEN, Color.BLACK);
        Container contentPane = new Container();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(
                new JScrollPane(
                        textArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER);

        SetTerminalTo(textArea);

        return contentPane;
    }

    private static JTextArea setupJTextArea(int rows, int columns, String fontname, int style, int size, Color fg, Color bg) {
        JTextArea textArea = new JTextArea(rows, columns);
        Font font = new Font(fontname, style, size);
        textArea.setFont(font);
        textArea.setForeground(fg);
        textArea.setBackground(bg);
        textArea.setEditable(false);
        return textArea;
    }

    public static void SetTerminalTo(JTextArea textArea) {
        SOEN6751_TerminalOutput out = new SOEN6751_TerminalOutput(textArea);
        System.setOut(new PrintStream(out));
    }

    public static void testSomeOutput() throws IOException, InterruptedException {
        SOEN6751_GccProcLib.run("echo 'testing one command'"); //windows
        SOEN6751_GccProcLib.run("cat 'testing one command'"); //unix
        SOEN6751_GccProcLib.run("cd c:\\ && dir && echo 'testing 3 commands at once' "); //windows
        SOEN6751_GccProcLib.run("cd \\ ; ls -lah ; cat 'testing 3 commands at once' "); //unix
    }
}