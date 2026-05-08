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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;

public class WMIProcessorPanel extends JPanel {

    // the main panel has five sub panels

    // hardware ID panel components
    private final JTextField hardwareIdTextField = new JTextField();
    private final JButton copyHid = new JButton();

    // primary info panel components
    private final JLabel cpuIdLabel = new JLabel("CPU#");
    private final JComboBox<String> cpuIdComboBox = new JComboBox<>();

    private final JLabel cpuNameLabel = new JLabel("Name");
    private final JTextField cpuNameTextField = new JTextField();

    private final JLabel coreLabel = new JLabel("Cores");
    private final JTextField coreTextField = new JTextField();

    private final JLabel threadLabel = new JLabel("Threads");
    private final JTextField threadTextField = new JTextField();

    private final JLabel effectiveClockLabel = new JLabel("Effective Clock");
    private final JTextField effectiveClockTextField = new JTextField();

    private final JLabel addressWidthLabel = new JLabel("Address Width");
    private final JTextField addressWidthTextField = new JTextField();

    private final JLabel socketLabel = new JLabel("Socket");
    private final JTextField socketTextField = new JTextField();

    private final JLabel baseClockLabel = new JLabel("Base Clock");
    private final JTextField baseClockTextField = new JTextField();

    // secondary info panel components
    private final JLabel versionLabel = new JLabel("Version");
    private final JTextField versionTextField = new JTextField();

    private final JLabel familyLabel = new JLabel("Family");
    private final JTextField familyTextField = new JTextField();

    private final JLabel steppingLabel = new JLabel("Stepping");
    private final JTextField steppingTextField = new JTextField();

    private final JLabel manufacturerLabel = new JLabel("Manufacturer");
    private final JTextField manufacturerTextField = new JTextField();

    private final JLabel captionLabel = new JLabel("Caption");
    private final JTextField captionTextField = new JTextField();

    private final JLabel processorIdLabel = new JLabel("Processor ID");
    private final JTextField processorIdTextField = new JTextField();

    private final JLabel enabledCoresLabel = new JLabel("Enabled Cores");
    private final JTextField enabledCoresTextField = new JTextField();

    private final JLabel enabledThreadsLabel = new JLabel("Enabled Threads");
    private final JTextField enabledThreadsTextField = new JTextField();

    private final JLabel cpuArchitectureLabel = new JLabel("Architecture");
    private final JTextField cpuArchitectureTextField = new JTextField();

    // tertiary panel components
    private final JTextArea cpuConciseInfoTextArea = new JTextArea();
    private final JTextArea cacheTextArea = new JTextArea();


    public WMIProcessorPanel initUI() {

        setLayout(new MigLayout(" insets 0", "[grow][grow]", "[][grow][grow][grow]"));

        return this;
    }

    public WMIProcessorPanel initComponents() {
        add(new JScrollPane(createHardwareIdPanel()), "cell 0 0 2 1, grow");
        add(new JScrollPane(createPrimaryInfoPanel()), "cell 0 1 2 1, grow");
        add(new JScrollPane(createSecondaryInfoPanel()), "cell 0 2 2 1, grow");
        add(createConciseCpuInfoPanel(), "cell 0 3 1 1, grow");
        add(createCachePanel(), "cell 1 3 1 1, grow");

        return this;
    }

    private JPanel createHardwareIdPanel() {

        final JPanel hardwareIdPanel = new JPanel();
        hardwareIdPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "HardwareID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        hardwareIdPanel.setLayout(new MigLayout("insets 0", "[grow][]", "[]"));

        hardwareIdPanel.add(hardwareIdTextField, "cell 0 0,grow");
        hardwareIdPanel.add(copyHid, "cell 1 0,alignx center");

        hardwareIdTextField.setEditable(false);

        copyHid.setIcon(new FlatSVGIcon(WMIProcessorPanel.class.getResource("/icons/general_icons/copy.svg")));
        copyHid.addActionListener(copyAction -> {
            StringSelection textToCopy = new StringSelection(hardwareIdTextField.getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(textToCopy, null);
        });

        return hardwareIdPanel;
    }

    private JPanel createPrimaryInfoPanel() {

        final JPanel primaryInfoPanel = new JPanel();
        primaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Primary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        primaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][]"));

        primaryInfoPanel.add(cpuIdLabel, "cell 0 0,alignx leading");
        primaryInfoPanel.add(cpuIdComboBox, "cell 1 0,growx");

        primaryInfoPanel.add(cpuNameLabel, "cell 2 0,alignx leading");
        primaryInfoPanel.add(cpuNameTextField, "cell 3 0 3 1,growx");

        primaryInfoPanel.add(coreLabel, "cell 0 1,alignx leading");
        primaryInfoPanel.add(coreTextField, "cell 1 1,growx");

        primaryInfoPanel.add(threadLabel, "cell 2 1,alignx leading");
        primaryInfoPanel.add(threadTextField, "cell 3 1,growx");

        primaryInfoPanel.add(effectiveClockLabel, "cell 4 1,alignx leading");
        primaryInfoPanel.add(effectiveClockTextField, "cell 5 1,growx");

        primaryInfoPanel.add(addressWidthLabel, "cell 0 2,alignx leading");
        primaryInfoPanel.add(addressWidthTextField, "cell 1 2,growx");

        primaryInfoPanel.add(socketLabel, "cell 2 2,alignx leading");
        primaryInfoPanel.add(socketTextField, "cell 3 2,growx");

        primaryInfoPanel.add(baseClockLabel, "cell 4 2,alignx leading");
        primaryInfoPanel.add(baseClockTextField, "cell 5 2,growx");

        cpuNameTextField.setEditable(false);
        coreTextField.setEditable(false);
        threadTextField.setEditable(false);
        effectiveClockTextField.setEditable(false);
        addressWidthTextField.setEditable(false);
        socketTextField.setEditable(false);
        baseClockTextField.setEditable(false);

        return primaryInfoPanel;
    }

