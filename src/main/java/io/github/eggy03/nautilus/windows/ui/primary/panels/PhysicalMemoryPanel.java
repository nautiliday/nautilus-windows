/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIPhysicalMemoryPanelWorker;
import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.util.List;

@SuppressWarnings("java:S1192")
public class PhysicalMemoryPanel extends JPanel {

    private final @NonNull JLabel slotLabel = new JLabel("Slot#");
    private final @NonNull JComboBox<String> slotComboBox = new JComboBox<>();

    private final @NonNull JLabel nameLabel = new JLabel("Name");
    private final @NonNull JTextField nameTextField = new JTextField();

    private final @NonNull JLabel manufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JTextField manufacturerTextField = new JTextField();

    private final @NonNull JLabel modelLabel = new JLabel("Model");
    private final @NonNull JTextField modelTextField = new JTextField();

    private final @NonNull JLabel otherIdLabel = new JLabel("Other ID");
    private final @NonNull JTextField oidTextField = new JTextField();

    private final @NonNull JLabel partNumberLabel = new JLabel("Part Number");
    private final @NonNull JTextField partNumberTextField = new JTextField();

    private final @NonNull JLabel serialNumberLabel = new JLabel("Serial Number");
    private final @NonNull JTextField serialTextField = new JTextField();

    private final @NonNull JLabel formFactorLabel = new JLabel("Form Factor");
    private final @NonNull JTextField formFactorTextField = new JTextField();

    private final @NonNull JLabel bankLabel = new JLabel("Bank Label");
    private final @NonNull JTextField bankLabelTextField = new JTextField();

    private final @NonNull JLabel capacityLabel = new JLabel("Capacity");
    private final @NonNull JTextField capacityTextField = new JTextField();

    private final @NonNull JLabel dataWidthLabel = new JLabel("Data Width");
    private final @NonNull JTextField dataWidthTextField = new JTextField();

    private final @NonNull JLabel speedLabel = new JLabel("Speed");
    private final @NonNull JTextField speedTextField = new JTextField();

    private final @NonNull JLabel configSpeedLabel = new JLabel("Configured Speed");
    private final @NonNull JTextField configSpeedTextField = new JTextField();

    private final @NonNull JLabel deviceLocatorLabel = new JLabel("Device Locator");
    private final @NonNull JTextField deviceLocatorTextField = new JTextField();


    public @NonNull PhysicalMemoryPanel initUI() {

        setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][][][][]"));
        setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        return this;
    }


    public @NonNull PhysicalMemoryPanel initComponents() {

        add(slotLabel, "cell 0 0,alignx leading");
        add(slotComboBox, "cell 1 0,growx");

        add(nameLabel, "cell 0 1,alignx leading");
        add(nameTextField, "cell 1 1,growx");

        add(manufacturerLabel, "cell 0 2,alignx leading");
        add(manufacturerTextField, "cell 1 2,growx");

        add(modelLabel, "cell 0 3,alignx leading");
        add(modelTextField, "cell 1 3,growx");

        add(otherIdLabel, "cell 0 4,alignx leading");
        add(oidTextField, "cell 1 4,growx");

        add(partNumberLabel, "cell 0 5,alignx leading");
        add(partNumberTextField, "cell 1 5,growx");

        add(serialNumberLabel, "cell 0 6,alignx leading");
        add(serialTextField, "cell 1 6,growx");

        add(formFactorLabel, "cell 0 7,alignx leading");
        add(formFactorTextField, "cell 1 7,growx");

        add(bankLabel, "cell 0 8,alignx leading");
        add(bankLabelTextField, "cell 1 8,growx");

        add(capacityLabel, "cell 0 9,alignx leading");
        add(capacityTextField, "cell 1 9,growx");

        add(dataWidthLabel, "cell 0 10,alignx leading");
        add(dataWidthTextField, "cell 1 10,growx");

        add(speedLabel, "cell 0 11,alignx leading");
        add(speedTextField, "cell 1 11,growx");

        add(configSpeedLabel, "cell 0 12,alignx leading");
        add(configSpeedTextField, "cell 1 12,growx");

        add(deviceLocatorLabel, "cell 0 13,alignx leading");
        add(deviceLocatorTextField, "cell 1 13,growx");

        nameTextField.setEditable(false);
        manufacturerTextField.setEditable(false);
        modelTextField.setEditable(false);
        oidTextField.setEditable(false);
        partNumberTextField.setEditable(false);
        serialTextField.setEditable(false);
        formFactorTextField.setEditable(false);
        bankLabelTextField.setEditable(false);
        capacityTextField.setEditable(false);
        dataWidthTextField.setEditable(false);
        speedTextField.setEditable(false);
        configSpeedTextField.setEditable(false);
        deviceLocatorTextField.setEditable(false);

        return this;
    }

    public @NonNull PhysicalMemoryPanel initWorkers() {

        List<JTextField> memoryFields = List.of(nameTextField, manufacturerTextField, modelTextField, oidTextField,
                partNumberTextField, serialTextField, formFactorTextField, bankLabelTextField, capacityTextField,
                dataWidthTextField, speedTextField, configSpeedTextField, deviceLocatorTextField);

        new WMIPhysicalMemoryPanelWorker(slotComboBox, memoryFields).execute();

        return this;
    }

}
