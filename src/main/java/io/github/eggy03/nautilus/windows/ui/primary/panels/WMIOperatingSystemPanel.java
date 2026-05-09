/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import io.github.eggy03.nautilus.windows.worker.WMIOperatingSystemWorker;
import io.github.eggy03.nautilus.windows.worker.WMIUserAccountWorker;
import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

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

    // OS Panel Components
    private final @NonNull JLabel osCurrentOsLabel = new JLabel("CurrentOS");
    private final @NonNull JLabel osCaptionLabel = new JLabel("Caption");
    private final @NonNull JLabel osVersionLabel = new JLabel("Version");
    private final @NonNull JLabel osBuildNumberLabel = new JLabel("Build Number");
    private final @NonNull JLabel osManufacturerLabel = new JLabel("Manufacturer");
    private final @NonNull JLabel osArchitectureLabel = new JLabel("Architecture");
    private final @NonNull JLabel osInstallDateLabel = new JLabel("Install Date");
    private final @NonNull JLabel osLastBootupTimeLabel = new JLabel("Last Bootup Time");
    private final @NonNull JLabel osSerialNumberLabel = new JLabel("Serial Number");
    private final @NonNull JLabel osLanguagesLabel = new JLabel("Languages");
    private final @NonNull JLabel osPrimaryLabel = new JLabel("Primary");
    private final @NonNull JLabel osDistributedLabel = new JLabel("Distributed");
    private final @NonNull JLabel osPortableLabel = new JLabel("Portable");
    private final @NonNull JLabel osDeviceNameLabel = new JLabel("Device Name");
    private final @NonNull JLabel osUserCountLabel = new JLabel("User Count");
    private final @NonNull JLabel osRegisteredUserLabel = new JLabel("Registered User");
    private final @NonNull JLabel osSystemDriveLabel = new JLabel("System Drive");
    private final @NonNull JLabel osWindowsDirectoryLabel = new JLabel("Windows Directory");
    private final @NonNull JLabel osSystemDirectoryLabel = new JLabel("System Directory");

    private final @NonNull JComboBox<String> osCurrentOsComboBox = new JComboBox<>();
    private final @NonNull JTextArea osConciseInfoTextArea = new JTextArea();
    private final @NonNull JTextField osCaptionTextField = new JTextField();
    private final @NonNull JTextField osVersionTextField = new JTextField();
    private final @NonNull JTextField osBuildNumberTextField = new JTextField();
    private final @NonNull JTextField osManufacturerTextField = new JTextField();
    private final @NonNull JTextField osArchitectureTextField = new JTextField();
    private final @NonNull JTextField osInstallDateTextField = new JTextField();
    private final @NonNull JTextField osLastBootupTimeTextField = new JTextField();
    private final @NonNull JTextField osSerialNumberTextField = new JTextField();
    private final @NonNull JTextField osLanguagesTextField = new JTextField();
    private final @NonNull JTextField osPrimaryTextField = new JTextField();
    private final @NonNull JTextField osDistributedTextField = new JTextField();
    private final @NonNull JTextField osPortableTextField = new JTextField();
    private final @NonNull JTextField osDeviceNameTextField = new JTextField();
    private final @NonNull JTextField osUserCountTextField = new JTextField();
    private final @NonNull JTextField osRegisteredUserTextField = new JTextField();
    private final @NonNull JTextField osSystemDriveTextField = new JTextField();
    private final @NonNull JTextField osWindowsDirectoryTextField = new JTextField();
    private final @NonNull JTextField osSystemDirectoryTextField = new JTextField();

    // User Account Components
    private final @NonNull JLabel userSIDLabel = new JLabel("SID");
    private final @NonNull JLabel userNameLabel = new JLabel("Name");
    private final @NonNull JLabel userCaptionLabel = new JLabel("Caption");
    private final @NonNull JLabel userDomainLabel = new JLabel("Domain");
    private final @NonNull JLabel userDescriptionLabel = new JLabel("Description");
    private final @NonNull JLabel userPasswordRequiredLabel = new JLabel("Password Required");
    private final @NonNull JLabel userPasswordChangeableLabel = new JLabel("Password Changeable");
    private final @NonNull JLabel userPasswordExpiresLabel = new JLabel("Password Expires");
    private final @NonNull JLabel userLocalAccountLabel = new JLabel("Local Account");
    private final @NonNull JLabel userDisabledLabel = new JLabel("Disabled");
    private final @NonNull JLabel userLockedOutLabel = new JLabel("Locked Out");
    private final @NonNull JLabel userAccountTypeLabel = new JLabel("Account Type");
    private final @NonNull JLabel userSIDTypeLabel = new JLabel("SID Type");
    private final @NonNull JLabel userStatusLabel = new JLabel("Status");

    private final @NonNull JComboBox<String> userAccountSIDComboBox = new JComboBox<>();
    private final @NonNull JTextField userNameTextField = new JTextField();
    private final @NonNull JTextField userCaptionTextField = new JTextField();
    private final @NonNull JTextField userDomainTextField = new JTextField();
    private final @NonNull JTextField userDescriptionTextField = new JTextField();
    private final @NonNull JTextField userPasswordRequiredTextField = new JTextField();
    private final @NonNull JTextField userPasswordChangeableTextField = new JTextField();
    private final @NonNull JTextField userPasswordExpiresTextField = new JTextField();
    private final @NonNull JTextField userLocalAccountTextField = new JTextField();
    private final @NonNull JTextField userDisabledTextField = new JTextField();
    private final @NonNull JTextField userLockedOutTextField = new JTextField();
    private final @NonNull JTextField userAccountTypeTextField = new JTextField();
    private final @NonNull JTextField userSIDTypeTextField = new JTextField();
    private final @NonNull JTextField userStatusTextField = new JTextField();


    public @NonNull WMIOperatingSystemPanel initUI() {
        setLayout(new GridLayout(2, 1, 0, 0));
        return this;
    }

    public @NonNull WMIOperatingSystemPanel initComponents() {
        add(new JScrollPane(createOsPanel()));
        add(new JScrollPane(createUserPanel()));
        return this;
    }

    private @NonNull JPanel createOsPanel() {

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

    private @NonNull JPanel createUserPanel() {

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

    public @NonNull WMIOperatingSystemPanel initWorkers() {

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
