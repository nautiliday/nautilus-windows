/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import io.github.eggy03.nautilus.windows.worker.WMIVideoControllerPanelWorker;
import net.miginfocom.swing.MigLayout;

public class WMIVideoControllerPanelUI extends JPanel {
	
	private JComboBox<String> gpuDeviceIdComboBox;
	private JTextField gpuNameTextField;
	private JTextField gpuPnPDeviceIdTextField;
	private JTextField gpuHorizontalResTextField;
	private JTextField gpuVerticalResTextField;
	private JTextField gpuBitsPerPixelTextField;
	private JTextField gpuMinRefreshRateTextField;
	private JTextField gpuMaxRefreshRateTextField;
	private JTextField gpuCurrentRefreshRateTextField;
	private JTextField gpuAdapterDACTypeTextField;
	private JTextField gpuVRAMTextField;
	private JTextField gpuDriverVersionTextField;
	private JTextField gpuDriverDateTextField;
	private JTextField gpuVideoProcessorTextField;
	
	public JPanel getPanel() {
		return this;
	}
	
	public WMIVideoControllerPanelUI() {
		setLayout(new MigLayout("insets 0", "[grow][grow]", "[grow][grow]"));
		
		add(createIdentifierPanel(), "cell 0 0 2 1, grow");
		add(createDisplayPanel(), "cell 0 1, grow");
		add(createDriverPanel(), "cell 1 1, grow");

		setWorker();
	}
	
