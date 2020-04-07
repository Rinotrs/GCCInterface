package util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//https://gist.github.com/rubenhorn/491fce66a90ec8fc1bfef975dda3fed7
public class JTextAreaOutputStream extends OutputStream
{   private final JTextArea destination;

    public JTextAreaOutputStream (JTextArea destination)
    {   if (destination == null) throw new IllegalArgumentException ("Destination is null");
        this.destination = destination;
    }

    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {   final String text = new String (buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable ()
        {   @Override
            public void run() {   destination.append (text);  }
        });
    }

    @Override
    public void write(int b) throws IOException
    {   write (new byte [] {(byte)b}, 0, 1);
    }

    public static void main (String[] args) throws Exception
    {
        JTextArea textArea = getjTextAreaTerminalGUI();

        JTextAreaOutputStream out = new JTextAreaOutputStream (textArea);
        System.setOut (new PrintStream (out));

        testSomeOutput();
    }

    private static void testSomeOutput() throws IOException, InterruptedException {
        SOEN6751_GccProcLib.run("echo 'testing one command'"); //windows
        SOEN6751_GccProcLib.run("cat 'testing one command'"); //unix
        SOEN6751_GccProcLib.run("cd c:\\ && dir && echo 'testing 3 commands at once' "); //windows
        SOEN6751_GccProcLib.run("cd \\ ; ls -lah ; cat 'testing 3 commands at once' "); //unix
/*        while (true)
        {   System.out.println ("Current time: " + System.currentTimeMillis ());
            Thread.sleep (1000L);
        }*/
    }

    private static JTextArea getjTextAreaTerminalGUI() {
        JTextArea textArea = new JTextArea (25, 80);
        textArea.setEditable (false);
        JFrame frame = new JFrame ("stdout");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (
                new JScrollPane (
                        textArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER);
        frame.pack ();
        frame.setVisible (true);
        return textArea;
    }
}