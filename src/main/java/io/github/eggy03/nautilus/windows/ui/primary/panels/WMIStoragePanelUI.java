/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import io.github.eggy03.nautilus.windows.worker.WMIStorageWorker;
import net.miginfocom.swing.MigLayout;

public class WMIStoragePanelUI extends JPanel {
	
	private JComboBox<String> diskDeviceIdComboBox;

	private JTextField diskPnpDeviceIdTextField;
	private JTextField diskCaptionTextField;
	private JTextField diskModelTextField;
	private JTextField diskFirmwareRevisionTextField;
	private JTextField diskInterfaceTypeTextField;
	private JTextField diskSerialNumberTextField;
	private JTextField diskSizeTextField;
	private JTextField diskPartitionsTextField;
	private JTextField diskCapabilitiesTextField;
	private JTextField diskStatusTextField;

	private JEditorPane diskPartitionEditorPane;
	private JEditorPane diskVolumeEditorPane;
	
	public JPanel getPanel() {
		return this;
	}

	/**
	 * Create the panel.
	 */
	public WMIStoragePanelUI() {
		setLayout(new GridLayout(2, 1, 0, 0));
		
		add(createDiskDrivePanel());
		setDiskPartitionAndVolumePane();
		setWorker();
	}
	
	private JScrollPane createDiskDrivePanel() {
		
		JPanel diskDrivePanel = new JPanel();
		diskDrivePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Disk Drive", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		diskDrivePanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][]"));
		
		JLabel diskDeviceIdLabel = new JLabel("Disk#");
		diskDrivePanel.add(diskDeviceIdLabel, "cell 0 0,alignx leading");
		
		diskDeviceIdComboBox = new JComboBox<>();
		diskDrivePanel.add(diskDeviceIdComboBox, "cell 1 0,growx");
		
		JLabel diskPnpDeviceIdLabel = new JLabel("PnP DeviceID");
		diskDrivePanel.add(diskPnpDeviceIdLabel, "cell 0 1,alignx leading");
		
		diskPnpDeviceIdTextField = new JTextField();
		diskPnpDeviceIdTextField.setEditable(false);
		diskDrivePanel.add(diskPnpDeviceIdTextField, "cell 1 1,growx");
		
		JLabel diskCaptionLabel = new JLabel("Name");
		diskDrivePanel.add(diskCaptionLabel, "cell 0 2,alignx leading");
		
		diskCaptionTextField = new JTextField();
		diskCaptionTextField.setEditable(false);
		diskDrivePanel.add(diskCaptionTextField, "cell 1 2,growx");
		
		JLabel diskModelLabel = new JLabel("Model");
		diskDrivePanel.add(diskModelLabel, "cell 0 3,alignx leading");
		
		diskModelTextField = new JTextField();
		diskModelTextField.setEditable(false);
		diskDrivePanel.add(diskModelTextField, "cell 1 3,growx");
		
		JLabel diskFirmwareRevisionLabel = new JLabel("Firmware");
		diskDrivePanel.add(diskFirmwareRevisionLabel, "cell 0 4,alignx leading");
		
		diskFirmwareRevisionTextField = new JTextField();
		diskFirmwareRevisionTextField.setEditable(false);
		diskDrivePanel.add(diskFirmwareRevisionTextField, "cell 1 4,growx");
		
		JLabel diskInterfaceTypeLabel = new JLabel("Interface");
		diskDrivePanel.add(diskInterfaceTypeLabel, "cell 0 5,alignx leading");
		
		diskInterfaceTypeTextField = new JTextField();
		diskInterfaceTypeTextField.setEditable(false);
		diskDrivePanel.add(diskInterfaceTypeTextField, "cell 1 5,growx");
		
		JLabel diskSerialNumberLabel = new JLabel("Serial Number");
		diskDrivePanel.add(diskSerialNumberLabel, "cell 0 6,alignx leading");
		
		diskSerialNumberTextField = new JTextField();
		diskSerialNumberTextField.setEditable(false);
		diskDrivePanel.add(diskSerialNumberTextField, "cell 1 6,growx");
		
		JLabel diskSizeLabel = new JLabel("Size");
		diskDrivePanel.add(diskSizeLabel, "cell 0 7,alignx leading");
		
		diskSizeTextField = new JTextField();
		diskSizeTextField.setEditable(false);
		diskDrivePanel.add(diskSizeTextField, "cell 1 7,growx");
		
		JLabel diskPartitionsLabel = new JLabel("Partitions");
		diskDrivePanel.add(diskPartitionsLabel, "cell 0 8,alignx leading");
		
		diskPartitionsTextField = new JTextField();
		diskPartitionsTextField.setEditable(false);
		diskDrivePanel.add(diskPartitionsTextField, "cell 1 8,growx");
		
		JLabel diskCapabilitiesLabel = new JLabel("Capabilities");
		diskDrivePanel.add(diskCapabilitiesLabel, "cell 0 9,alignx leading");
		
		diskCapabilitiesTextField = new JTextField();
		diskCapabilitiesTextField.setEditable(false);
		diskDrivePanel.add(diskCapabilitiesTextField, "cell 1 9,growx");
		
		JLabel diskStatusLabel = new JLabel("Status");
		diskDrivePanel.add(diskStatusLabel, "cell 0 10,alignx leading");
		
		diskStatusTextField = new JTextField();
		diskStatusTextField.setEditable(false);
		diskDrivePanel.add(diskStatusTextField, "cell 1 10,growx");
		
		return new JScrollPane(diskDrivePanel);
	}
	
	private void setDiskPartitionAndVolumePane() {
		
		JTabbedPane diskPartitionAndVolumePane = new JTabbedPane(JTabbedPane.TOP);
		add(diskPartitionAndVolumePane);
		
		// partition
		JPanel diskPartitionPanel = new JPanel();
		diskPartitionPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Partition Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		diskPartitionPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		diskPartitionEditorPane = new JEditorPane();
		diskPartitionEditorPane.setContentType("text/html");
		diskPartitionEditorPane.setEditable(false);
		
		diskPartitionPanel.add(new JScrollPane(diskPartitionEditorPane));
		
		// volume
		JPanel diskVolumePanel = new JPanel();
		diskVolumePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Volume Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		diskVolumePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		diskVolumeEditorPane = new JEditorPane();
		diskVolumeEditorPane.setContentType("text/html");
		diskVolumeEditorPane.setEditable(false);
		
		diskVolumePanel.add(new JScrollPane(diskVolumeEditorPane));
		
		// add panels to the tabbed pane
		diskPartitionAndVolumePane.addTab("Disk Partition", new FlatSVGIcon(WMIStoragePanelUI.class.getResource("/icons/tab_icons_material_green/DiskPartition.svg")), diskPartitionPanel, null);
		diskPartitionAndVolumePane.addTab("Logical Volume", new FlatSVGIcon(WMIStoragePanelUI.class.getResource("/icons/tab_icons_material_green/LogicalVolume.svg")), diskVolumePanel, null);
	}
	
	private void setWorker() {

		List<JTextField> storageFields = List.of(diskPnpDeviceIdTextField, diskCaptionTextField, diskModelTextField,
		diskFirmwareRevisionTextField, diskInterfaceTypeTextField, diskSerialNumberTextField, diskSizeTextField,
		diskPartitionsTextField, diskCapabilitiesTextField, diskStatusTextField);

		List<JEditorPane> editorPanes = List.of(diskPartitionEditorPane, diskVolumeEditorPane);

		new WMIStorageWorker(diskDeviceIdComboBox, storageFields, editorPanes).execute();
	}

}