	private JScrollPane createIdentifierPanel() {
		JPanel identifierPanel = new JPanel();
		identifierPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Identifier", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		identifierPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][]"));
		
		JLabel gpuDeviceIdLabel = new JLabel("Device ID");
		identifierPanel.add(gpuDeviceIdLabel, "cell 0 0,alignx leading");
		
		gpuDeviceIdComboBox = new JComboBox<>();
		identifierPanel.add(gpuDeviceIdComboBox, "cell 1 0,growx");
		
		JLabel gpuNameLabel = new JLabel("Name");
		identifierPanel.add(gpuNameLabel, "cell 0 1,alignx leading");
		
		gpuNameTextField = new JTextField();
		gpuNameTextField.setEditable(false);
		identifierPanel.add(gpuNameTextField, "cell 1 1,growx");
		
		JLabel gpuPnPDeviceIdLabel = new JLabel("PnP Dev. ID");
		identifierPanel.add(gpuPnPDeviceIdLabel, "cell 0 2,alignx leading");
		
		gpuPnPDeviceIdTextField = new JTextField();
		gpuPnPDeviceIdTextField.setEditable(false);
		identifierPanel.add(gpuPnPDeviceIdTextField, "cell 1 2,growx");
		
		return new JScrollPane(identifierPanel);
	}
	
	private JScrollPane createDisplayPanel() {
		
		JPanel displayPanel = new JPanel();
		displayPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		displayPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][]"));
		
		JLabel gpuHorizontalResLabel = new JLabel("Horizontal Res.");
		displayPanel.add(gpuHorizontalResLabel, "cell 0 0,alignx leading");
		
		gpuHorizontalResTextField = new JTextField();
		gpuHorizontalResTextField.setEditable(false);
		displayPanel.add(gpuHorizontalResTextField, "cell 1 0,growx");
		
		JLabel gpuVerticalResLabel = new JLabel("Vertical Res.");
		displayPanel.add(gpuVerticalResLabel, "cell 0 1,alignx leading");
		
		gpuVerticalResTextField = new JTextField();
		gpuVerticalResTextField.setEditable(false);
		displayPanel.add(gpuVerticalResTextField, "cell 1 1,growx");
		
		JLabel gpuBitsPerPixelLabel = new JLabel("Bits Per Pixel");
		displayPanel.add(gpuBitsPerPixelLabel, "cell 0 2,alignx leading");
		
		gpuBitsPerPixelTextField = new JTextField();
		gpuBitsPerPixelTextField.setEditable(false);
		displayPanel.add(gpuBitsPerPixelTextField, "cell 1 2,growx");
		
		JLabel gpuMinRefreshRateLabel = new JLabel("Min Refresh Rate");
		displayPanel.add(gpuMinRefreshRateLabel, "cell 0 3,alignx leading");
		
		gpuMinRefreshRateTextField = new JTextField();
		gpuMinRefreshRateTextField.setEditable(false);
		displayPanel.add(gpuMinRefreshRateTextField, "cell 1 3,growx");
		
		JLabel gpuMaxRefreshRateLabel = new JLabel("Max Refresh Rate");
		displayPanel.add(gpuMaxRefreshRateLabel, "cell 0 4,alignx leading");
		
		gpuMaxRefreshRateTextField = new JTextField();
		gpuMaxRefreshRateTextField.setEditable(false);
		displayPanel.add(gpuMaxRefreshRateTextField, "cell 1 4,growx");
		
		JLabel gpuCurrentRefreshRateLabel = new JLabel("Current Refresh Rate");
		displayPanel.add(gpuCurrentRefreshRateLabel, "cell 0 5,alignx leading");
		
		gpuCurrentRefreshRateTextField = new JTextField();
		gpuCurrentRefreshRateTextField.setEditable(false);
		displayPanel.add(gpuCurrentRefreshRateTextField, "cell 1 5,growx");
		
		return new JScrollPane(displayPanel);
	}
	
	private JScrollPane createDriverPanel() {
		
		JPanel driverPanel = new JPanel();
		driverPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Driver", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		driverPanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][]"));
		
		JLabel gpuAdapterDACTypeLabel = new JLabel("Adapter DAC Type");
		driverPanel.add(gpuAdapterDACTypeLabel, "cell 0 0,alignx leading");
		
		gpuAdapterDACTypeTextField = new JTextField();
		gpuAdapterDACTypeTextField.setEditable(false);
		driverPanel.add(gpuAdapterDACTypeTextField, "cell 1 0,growx");
		
		JLabel gpuVRAMLabel = new JLabel("VRAM");
		driverPanel.add(gpuVRAMLabel, "cell 0 1,alignx leading");
		
		gpuVRAMTextField = new JTextField();
		gpuVRAMTextField.setEditable(false);
		driverPanel.add(gpuVRAMTextField, "cell 1 1,growx");
		
		JLabel gpuDriverVersionLabel = new JLabel("Driver Version");
		driverPanel.add(gpuDriverVersionLabel, "cell 0 2,alignx leading");
		
		gpuDriverVersionTextField = new JTextField();
		gpuDriverVersionTextField.setEditable(false);
		driverPanel.add(gpuDriverVersionTextField, "cell 1 2,growx");
		
		JLabel gpuDriverDateLabel = new JLabel("Driver Date");
		driverPanel.add(gpuDriverDateLabel, "cell 0 3,alignx leading");
		
		gpuDriverDateTextField = new JTextField();
		gpuDriverDateTextField.setEditable(false);
		driverPanel.add(gpuDriverDateTextField, "cell 1 3,growx");
		
		JLabel gpuVideoProcessorLabel = new JLabel("Video Processor");
		driverPanel.add(gpuVideoProcessorLabel, "cell 0 4,alignx leading");
		
		gpuVideoProcessorTextField = new JTextField();
		gpuVideoProcessorTextField.setEditable(false);
		driverPanel.add(gpuVideoProcessorTextField, "cell 1 4,growx");
		
		return new JScrollPane(driverPanel);
	}

	private void setWorker() {

		List<JTextField> gpuFields = List.of(
		gpuNameTextField, gpuPnPDeviceIdTextField, gpuHorizontalResTextField, gpuVerticalResTextField,
		gpuBitsPerPixelTextField, gpuMinRefreshRateTextField, gpuMaxRefreshRateTextField, gpuCurrentRefreshRateTextField,
		gpuAdapterDACTypeTextField, gpuVRAMTextField, gpuDriverVersionTextField, gpuDriverDateTextField,
		gpuVideoProcessorTextField
		);
		
		new WMIVideoControllerPanelWorker(gpuDeviceIdComboBox, gpuFields).execute();
	}
}
