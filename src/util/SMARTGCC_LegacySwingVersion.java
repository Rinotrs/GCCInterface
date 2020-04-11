package util;

import util.accordion.SideBar;
import util.accordion.SidebarSection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class SMARTGCC_LegacySwingVersion extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel pane1;
    private JSplitPane split;
    private JTextArea gccPreviewTxt;
    private JButton executeCommandButton;
    private JPanel terminalpane2;
    private JPanel menu1;
    private JRadioButton noviceRadioButton;
    private JRadioButton typicalRadioButton;
    private JRadioButton expertRadioButton;
    private final ArrayList<String> l = new ArrayList<>();

    public SMARTGCC_LegacySwingVersion() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int) screenSize.getWidth() / 2, (int) screenSize.getHeight() / 2));
        setContentPane(contentPane);
        this.pack();
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        executeCommandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(gccPreviewTxt.getText());
                try {
                    SOEN6751_GccProcLib.run(gccPreviewTxt.getText());
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SMARTGCC_LegacySwingVersion dialog = new SMARTGCC_LegacySwingVersion();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // custom component creation code
        Icon iconCal24 = null;//new ImageIcon("src\\util\\accordion\\Calendar_24x24.png");

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel listPanel = new JPanel(new BorderLayout());
        SideBar sideBar = new SideBar(SideBar.SideBarMode.TOP_LEVEL, true, 300, true);

        SidebarSection ss = AccordionSection(sideBar, "COMPILER", SOEN6751_OptionsModel.compiler, iconCal24);
        AccordionSection(sideBar, "EXECUTION", SOEN6751_OptionsModel.execute, iconCal24);
        AccordionSection(sideBar, "DEBUG", SOEN6751_OptionsModel.debugging, iconCal24);
        AccordionSection(sideBar, "LINKING", SOEN6751_OptionsModel.linking, iconCal24);

        JTree tree = new JTree();
        SidebarSection ss2 = new SidebarSection(sideBar, "TREE", tree, iconCal24);
        sideBar.addSection(ss2);

        sideBar.setCurrentSection(ss);
        listPanel.add(sideBar, BorderLayout.CENTER);

        //listPanel.add(/*new JLabel("<html><body><h1>central panel</html>", JLabel.CENTER)*/checkboxes,BorderLayout.CENTER);

        tabbedPane.add("GCC Parameter Options", listPanel);

        this.menu1 = new JPanel(new BorderLayout());
        this.menu1.add(tabbedPane, BorderLayout.CENTER);

        this.terminalpane2 = new JPanel(new BorderLayout());
        this.terminalpane2.add(SOEN6751_TerminalOutput.getTerminalContainer(), BorderLayout.CENTER);
        System.out.println("Welcome to SMARTGCC (legacy version)");
/*        try {
            SOEN6751_TerminalOutput.testSomeOutput();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }*/
        pack();
    }

    private SidebarSection AccordionSection(SideBar sidebar, String sectioname, String[] compilerOptions, Icon icon) {
        //https://bugs.openjdk.java.net/browse/JDK-5082531
        JPanel checkboxes = new JPanel(new WrapLayout(FlowLayout.CENTER));
        for (String opt : compilerOptions) {
            JCheckBox optchip = new JCheckBox(opt);
            optchip.setBorderPainted(true);
            optchip.setBorderPaintedFlat(true);
            optchip.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    l.add(optchip.getText());
                    optchip.setBackground(Color.GREEN);
                } else {
                    l.remove(optchip.getText());
                    optchip.setBackground(Color.YELLOW);
                }
                gccPreviewTxt.setText("gcc " + String.join(" ", l));
            });
            checkboxes.add(optchip);
        }

        JScrollPane scroll = new JScrollPane(checkboxes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SidebarSection ss1 = new SidebarSection(sidebar, sectioname, scroll, icon);
        sidebar.addSection(ss1);
        return ss1;
    }

}
