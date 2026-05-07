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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import io.github.eggy03.nautilus.windows.worker.WMIOperatingSystemWorker;
import io.github.eggy03.nautilus.windows.worker.WMIUserAccountWorker;
import net.miginfocom.swing.MigLayout;

public class WMIOperatingSystemUI extends JPanel {
	
	// OS
	private JComboBox<String> osCurrentOsComboBox;
	private JTextArea osConciseInfoTextArea;
	
	private JTextField osCaptionTextField;
	private JTextField osVersionTextField;
	private JTextField osBuildNumberTextField;
	private JTextField osManufacturerTextField;
	private JTextField osArchitectureTextField;
	private JTextField osInstallDateTextField;
	private JTextField osLastBootupTimeTextField;
	private JTextField osSerialNumberTextField;
	private JTextField osLanguagesTextField;
	private JTextField osPrimaryTextField;
	private JTextField osDistributedTextField;
	private JTextField osPortableTextField;
	private JTextField osDeviceNameTextField;
	private JTextField osUserCountTextField;
	private JTextField osRegisteredUserTextField;
	private JTextField osSystemDriveTextField;
	private JTextField osWindowsDirectoryTextField;
	private JTextField osSystemDirectoryTextField;
	
	// User Account
	private JComboBox<String> userAccountSIDComboBox;

	private JTextField userNameTextField;
	private JTextField userCaptionTextField;
	private JTextField userDomainTextField;
	private JTextField userDescriptionTextField;
	private JTextField userPasswordRequiredTextField;
	private JTextField userPasswordChangeableTextField;
	private JTextField userPasswordExpiresTextField;
	private JTextField userLocalAccountTextField;
	private JTextField userDisabledTextField;
	private JTextField userLockedOutTextField;
	private JTextField userAccountTypeTextField;
	private JTextField userSIDTypeTextField;
	private JTextField userStatusTextField;
	
	public JPanel getPanel() {
		return this;
	}
	
	public WMIOperatingSystemUI() {
		
		setLayout(new GridLayout(2, 1, 0, 0));
		
		add(createOsPanel());
		add(createUserPanel());
		setWorker();
	}
	
