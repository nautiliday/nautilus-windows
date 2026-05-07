/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import io.github.eggy03.nautilus.windows.worker.WMIPhysicalMemoryPanelWorker;
import net.miginfocom.swing.MigLayout;

public class WMIPhysicalMemoryPanelUI extends JPanel {

	private JComboBox<String> slotComboBox;
	private JTextField nameTextField;
	private JTextField manufacturerTextField;
	private JTextField modelTextField;
	private JTextField oidTextField;
	private JTextField partNumberTextField;
	private JTextField serialTextField;
	private JTextField formFactorTextField;
	private JTextField bankLabelTextField;
	private JTextField capacityTextField;
	private JTextField dataWidthTextField;
	private JTextField speedTextField;
	private JTextField configSpeedTextField;
	private JTextField deviceLocatorTextField;
	
	public JPanel getPanel() {
		return this;
	}

	/**
	 * Create the panel.
	 */
	public WMIPhysicalMemoryPanelUI() {
		setUI();
		setWorker();
	}
	
	private void setUI() {
		
		setLayout(new GridLayout(1, 0, 0, 0));
		
		// add panel
		JPanel memoryPanel = new JPanel();
		memoryPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		memoryPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][][][][]"));
		
		JLabel slotLabel = new JLabel("Slot#");
		memoryPanel.add(slotLabel, "cell 0 0,alignx leading");
		
		slotComboBox = new JComboBox<>();
		memoryPanel.add(slotComboBox, "cell 1 0,growx");
		
		JLabel nameLabel = new JLabel("Name");
		memoryPanel.add(nameLabel, "cell 0 1,alignx leading");
		
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		memoryPanel.add(nameTextField, "cell 1 1,growx");
		
		JLabel manufacturerLabel = new JLabel("Manufacturer");
		memoryPanel.add(manufacturerLabel, "cell 0 2,alignx leading");
		
		manufacturerTextField = new JTextField();
		manufacturerTextField.setEditable(false);
		manufacturerTextField.setColumns(10);
		memoryPanel.add(manufacturerTextField, "cell 1 2,growx");
		
		JLabel modelLabel = new JLabel("Model");
		memoryPanel.add(modelLabel, "cell 0 3,alignx leading");
		
		modelTextField = new JTextField();
		modelTextField.setEditable(false);
		modelTextField.setColumns(10);
		memoryPanel.add(modelTextField, "cell 1 3,growx");
		
		JLabel otherIdLabel = new JLabel("Other ID");
		memoryPanel.add(otherIdLabel, "cell 0 4,alignx leading");
		
		oidTextField = new JTextField();
		oidTextField.setEditable(false);
		memoryPanel.add(oidTextField, "cell 1 4,growx");
		
		JLabel partNumberLabel = new JLabel("Part Number");
		memoryPanel.add(partNumberLabel, "cell 0 5,alignx leading");
		
		partNumberTextField = new JTextField();
		partNumberTextField.setEditable(false);
		memoryPanel.add(partNumberTextField, "cell 1 5,growx");
		
		JLabel serialNumberLabel = new JLabel("Serial Number");
		memoryPanel.add(serialNumberLabel, "cell 0 6,alignx leading");
		
		serialTextField = new JTextField();
		serialTextField.setEditable(false);
		memoryPanel.add(serialTextField, "cell 1 6,growx");
		
		JLabel formFactorLabel = new JLabel("Form Factor");
		memoryPanel.add(formFactorLabel, "cell 0 7,alignx leading");
		
		formFactorTextField = new JTextField();
		formFactorTextField.setEditable(false);
		memoryPanel.add(formFactorTextField, "cell 1 7,growx");
		
		JLabel bankLabel = new JLabel("Bank Label");
		memoryPanel.add(bankLabel, "cell 0 8,alignx leading");
		
		bankLabelTextField = new JTextField();
		bankLabelTextField.setEditable(false);
		memoryPanel.add(bankLabelTextField, "cell 1 8,growx");
		
		JLabel capacityLabel = new JLabel("Capacity");
		memoryPanel.add(capacityLabel, "cell 0 9,alignx leading");
		
		capacityTextField = new JTextField();
		capacityTextField.setEditable(false);
		memoryPanel.add(capacityTextField, "cell 1 9,growx");
		
		JLabel dataWidthLabel = new JLabel("Data Width");
		memoryPanel.add(dataWidthLabel, "cell 0 10,alignx leading");
		
		dataWidthTextField = new JTextField();
		dataWidthTextField.setEditable(false);
		memoryPanel.add(dataWidthTextField, "cell 1 10,growx");
		
		JLabel speedLabel = new JLabel("Speed");
		memoryPanel.add(speedLabel, "cell 0 11,alignx leading");
		
		speedTextField = new JTextField();
		speedTextField.setEditable(false);
		memoryPanel.add(speedTextField, "cell 1 11,growx");
		
		JLabel configSpeedLabel = new JLabel("Configured Speed");
		memoryPanel.add(configSpeedLabel, "cell 0 12,alignx leading");
		
		configSpeedTextField = new JTextField();
		configSpeedTextField.setEditable(false);
		memoryPanel.add(configSpeedTextField, "cell 1 12,growx");
		
		JLabel deviceLocatorLabel = new JLabel("Device Locator");
		memoryPanel.add(deviceLocatorLabel, "cell 0 13,alignx leading");
		
		deviceLocatorTextField = new JTextField();
		deviceLocatorTextField.setEditable(false);
		memoryPanel.add(deviceLocatorTextField, "cell 1 13,growx");
		
		// wrap scroll-pane to the panel add it to the main panel
		add(new JScrollPane(memoryPanel));
	}
	
	private void setWorker() {

		List<JTextField> memoryFields = List.of(nameTextField, manufacturerTextField, modelTextField, oidTextField,
		partNumberTextField, serialTextField, formFactorTextField, bankLabelTextField, capacityTextField,
		dataWidthTextField, speedTextField, configSpeedTextField, deviceLocatorTextField);

		new WMIPhysicalMemoryPanelWorker(slotComboBox, memoryFields).execute();
	}

}
