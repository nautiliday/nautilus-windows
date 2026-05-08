/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIOperatingSystemWorker;
import io.github.eggy03.nautilus.windows.worker.WMIUserAccountWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.util.List;

public class WMIOperatingSystemPanel extends JPanel {

    // Init Components in the main panel
    // the main panel consists of 2 sub panels

    private final JComboBox<String> osCurrentOsComboBox = new JComboBox<>();
    private final JTextArea osConciseInfoTextArea = new JTextArea();
    private final JTextField osCaptionTextField = new JTextField();
    private final JTextField osVersionTextField = new JTextField();
    private final JTextField osBuildNumberTextField = new JTextField();
    private final JTextField osManufacturerTextField = new JTextField();
    private final JTextField osArchitectureTextField = new JTextField();
    private final JTextField osInstallDateTextField = new JTextField();
    private final JTextField osLastBootupTimeTextField = new JTextField();
    private final JTextField osSerialNumberTextField = new JTextField();
    private final JTextField osLanguagesTextField = new JTextField();
    private final JTextField osPrimaryTextField = new JTextField();
    private final JTextField osDistributedTextField = new JTextField();
    private final JTextField osPortableTextField = new JTextField();
    private final JTextField osDeviceNameTextField = new JTextField();
    private final JTextField osUserCountTextField = new JTextField();
    private final JTextField osRegisteredUserTextField = new JTextField();
    private final JTextField osSystemDriveTextField = new JTextField();
    private final JTextField osWindowsDirectoryTextField = new JTextField();
    private final JTextField osSystemDirectoryTextField = new JTextField();
    // User Account Components
    private final JLabel userSIDLabel = new JLabel("SID");
    private final JComboBox<String> userAccountSIDComboBox = new JComboBox<>();
    private final JLabel userNameLabel = new JLabel("Name");
    private final JTextField userNameTextField = new JTextField();
    private final JLabel userCaptionLabel = new JLabel("Caption");
    private final JTextField userCaptionTextField = new JTextField();
    private final JLabel userDomainLabel = new JLabel("Domain");
    private final JTextField userDomainTextField = new JTextField();
    private final JLabel userDescriptionLabel = new JLabel("Description");
    private final JTextField userDescriptionTextField = new JTextField();
    private final JLabel userPasswordRequiredLabel = new JLabel("Password Required");
    private final JTextField userPasswordRequiredTextField = new JTextField();
    private final JLabel userPasswordChangeableLabel = new JLabel("Password Changeable");
    private final JTextField userPasswordChangeableTextField = new JTextField();
    private final JLabel userPasswordExpiresLabel = new JLabel("Password Expires");
    private final JTextField userPasswordExpiresTextField = new JTextField();
    private final JLabel userLocalAccountLabel = new JLabel("Local Account");
    private final JTextField userLocalAccountTextField = new JTextField();
    private final JLabel userDisabledLabel = new JLabel("Disabled");
    private final JTextField userDisabledTextField = new JTextField();
    private final JLabel userLockedOutLabel = new JLabel("Locked Out");
    private final JTextField userLockedOutTextField = new JTextField();
    private final JLabel userAccountTypeLabel = new JLabel("Account Type");
    private final JTextField userAccountTypeTextField = new JTextField();
    private final JLabel userSIDTypeLabel = new JLabel("SID Type");
    private final JTextField userSIDTypeTextField = new JTextField();
    private final JLabel userStatusLabel = new JLabel("Status");
    private final JTextField userStatusTextField = new JTextField();
    // OS Panel Components
    private final JLabel osCurrentOsLabel = new JLabel("CurrentOS");
    private final JLabel osCaptionLabel = new JLabel("Caption");
    private final JLabel osVersionLabel = new JLabel("Version");
    private final JLabel osBuildNumberLabel = new JLabel("Build Number");
    private final JLabel osManufacturerLabel = new JLabel("Manufacturer");
    private final JLabel osArchitectureLabel = new JLabel("Architecture");
    private final JLabel osInstallDateLabel = new JLabel("Install Date");
    private final JLabel osLastBootupTimeLabel = new JLabel("Last Bootup Time");
    private final JLabel osSerialNumberLabel = new JLabel("Serial Number");
    private final JLabel osLanguagesLabel = new JLabel("Languages");
    private final JLabel osPrimaryLabel = new JLabel("Primary");
    private final JLabel osDistributedLabel = new JLabel("Distributed");
    private final JLabel osPortableLabel = new JLabel("Portable");
    private final JLabel osDeviceNameLabel = new JLabel("Device Name");
    private final JLabel osUserCountLabel = new JLabel("User Count");
    private final JLabel osRegisteredUserLabel = new JLabel("Registered User");
    private final JLabel osSystemDriveLabel = new JLabel("System Drive");
    private final JLabel osWindowsDirectoryLabel = new JLabel("Windows Directory");
    private final JLabel osSystemDirectoryLabel = new JLabel("System Directory");