	private JScrollPane createOsPanel() {
		
		JPanel operatingSystemPanel = new JPanel();
		operatingSystemPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Operating System", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		operatingSystemPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][][][][][][]"));
		
		JLabel osCurrrentOsLabel = new JLabel("CurrentOS");
		operatingSystemPanel.add(osCurrrentOsLabel, "cell 0 0,alignx leading");
		
		osCurrentOsComboBox = new JComboBox<>();
		operatingSystemPanel.add(osCurrentOsComboBox, "cell 1 0 5 1,growx");
		
		JLabel osCaptionLabel = new JLabel("Caption");
		operatingSystemPanel.add(osCaptionLabel, "cell 0 1,alignx leading");
		
		osCaptionTextField = new JTextField();
		osCaptionTextField.setEditable(false);
		operatingSystemPanel.add(osCaptionTextField, "cell 1 1 3 1,growx");
		
		JPanel osConciseInfoPanel = new JPanel();
		osConciseInfoPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Concise Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		osConciseInfoPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		osConciseInfoTextArea = new JTextArea();
		osConciseInfoTextArea.setEditable(false);
		osConciseInfoPanel.add(osConciseInfoTextArea);
		
		operatingSystemPanel.add(osConciseInfoPanel, "cell 4 1 2 4,grow");
		
		JLabel osVersionLabel = new JLabel("Version");
		operatingSystemPanel.add(osVersionLabel, "cell 0 2,alignx leading");
		
		osVersionTextField = new JTextField();
		osVersionTextField.setEditable(false);
		operatingSystemPanel.add(osVersionTextField, "cell 1 2,growx");
		
		JLabel osBuildNumberLabel = new JLabel("Build Number");
		operatingSystemPanel.add(osBuildNumberLabel, "cell 2 2,alignx leading");
		
		osBuildNumberTextField = new JTextField();
		osBuildNumberTextField.setEditable(false);
		operatingSystemPanel.add(osBuildNumberTextField, "cell 3 2,growx");
		
		JLabel osManufacturerLabel = new JLabel("Manufacturer");
		operatingSystemPanel.add(osManufacturerLabel, "cell 0 3,alignx leading");
		
		osManufacturerTextField = new JTextField();
		osManufacturerTextField.setEditable(false);
		operatingSystemPanel.add(osManufacturerTextField, "cell 1 3,growx");
		
		JLabel osArchitectureLabel = new JLabel("Architecture");
		operatingSystemPanel.add(osArchitectureLabel, "cell 2 3,alignx leading");
		
		osArchitectureTextField = new JTextField();
		osArchitectureTextField.setEditable(false);
		operatingSystemPanel.add(osArchitectureTextField, "cell 3 3,growx");
		
		JLabel osInstallDateLabel = new JLabel("Install Date");
		operatingSystemPanel.add(osInstallDateLabel, "cell 0 4,alignx leading");
		
		osInstallDateTextField = new JTextField();
		osInstallDateTextField.setEditable(false);
		operatingSystemPanel.add(osInstallDateTextField, "cell 1 4,growx");
		
		JLabel osLastBootupTimeLabel = new JLabel("Last Bootup Time");
		operatingSystemPanel.add(osLastBootupTimeLabel, "cell 2 4,alignx leading");
		
		osLastBootupTimeTextField = new JTextField();
		osLastBootupTimeTextField.setEditable(false);
		operatingSystemPanel.add(osLastBootupTimeTextField, "cell 3 4,growx");
		
		JLabel osSerialNumberLabel = new JLabel("Serial Number");
		operatingSystemPanel.add(osSerialNumberLabel, "cell 0 5,alignx leading");
		
		osSerialNumberTextField = new JTextField();
		osSerialNumberTextField.setEditable(false);
		operatingSystemPanel.add(osSerialNumberTextField, "cell 1 5 3 1,growx");
		
		JLabel osLanguagesLabel = new JLabel("Languages");
		operatingSystemPanel.add(osLanguagesLabel, "cell 4 5,alignx leading");
		
		osLanguagesTextField = new JTextField();
		osLanguagesTextField.setEditable(false);
		operatingSystemPanel.add(osLanguagesTextField, "cell 5 5,growx");
		
		JLabel osPrimaryLabel = new JLabel("Primary");
		operatingSystemPanel.add(osPrimaryLabel, "cell 0 6,alignx leading");
		
		osPrimaryTextField = new JTextField();
		osPrimaryTextField.setEditable(false);
		operatingSystemPanel.add(osPrimaryTextField, "cell 1 6,growx");
		
		JLabel osDistributedLabel = new JLabel("Distributed");
		operatingSystemPanel.add(osDistributedLabel, "cell 2 6,alignx leading");
		
		osDistributedTextField = new JTextField();
		osDistributedTextField.setEditable(false);
		operatingSystemPanel.add(osDistributedTextField, "cell 3 6,growx");
		
		JLabel osPortableLabel = new JLabel("Portable");
		operatingSystemPanel.add(osPortableLabel, "cell 4 6,alignx leading");
		
		osPortableTextField = new JTextField();
		osPortableTextField.setEditable(false);
		operatingSystemPanel.add(osPortableTextField, "cell 5 6,growx");
		
		JLabel osDeviceNameLabel = new JLabel("Device Name");
		operatingSystemPanel.add(osDeviceNameLabel, "cell 0 7,alignx leading");
		
		osDeviceNameTextField = new JTextField();
		osDeviceNameTextField.setEditable(false);
		operatingSystemPanel.add(osDeviceNameTextField, "cell 1 7,growx");
		
		JLabel osUserCountLabel = new JLabel("User Count");
		operatingSystemPanel.add(osUserCountLabel, "cell 2 7,alignx leading");
		
		osUserCountTextField = new JTextField();
		osUserCountTextField.setEditable(false);
		operatingSystemPanel.add(osUserCountTextField, "cell 3 7,growx");
		
		JLabel osRegisteredUserLabel = new JLabel("Registered User");
		operatingSystemPanel.add(osRegisteredUserLabel, "cell 4 7,alignx leading");
		
		osRegisteredUserTextField = new JTextField();
		osRegisteredUserTextField.setEditable(false);
		operatingSystemPanel.add(osRegisteredUserTextField, "cell 5 7,growx");
		
		JLabel osSystemDriveLabel = new JLabel("System Drive");
		operatingSystemPanel.add(osSystemDriveLabel, "cell 0 8,alignx leading");
		
		osSystemDriveTextField = new JTextField();
		osSystemDriveTextField.setEditable(false);
		operatingSystemPanel.add(osSystemDriveTextField, "cell 1 8,growx");
		
		JLabel osWindowsDirectoryLabel = new JLabel("Windows Directory");
		operatingSystemPanel.add(osWindowsDirectoryLabel, "cell 2 8,alignx leading");
		
		osWindowsDirectoryTextField = new JTextField();
		osWindowsDirectoryTextField.setEditable(false);
		operatingSystemPanel.add(osWindowsDirectoryTextField, "cell 3 8,growx");
		
		JLabel osSystemDirectoryLabel = new JLabel("System Directory");
		operatingSystemPanel.add(osSystemDirectoryLabel, "cell 4 8,alignx leading");
		
		osSystemDirectoryTextField = new JTextField();
		osSystemDirectoryTextField.setEditable(false);
		operatingSystemPanel.add(osSystemDirectoryTextField, "cell 5 8,growx");
		
		return new JScrollPane(operatingSystemPanel);
	}
	
	private JScrollPane createUserPanel() {
		
		JPanel userPanel = new JPanel();
		userPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][][][][]"));
		
		JLabel userSIDLabel = new JLabel("SID");
		userPanel.add(userSIDLabel, "cell 0 0,alignx leading");
		
		userAccountSIDComboBox = new JComboBox<>();
		userPanel.add(userAccountSIDComboBox, "cell 1 0 5 1,growx");
		
		JLabel userNameLabel = new JLabel("Name");
		userPanel.add(userNameLabel, "cell 0 1,alignx leading");
		
		userNameTextField = new JTextField();
		userNameTextField.setEditable(false);
		userPanel.add(userNameTextField, "cell 1 1,growx");
		
		JLabel userCaptionLabel = new JLabel("Caption");
		userPanel.add(userCaptionLabel, "cell 2 1,alignx leading");
		
		userCaptionTextField = new JTextField();
		userCaptionTextField.setEditable(false);
		userPanel.add(userCaptionTextField, "cell 3 1,growx");
		
		JLabel userDomainLabel = new JLabel("Domain");
		userPanel.add(userDomainLabel, "cell 4 1,alignx leading");
		
		userDomainTextField = new JTextField();
		userDomainTextField.setEditable(false);
		userPanel.add(userDomainTextField, "cell 5 1,growx");
		
		JLabel userDescriptionLabel = new JLabel("Description");
		userPanel.add(userDescriptionLabel, "cell 0 2,alignx leading");
		
		userDescriptionTextField = new JTextField();
		userDescriptionTextField.setEditable(false);
		userPanel.add(userDescriptionTextField, "cell 1 2 5 1,growx");
		
		JLabel userPasswordRequiredLabel = new JLabel("Password Required");
		userPanel.add(userPasswordRequiredLabel, "cell 0 3,alignx leading");
		
		userPasswordRequiredTextField = new JTextField();
		userPasswordRequiredTextField.setEditable(false);
		userPanel.add(userPasswordRequiredTextField, "cell 1 3,growx");
		
		JLabel userPasswordChangeableLabel = new JLabel("Password Changeable");
		userPanel.add(userPasswordChangeableLabel, "cell 2 3,alignx leading");
		
		userPasswordChangeableTextField = new JTextField();
		userPasswordChangeableTextField.setEditable(false);
		userPanel.add(userPasswordChangeableTextField, "cell 3 3,growx");
		
		JLabel userPasswordExpiresLabel = new JLabel("Password Expires");
		userPanel.add(userPasswordExpiresLabel, "cell 4 3,alignx leading");
		
		userPasswordExpiresTextField = new JTextField();
		userPasswordExpiresTextField.setEditable(false);
		userPanel.add(userPasswordExpiresTextField, "cell 5 3,growx");
		
		JLabel userLocalAccountLabel = new JLabel("Local Account");
		userPanel.add(userLocalAccountLabel, "cell 0 4,alignx leading");
		
		userLocalAccountTextField = new JTextField();
		userLocalAccountTextField.setEditable(false);
		userPanel.add(userLocalAccountTextField, "cell 1 4,growx");
		
		JLabel userDisabledLabel = new JLabel("Disabled");
		userPanel.add(userDisabledLabel, "cell 2 4,alignx leading");
		
		userDisabledTextField = new JTextField();
		userDisabledTextField.setEditable(false);
		userPanel.add(userDisabledTextField, "cell 3 4,growx");
		
		JLabel userLockedOutLabel = new JLabel("Locked Out");
		userPanel.add(userLockedOutLabel, "cell 4 4,alignx leading");
		
		userLockedOutTextField = new JTextField();
		userLockedOutTextField.setEditable(false);
		userPanel.add(userLockedOutTextField, "cell 5 4,growx");
		
		JLabel userAccountTypeLabel = new JLabel("Account Type");
		userPanel.add(userAccountTypeLabel, "cell 0 5,alignx leading");
		
		userAccountTypeTextField = new JTextField();
		userAccountTypeTextField.setEditable(false);
		userPanel.add(userAccountTypeTextField, "cell 1 5,growx");
		
		JLabel userSIDTypeLabel = new JLabel("SID Type");
		userPanel.add(userSIDTypeLabel, "cell 2 5,alignx leading");
		
		userSIDTypeTextField = new JTextField();
		userSIDTypeTextField.setEditable(false);
		userPanel.add(userSIDTypeTextField, "cell 3 5,growx");
		
		JLabel userStatusLabel = new JLabel("Status");
		userPanel.add(userStatusLabel, "cell 4 5,alignx leading");
		
		userStatusTextField = new JTextField();
		userStatusTextField.setEditable(false);
		userPanel.add(userStatusTextField, "cell 5 5,growx");
		
		return new JScrollPane(userPanel);
	}
	
	private void setWorker() {

		List<JTextField> osFields = List.of(osCaptionTextField, osVersionTextField, osBuildNumberTextField,
		osManufacturerTextField, osArchitectureTextField, osInstallDateTextField, osLastBootupTimeTextField,
		osSerialNumberTextField, osLanguagesTextField, osPrimaryTextField, osDistributedTextField,
		osPortableTextField, osDeviceNameTextField, osUserCountTextField, osRegisteredUserTextField,
		osSystemDriveTextField, osWindowsDirectoryTextField, osSystemDirectoryTextField);
		
		new WMIOperatingSystemWorker(osCurrentOsComboBox, osFields, osConciseInfoTextArea).execute();

		List<JTextField> userAccountFields = List.of(userNameTextField, userCaptionTextField, userDomainTextField,
		userDescriptionTextField, userPasswordRequiredTextField, userPasswordChangeableTextField,
		userPasswordExpiresTextField, userLocalAccountTextField, userDisabledTextField,
		userLockedOutTextField, userAccountTypeTextField, userSIDTypeTextField, userStatusTextField);

		new WMIUserAccountWorker(userAccountSIDComboBox, userAccountFields).execute();
		
	}

}
