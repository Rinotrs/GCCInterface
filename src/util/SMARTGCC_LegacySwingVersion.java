package util;

import util.accordion.SideBar;
import util.accordion.SidebarSection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private SidebarSection section_compiler;
    private SidebarSection section_linking;
    private SidebarSection section_execution;
    private SidebarSection section_debug;
    private SidebarSection section_generation;
    private SidebarSection section_optimization;
    private SidebarSection section_developer;

    public SMARTGCC_LegacySwingVersion() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() * 0.65)));
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

        noviceRadioButton.addActionListener(e -> {
            setUserProfile(false, false);
        });
        typicalRadioButton.addActionListener(e -> {
            setUserProfile(true, false);
        });
        expertRadioButton.addActionListener(e -> {
            setUserProfile(true, true);
        });
    }

    public void setUserProfile(boolean gen_opt, boolean dev) {
        section_compiler.setVisible(true);//.setEnabled(true);
        section_linking.setVisible(true);//.setEnabled(true);
        section_execution.setVisible(true);//.setEnabled(true);
        section_debug.setVisible(true);//.setEnabled(true);
        section_generation.setVisible(gen_opt);//.setEnabled(false);
        section_optimization.setVisible(gen_opt);//.setEnabled(false);
        section_developer.setVisible(dev);//.setEnabled(false);
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        startSMARTGCC("novice");
    }

    public static void startSMARTGCC(String usertype) {
        SMARTGCC_LegacySwingVersion dialog = new SMARTGCC_LegacySwingVersion();
        dialog.pack();

        dialog.noviceRadioButton.doClick();
        if (usertype.equalsIgnoreCase("typical")) dialog.typicalRadioButton.doClick();
        if (usertype.equalsIgnoreCase("expert")) dialog.expertRadioButton.doClick();

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // custom component creation code
        this.setTitle("SMART GCC Legacy version");
        Icon iconCal24 = null;//new ImageIcon("src\\util\\accordion\\Calendar_24x24.png");

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel listPanel = new JPanel(new BorderLayout());
        SideBar sideBar = new SideBar(SideBar.SideBarMode.TOP_LEVEL, true, 300, true);

        section_compiler = AccordionSection(sideBar, "COMPILER", SOEN6751_OptionsModel.compiler, iconCal24);
        section_linking = AccordionSection(sideBar, "LINKING", SOEN6751_OptionsModel.linking, iconCal24);
        section_execution = AccordionSection(sideBar, "EXECUTION", SOEN6751_OptionsModel.execute, iconCal24);
        section_debug = AccordionSection(sideBar, "DEBUG", SOEN6751_OptionsModel.debugging, iconCal24);

        section_generation = AccordionSection(sideBar, "GENERATION", SOEN6751_OptionsModel.generation, iconCal24);
        section_optimization = AccordionSection(sideBar, "OPTIMIZATION", SOEN6751_OptionsModel.optimization, iconCal24);

        section_developer = AccordionSection(sideBar, "DEVELOPER", SOEN6751_OptionsModel.developer, iconCal24);

        /*JTree tree = new JTree();
        SidebarSection ss2 = new SidebarSection(sideBar, "TREE", tree, iconCal24);
        sideBar.addSection(ss2);*/

        noviceRadioButton = new JRadioButton();
        typicalRadioButton = new JRadioButton();
        expertRadioButton = new JRadioButton();

        sideBar.setCurrentSection(section_linking);
        listPanel.add(sideBar, BorderLayout.CENTER);

        //listPanel.add(/*new JLabel("<html><body><h1>central panel</html>", JLabel.CENTER)*/checkboxes,BorderLayout.CENTER);

        tabbedPane.add("Select GCC Parameter Options", listPanel);

        this.menu1 = new JPanel(new BorderLayout());
        this.menu1.add(tabbedPane, BorderLayout.CENTER);

        this.terminalpane2 = new JPanel(new BorderLayout());
        this.terminalpane2.add(SOEN6751_TerminalOutput.getTerminalContainer(), BorderLayout.CENTER);
        System.out.println("Welcome to SMARTGCC (legacy version)\n");
/*        try {
            SOEN6751_TerminalOutput.testSomeOutput();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }*/
        //noviceRadioButton.doClick();
        pack();
    }

    private SidebarSection AccordionSection(SideBar sidebar, String sectioname, String[] compilerOptions, Icon icon) {
        //https://bugs.openjdk.java.net/browse/JDK-5082531
        JPanel checkboxes = new JPanel(new WrapLayout(FlowLayout.CENTER));
        for (String opt : compilerOptions) {
            String opt_html = "<html>"+opt+"</html>";
            Pattern p = Pattern.compile("(<html>.*=)(.*)(</html>)");
            Matcher m = p.matcher(opt_html);
            String output = opt_html;
            if (m.find()) {
                output = m.replaceFirst("$1<font color=\"red\">$2</font>$3");
            }

            JCheckBox optchip = new JCheckBox(output);
            optchip.setActionCommand(opt);
            optchip.setBorderPainted(true);
            optchip.setBorderPaintedFlat(true);
            optchip.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    l.add(optchip.getActionCommand()/*.getText()*/);
                    optchip.setBackground(Color.GREEN);
                } else {
                    l.remove(optchip.getActionCommand()/*.getText()*/);
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