    private JPanel createSecondaryInfoPanel() {

        final JPanel secondaryInfoPanel = new JPanel();
        secondaryInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Secondary Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        secondaryInfoPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][]"));

        secondaryInfoPanel.add(versionLabel, "cell 0 0,alignx leading");
        secondaryInfoPanel.add(versionTextField, "cell 1 0,growx");

        secondaryInfoPanel.add(familyLabel, "cell 2 0,alignx leading");
        secondaryInfoPanel.add(familyTextField, "cell 3 0,growx");

        secondaryInfoPanel.add(steppingLabel, "cell 4 0,alignx leading");
        secondaryInfoPanel.add(steppingTextField, "cell 5 0,growx");

        secondaryInfoPanel.add(manufacturerLabel, "cell 0 1,alignx leading");
        secondaryInfoPanel.add(manufacturerTextField, "cell 1 1,growx");

        secondaryInfoPanel.add(captionLabel, "cell 2 1,alignx leading");
        secondaryInfoPanel.add(captionTextField, "cell 3 1,growx");

        secondaryInfoPanel.add(processorIdLabel, "cell 4 1,alignx leading");
        secondaryInfoPanel.add(processorIdTextField, "cell 5 1,growx");

        secondaryInfoPanel.add(enabledCoresLabel, "cell 0 2,alignx leading");
        secondaryInfoPanel.add(enabledCoresTextField, "cell 1 2,growx");

        secondaryInfoPanel.add(enabledThreadsLabel, "cell 2 2,alignx leading");
        secondaryInfoPanel.add(enabledThreadsTextField, "cell 3 2,growx");

        secondaryInfoPanel.add(cpuArchitectureLabel, "cell 4 2,alignx leading");
        secondaryInfoPanel.add(cpuArchitectureTextField, "cell 5 2,growx");

        versionTextField.setEditable(false);
        familyTextField.setEditable(false);
        steppingTextField.setEditable(false);
        manufacturerTextField.setEditable(false);
        captionTextField.setEditable(false);
        processorIdTextField.setEditable(false);
        enabledCoresTextField.setEditable(false);
        enabledThreadsTextField.setEditable(false);
        cpuArchitectureTextField.setEditable(false);

        return secondaryInfoPanel;
    }

    private JPanel createConciseCpuInfoPanel() {

        final JPanel cpuConciseInfoPanel = new JPanel();
        cpuConciseInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
        cpuConciseInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Concise CPU Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        cpuConciseInfoTextArea.setEditable(false);
        cpuConciseInfoTextArea.setRows(7);
        cpuConciseInfoTextArea.setColumns(10);
        cpuConciseInfoPanel.add(new JScrollPane(cpuConciseInfoTextArea));

        return cpuConciseInfoPanel;
    }

    private JPanel createCachePanel() {

        final JPanel cacheInfoPanel = new JPanel();
        cacheInfoPanel.setLayout(new GridLayout(1, 0, 0, 0));
        cacheInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "CPU Cache Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        cacheTextArea.setEditable(false);
        cacheTextArea.setRows(7);
        cacheTextArea.setColumns(10);
        cacheInfoPanel.add(new JScrollPane(cacheTextArea));

        return cacheInfoPanel;
    }

    public WMIProcessorPanel initWorkers() {
        new WMIHardwareIdWorker(hardwareIdTextField).execute();

        List<JTextField> cpuFields = List.of(coreTextField, threadTextField, effectiveClockTextField, cpuNameTextField,
                versionTextField, familyTextField, steppingTextField, manufacturerTextField, captionTextField,
                processorIdTextField, enabledCoresTextField, enabledThreadsTextField, cpuArchitectureTextField, addressWidthTextField,
                socketTextField, baseClockTextField);

        List<JTextArea> cpuTextAreas = List.of(cpuConciseInfoTextArea, cacheTextArea);

        new WMIProcessorPanelWorker(cpuIdComboBox, cpuFields, cpuTextAreas).execute();

        return this;
    }
}
