/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.worker.WMIHardwareIdWorker;
import io.github.eggy03.nautilus.windows.worker.WMIProcessorPanelWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class WMIProcessorPanelUI extends JPanel {

    private JTextField hardwareIdTextField;

    private JComboBox<String> cpuIdComboBox;
    private JTextField coreTextField;
    private JTextField threadTextField;
    private JTextField effectiveClockTextField;
    private JTextField cpuNameTextField;
    private JTextField versionTextField;
    private JTextField familyTextField;
    private JTextField steppingTextField;
    private JTextField manufacturerTextField;
    private JTextField captionTextField;
    private JTextField processorIdTextField;
    private JTextField enabledCoresTextField;
    private JTextField enabledThreadsTextField;
    private JTextField cpuArchitectureTextField;
    private JTextField addressWidthTextField;
    private JTextField socketTextField;
    private JTextField baseClockTextField;

    private JTextArea cpuConciseInfoTextArea;
    private JTextArea cacheTextArea;


    /**
     * Create the panel.
     */
    public WMIProcessorPanelUI() {

        setLayout(new BorderLayout(0, 0));

        //set UI
        add(createHardwareIdPanel(), BorderLayout.NORTH);
        add(createCpuPanel(), BorderLayout.CENTER);
        // execute workers
        setWorkers();

    }

    public JPanel getPanel() {
        return this;
    }

    private JPanel createHardwareIdPanel() {

        JPanel hardwareIdPanel = new JPanel();
        hardwareIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "HardwareID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        hardwareIdPanel.setLayout(new MigLayout("insets 0", "[grow][]", "[]"));

        hardwareIdTextField = new JTextField();
        hardwareIdTextField.setEditable(false);
        hardwareIdPanel.add(hardwareIdTextField, "cell 0 0,grow");

        JButton copyHwid = new JButton();
        copyHwid.setIcon(new FlatSVGIcon(WMIProcessorPanelUI.class.getResource("/icons/general_icons/copy.svg")));
        copyHwid.addActionListener(copyAction -> {
            StringSelection textToCopy = new StringSelection(hardwareIdTextField.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textToCopy, null);
        });
        hardwareIdPanel.add(copyHwid, "cell 1 0,alignx center");

        return hardwareIdPanel;
    }

    private JPanel createCpuPanel() {
        // add main cpu panel
        JPanel cpuPanel = new JPanel();
        cpuPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        cpuPanel.setLayout(new GridLayout(3, 1, 0, 0));

        // PRIMARY INFO PANEL
        JPanel primaryInfoPanel = new JPanel();
        primaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Primary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        primaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][]"));

        JLabel cpuIdLabel = new JLabel("CPU#");
        primaryInfoPanel.add(cpuIdLabel, "cell 0 0,alignx leading");

        cpuIdComboBox = new JComboBox<>();
        primaryInfoPanel.add(cpuIdComboBox, "cell 1 0,growx");

        JLabel cpuNameLabel = new JLabel("Name");
        primaryInfoPanel.add(cpuNameLabel, "cell 2 0,alignx leading");

        cpuNameTextField = new JTextField();
        cpuNameTextField.setEditable(false);
        primaryInfoPanel.add(cpuNameTextField, "cell 3 0 3 1,growx");

        JLabel coreLabel = new JLabel("Cores");
        primaryInfoPanel.add(coreLabel, "cell 0 1,alignx leading");

        coreTextField = new JTextField();
        coreTextField.setEditable(false);
        primaryInfoPanel.add(coreTextField, "cell 1 1,growx");

        JLabel threadLabel = new JLabel("Threads");
        primaryInfoPanel.add(threadLabel, "cell 2 1,alignx leading");

        threadTextField = new JTextField();
        threadTextField.setEditable(false);
        primaryInfoPanel.add(threadTextField, "cell 3 1,growx");

        JLabel effectiveClockLabel = new JLabel("Effective Clock");
        primaryInfoPanel.add(effectiveClockLabel, "cell 4 1,alignx leading");

        effectiveClockTextField = new JTextField();
        effectiveClockTextField.setEditable(false);
        primaryInfoPanel.add(effectiveClockTextField, "cell 5 1,growx");

        JLabel addressWidthLabel = new JLabel("Address Width");
        primaryInfoPanel.add(addressWidthLabel, "cell 0 2,alignx leading");

        addressWidthTextField = new JTextField();
        addressWidthTextField.setEditable(false);
        primaryInfoPanel.add(addressWidthTextField, "cell 1 2,growx");

        JLabel socketLabel = new JLabel("Socket");
        primaryInfoPanel.add(socketLabel, "cell 2 2,alignx leading");

        socketTextField = new JTextField();
        socketTextField.setEditable(false);
        primaryInfoPanel.add(socketTextField, "cell 3 2,growx");

        JLabel baseClockLabel = new JLabel("Base Clock");
        primaryInfoPanel.add(baseClockLabel, "cell 4 2,alignx leading");

        baseClockTextField = new JTextField();
        baseClockTextField.setEditable(false);
        primaryInfoPanel.add(baseClockTextField, "cell 5 2,growx");

        // SECONDARY INFO PANEL
        JPanel secondaryInfoPanel = new JPanel();
        secondaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Secondary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        secondaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][]"));

        JLabel versionLabel = new JLabel("Version");
        secondaryInfoPanel.add(versionLabel, "cell 0 0,alignx leading");

        versionTextField = new JTextField();
        versionTextField.setEditable(false);
        secondaryInfoPanel.add(versionTextField, "cell 1 0,growx");

        JLabel familyLabel = new JLabel("Family");
        secondaryInfoPanel.add(familyLabel, "cell 2 0,alignx leading");

        familyTextField = new JTextField();
        familyTextField.setEditable(false);
        secondaryInfoPanel.add(familyTextField, "cell 3 0,growx");

        JLabel steppingLabel = new JLabel("Stepping");
        secondaryInfoPanel.add(steppingLabel, "cell 4 0,alignx leading");

        steppingTextField = new JTextField();
        steppingTextField.setEditable(false);
        secondaryInfoPanel.add(steppingTextField, "cell 5 0,growx");

        JLabel manufacturerLabel = new JLabel("Manufacturer");
        secondaryInfoPanel.add(manufacturerLabel, "cell 0 1,alignx leading");

        manufacturerTextField = new JTextField();
        manufacturerTextField.setEditable(false);
        secondaryInfoPanel.add(manufacturerTextField, "cell 1 1,growx");

        JLabel captionLabel = new JLabel("Caption");
        secondaryInfoPanel.add(captionLabel, "cell 2 1,alignx leading");

        captionTextField = new JTextField();
        captionTextField.setEditable(false);
        secondaryInfoPanel.add(captionTextField, "cell 3 1,growx");

        JLabel processorIdLabel = new JLabel("Processor ID");
        secondaryInfoPanel.add(processorIdLabel, "cell 4 1,alignx leading");

        processorIdTextField = new JTextField();
        processorIdTextField.setEditable(false);
        secondaryInfoPanel.add(processorIdTextField, "cell 5 1,growx");

        JLabel enabledCoresLabel = new JLabel("Enabled Cores");
        secondaryInfoPanel.add(enabledCoresLabel, "cell 0 2,alignx leading");

        enabledCoresTextField = new JTextField();
        enabledCoresTextField.setEditable(false);
        secondaryInfoPanel.add(enabledCoresTextField, "cell 1 2,growx");

        JLabel enabledThreadsLabel = new JLabel("Enabled Threads");
        secondaryInfoPanel.add(enabledThreadsLabel, "cell 2 2,alignx leading");

        enabledThreadsTextField = new JTextField();
        enabledThreadsTextField.setEditable(false);
        secondaryInfoPanel.add(enabledThreadsTextField, "cell 3 2,growx");

        JLabel cpuArchitectureLabel = new JLabel("Architecture");
        secondaryInfoPanel.add(cpuArchitectureLabel, "cell 4 2,alignx leading");

        cpuArchitectureTextField = new JTextField();
        cpuArchitectureTextField.setEditable(false);
        secondaryInfoPanel.add(cpuArchitectureTextField, "cell 5 2,growx");

        // TERTIARY INFO PANEL
        JPanel tertiaryInfoPanel = new JPanel();
        tertiaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tertiary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        tertiaryInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));

        // concise cpu info panel
        JPanel cpuConciseInfoPanel = new JPanel();
        cpuConciseInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
        cpuConciseInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Concise CPU Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        cpuConciseInfoTextArea = new JTextArea();
        cpuConciseInfoTextArea.setEditable(false);

        cpuConciseInfoPanel.add(new JScrollPane(cpuConciseInfoTextArea));
        tertiaryInfoPanel.add(cpuConciseInfoPanel);

        // cache info panel
        JPanel cacheInfoPanel = new JPanel();
        cacheInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
        cacheInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU Cache Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        cacheTextArea = new JTextArea();
        cacheTextArea.setEditable(false);

        cacheInfoPanel.add(new JScrollPane(cacheTextArea));
        tertiaryInfoPanel.add(cacheInfoPanel);


        // wrap scroll panes around sub panels and then add the wrapped scroll panes to the main panel
        cpuPanel.add(new JScrollPane(primaryInfoPanel));
        cpuPanel.add(new JScrollPane(secondaryInfoPanel));
        cpuPanel.add(tertiaryInfoPanel); // tertiary panel doesn't have a scroll pane cause its text areas incorporated one each

        return cpuPanel;
    }

    private void setWorkers() {
        new WMIHardwareIdWorker(hardwareIdTextField).execute();

        List<JTextField> cpuFields = List.of(coreTextField, threadTextField, effectiveClockTextField, cpuNameTextField,
                versionTextField, familyTextField, steppingTextField, manufacturerTextField, captionTextField,
                processorIdTextField, enabledCoresTextField, enabledThreadsTextField, cpuArchitectureTextField, addressWidthTextField,
                socketTextField, baseClockTextField);

        List<JTextArea> cpuTextAreas = List.of(cpuConciseInfoTextArea, cacheTextArea);

        new WMIProcessorPanelWorker(cpuIdComboBox, cpuFields, cpuTextAreas).execute();
    }
}