    public WMIOperatingSystemPanel initUI() {
        setLayout(new GridLayout(2, 1, 0, 0));
        return this;
    }

    public WMIOperatingSystemPanel initComponents() {
        add(new JScrollPane(createOsPanel()));
        add(new JScrollPane(createUserPanel()));
        return this;
    }

    private JPanel createOsPanel() {

        final JPanel operatingSystemPanel = new JPanel();
        operatingSystemPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Operating System", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        operatingSystemPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][][][][][][]"));

        operatingSystemPanel.add(osCurrentOsLabel, "cell 0 0,alignx leading");
        operatingSystemPanel.add(osCurrentOsComboBox, "cell 1 0 5 1,growx");

        operatingSystemPanel.add(osCaptionLabel, "cell 0 1,alignx leading");
        operatingSystemPanel.add(osCaptionTextField, "cell 1 1 3 1,growx");

        operatingSystemPanel.add(new JScrollPane(osConciseInfoTextArea), "cell 4 1 2 4,grow");

        operatingSystemPanel.add(osVersionLabel, "cell 0 2,alignx leading");
        operatingSystemPanel.add(osVersionTextField, "cell 1 2,growx");

        operatingSystemPanel.add(osBuildNumberLabel, "cell 2 2,alignx leading");
        operatingSystemPanel.add(osBuildNumberTextField, "cell 3 2,growx");

        operatingSystemPanel.add(osManufacturerLabel, "cell 0 3,alignx leading");
        operatingSystemPanel.add(osManufacturerTextField, "cell 1 3,growx");

        operatingSystemPanel.add(osArchitectureLabel, "cell 2 3,alignx leading");
        operatingSystemPanel.add(osArchitectureTextField, "cell 3 3,growx");

        operatingSystemPanel.add(osInstallDateLabel, "cell 0 4,alignx leading");
        operatingSystemPanel.add(osInstallDateTextField, "cell 1 4,growx");

        operatingSystemPanel.add(osLastBootupTimeLabel, "cell 2 4,alignx leading");
        operatingSystemPanel.add(osLastBootupTimeTextField, "cell 3 4,growx");

        operatingSystemPanel.add(osSerialNumberLabel, "cell 0 5,alignx leading");
        operatingSystemPanel.add(osSerialNumberTextField, "cell 1 5 3 1,growx");

        operatingSystemPanel.add(osLanguagesLabel, "cell 4 5,alignx leading");
        operatingSystemPanel.add(osLanguagesTextField, "cell 5 5,growx");

        operatingSystemPanel.add(osPrimaryLabel, "cell 0 6,alignx leading");
        operatingSystemPanel.add(osPrimaryTextField, "cell 1 6,growx");

        operatingSystemPanel.add(osDistributedLabel, "cell 2 6,alignx leading");
        operatingSystemPanel.add(osDistributedTextField, "cell 3 6,growx");

        operatingSystemPanel.add(osPortableLabel, "cell 4 6,alignx leading");
        operatingSystemPanel.add(osPortableTextField, "cell 5 6,growx");

        operatingSystemPanel.add(osDeviceNameLabel, "cell 0 7,alignx leading");
        operatingSystemPanel.add(osDeviceNameTextField, "cell 1 7,growx");

        operatingSystemPanel.add(osUserCountLabel, "cell 2 7,alignx leading");
        operatingSystemPanel.add(osUserCountTextField, "cell 3 7,growx");

        operatingSystemPanel.add(osRegisteredUserLabel, "cell 4 7,alignx leading");
        operatingSystemPanel.add(osRegisteredUserTextField, "cell 5 7,growx");

        operatingSystemPanel.add(osSystemDriveLabel, "cell 0 8,alignx leading");
        operatingSystemPanel.add(osSystemDriveTextField, "cell 1 8,growx");

        operatingSystemPanel.add(osWindowsDirectoryLabel, "cell 2 8,alignx leading");
        operatingSystemPanel.add(osWindowsDirectoryTextField, "cell 3 8,growx");

        operatingSystemPanel.add(osSystemDirectoryLabel, "cell 4 8,alignx leading");
        operatingSystemPanel.add(osSystemDirectoryTextField, "cell 5 8,growx");

        osConciseInfoTextArea.setEditable(false);
        osCaptionTextField.setEditable(false);
        osVersionTextField.setEditable(false);
        osBuildNumberTextField.setEditable(false);
        osManufacturerTextField.setEditable(false);
        osArchitectureTextField.setEditable(false);
        osInstallDateTextField.setEditable(false);
        osLastBootupTimeTextField.setEditable(false);
        osSerialNumberTextField.setEditable(false);
        osLanguagesTextField.setEditable(false);
        osPrimaryTextField.setEditable(false);
        osDistributedTextField.setEditable(false);
        osPortableTextField.setEditable(false);
        osDeviceNameTextField.setEditable(false);
        osUserCountTextField.setEditable(false);
        osRegisteredUserTextField.setEditable(false);
        osSystemDriveTextField.setEditable(false);
        osWindowsDirectoryTextField.setEditable(false);
        osSystemDirectoryTextField.setEditable(false);

        return operatingSystemPanel;
    }

    private JPanel createUserPanel() {

        final JPanel userPanel = new JPanel();
        userPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "User", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        userPanel.setLayout(new MigLayout("insets 0", "[][grow][][grow][][grow]", "[][][][][][]"));

        userPanel.add(userSIDLabel, "cell 0 0,alignx leading");
        userPanel.add(userAccountSIDComboBox, "cell 1 0 5 1,growx");

        userPanel.add(userNameLabel, "cell 0 1,alignx leading");
        userPanel.add(userNameTextField, "cell 1 1,growx");

        userPanel.add(userCaptionLabel, "cell 2 1,alignx leading");
        userPanel.add(userCaptionTextField, "cell 3 1,growx");

        userPanel.add(userDomainLabel, "cell 4 1,alignx leading");
        userPanel.add(userDomainTextField, "cell 5 1,growx");

        userPanel.add(userDescriptionLabel, "cell 0 2,alignx leading");
        userPanel.add(userDescriptionTextField, "cell 1 2 5 1,growx");

        userPanel.add(userPasswordRequiredLabel, "cell 0 3,alignx leading");
        userPanel.add(userPasswordRequiredTextField, "cell 1 3,growx");

        userPanel.add(userPasswordChangeableLabel, "cell 2 3,alignx leading");
        userPanel.add(userPasswordChangeableTextField, "cell 3 3,growx");

        userPanel.add(userPasswordExpiresLabel, "cell 4 3,alignx leading");
        userPanel.add(userPasswordExpiresTextField, "cell 5 3,growx");

        userPanel.add(userLocalAccountLabel, "cell 0 4,alignx leading");
        userPanel.add(userLocalAccountTextField, "cell 1 4,growx");

        userPanel.add(userDisabledLabel, "cell 2 4,alignx leading");
        userPanel.add(userDisabledTextField, "cell 3 4,growx");

        userPanel.add(userLockedOutLabel, "cell 4 4,alignx leading");
        userPanel.add(userLockedOutTextField, "cell 5 4,growx");

        userPanel.add(userAccountTypeLabel, "cell 0 5,alignx leading");
        userPanel.add(userAccountTypeTextField, "cell 1 5,growx");

        userPanel.add(userSIDTypeLabel, "cell 2 5,alignx leading");
        userPanel.add(userSIDTypeTextField, "cell 3 5,growx");

        userPanel.add(userStatusLabel, "cell 4 5,alignx leading");
        userPanel.add(userStatusTextField, "cell 5 5,growx");

        userNameTextField.setEditable(false);
        userCaptionTextField.setEditable(false);
        userDomainTextField.setEditable(false);
        userDescriptionTextField.setEditable(false);
        userPasswordRequiredTextField.setEditable(false);
        userPasswordChangeableTextField.setEditable(false);
        userPasswordExpiresTextField.setEditable(false);
        userLocalAccountTextField.setEditable(false);
        userDisabledTextField.setEditable(false);
        userLockedOutTextField.setEditable(false);
        userAccountTypeTextField.setEditable(false);
        userSIDTypeTextField.setEditable(false);
        userStatusTextField.setEditable(false);

        return userPanel;
    }

    public WMIOperatingSystemPanel initWorkers() {

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

        return this;

    }

}
