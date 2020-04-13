package util;

import util.accordion.SideBar;
import util.accordion.SidebarSection;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.GCCDocParser.getDocumentation;
import static util.GCCDocParser.getParameterDescription;

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
    private JTextArea documentation;
    private JLabel doc_title;
    private JScrollPane scrollPane;
    //private JPanel preview_description;
    private JTextArea preview_desc;
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
        this.setPreferredSize(new Dimension((int) screenSize.getWidth() / 2, (int) (screenSize.getHeight() * 0.75)));
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

        noviceRadioButton.addActionListener(e -> {setUserProfile(false, false);});
        typicalRadioButton.addActionListener(e -> {setUserProfile(true, false);});
        expertRadioButton.addActionListener(e -> {setUserProfile(true, true);});

/*        gccPreviewTxt.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                getDescriptionFromMouse(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                getDescriptionFromMouse(e);
            }
        });*/
    }

    public void getDescriptionFromMouse(MouseEvent e) {
        int position = gccPreviewTxt.viewToModel(e.getPoint());
        int start = 0;
        int end = 0;
        String currentword = "";
        try {
            start = Utilities.getWordStart(gccPreviewTxt, position);
            end = Utilities.getWordEnd(gccPreviewTxt, position);
            currentword = gccPreviewTxt.getText(start, end - start);
            gccPreviewTxt.select(start,end);
        } catch (BadLocationException badLocationException) {
            badLocationException.printStackTrace();
        }
        try {
            String description = getParameterDescription(getDocumentation("linking", GCCDocParser.LOCAL), currentword);
            preview_desc.setText(description);
            //System.out.println(description);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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

        SOEN6751_OptionsModel model = new SOEN6751_OptionsModel();

        section_compiler = AccordionSection(sideBar, "COMPILER", model.compiler, iconCal24);
        section_linking = AccordionSection(sideBar, "LINKING", model.linking, iconCal24);
        section_execution = AccordionSection(sideBar, "EXECUTE", model.execute, iconCal24);
        section_debug = AccordionSection(sideBar, "DEBUGGING", model.debugging, iconCal24);

        section_generation = AccordionSection(sideBar, "GENERATION", model.generation, iconCal24);
        section_optimization = AccordionSection(sideBar, "OPTIMIZATION", model.optimization, iconCal24);

        section_developer = AccordionSection(sideBar, "DEVELOPER", model.developer, iconCal24);

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
        System.out.println("Welcome to SMARTGCC (legacy version)\n\n");
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
            optchip.addMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {}
                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    try {
                        doc_title.setText("<html>Documentation for <font color=\"blue\">"+opt+"</font></html>");
                        documentation.setText(getParameterDescription(getDocumentation(sectioname, GCCDocParser.LOCAL),opt));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    scrollPane.getViewport().setViewPosition( new Point(0, 0) );
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //doc_title.setText("Documentation");
                    //documentation.setText("");
                }
            });
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
            /*try {
                optchip.setToolTipText(GCCDocParser.getParameterDescription(getDocumentation("linking", GCCDocParser.LOCAL),opt));
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            checkboxes.add(optchip);
        }

        JScrollPane scroll = new JScrollPane(checkboxes, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        SidebarSection ss1 = new SidebarSection(sidebar, sectioname, scroll, icon);
        sidebar.addSection(ss1);
        return ss1;
    }

}
