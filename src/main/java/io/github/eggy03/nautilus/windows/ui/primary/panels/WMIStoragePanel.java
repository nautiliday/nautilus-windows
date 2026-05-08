/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.worker.WMIStorageWorker;
import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.util.List;

public class WMIStoragePanel extends JPanel {

    private final JLabel diskDeviceIdLabel = new JLabel("Disk#");
    private final JComboBox<String> diskDeviceIdComboBox = new JComboBox<>();

    private final JLabel diskPnpDeviceIdLabel = new JLabel("PnP DeviceID");
    private final JTextField diskPnpDeviceIdTextField = new JTextField();

    private final JLabel diskCaptionLabel = new JLabel("Name");
    private final JTextField diskCaptionTextField = new JTextField();

    private final JLabel diskModelLabel = new JLabel("Model");
    private final JTextField diskModelTextField = new JTextField();

    private final JLabel diskFirmwareRevisionLabel = new JLabel("Firmware");
    private final JTextField diskFirmwareRevisionTextField = new JTextField();

    private final JLabel diskInterfaceTypeLabel = new JLabel("Interface");
    private final JTextField diskInterfaceTypeTextField = new JTextField();

    private final JLabel diskSerialNumberLabel = new JLabel("Serial Number");
    private final JTextField diskSerialNumberTextField = new JTextField();

    private final JLabel diskSizeLabel = new JLabel("Size");
    private final JTextField diskSizeTextField = new JTextField();

    private final JLabel diskPartitionsLabel = new JLabel("Partitions");
    private final JTextField diskPartitionsTextField = new JTextField();

    private final JLabel diskCapabilitiesLabel = new JLabel("Capabilities");
    private final JTextField diskCapabilitiesTextField = new JTextField();

    private final JLabel diskStatusLabel = new JLabel("Status");
    private final JTextField diskStatusTextField = new JTextField();

    private final JEditorPane diskPartitionEditorPane = new JEditorPane();
    private final JEditorPane diskVolumeEditorPane = new JEditorPane();

    public WMIStoragePanel initUI() {
        setLayout(new GridLayout(2, 1, 0, 0));

        return this;
    }

    public WMIStoragePanel initComponents() {

        add(new JScrollPane(createDiskDrivePanel()));
        add(createDiskPartitionAndVolumeTabbedPane());

        return this;
    }

    private JPanel createDiskDrivePanel() {

        final JPanel diskDrivePanel = new JPanel();
        diskDrivePanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Disk Drive", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        diskDrivePanel.setLayout(new MigLayout("insets 0", "[][grow]", "[][][][][][][][][][][]"));

        diskDrivePanel.add(diskDeviceIdLabel, "cell 0 0,alignx leading");
        diskDrivePanel.add(diskDeviceIdComboBox, "cell 1 0,growx");

        diskDrivePanel.add(diskPnpDeviceIdLabel, "cell 0 1,alignx leading");
        diskDrivePanel.add(diskPnpDeviceIdTextField, "cell 1 1,growx");

        diskDrivePanel.add(diskCaptionLabel, "cell 0 2,alignx leading");
        diskDrivePanel.add(diskCaptionTextField, "cell 1 2,growx");

        diskDrivePanel.add(diskModelLabel, "cell 0 3,alignx leading");
        diskDrivePanel.add(diskModelTextField, "cell 1 3,growx");

        diskDrivePanel.add(diskFirmwareRevisionLabel, "cell 0 4,alignx leading");
        diskDrivePanel.add(diskFirmwareRevisionTextField, "cell 1 4,growx");

        diskDrivePanel.add(diskInterfaceTypeLabel, "cell 0 5,alignx leading");
        diskDrivePanel.add(diskInterfaceTypeTextField, "cell 1 5,growx");

        diskDrivePanel.add(diskSerialNumberLabel, "cell 0 6,alignx leading");
        diskDrivePanel.add(diskSerialNumberTextField, "cell 1 6,growx");

        diskDrivePanel.add(diskSizeLabel, "cell 0 7,alignx leading");
        diskDrivePanel.add(diskSizeTextField, "cell 1 7,growx");

        diskDrivePanel.add(diskPartitionsLabel, "cell 0 8,alignx leading");
        diskDrivePanel.add(diskPartitionsTextField, "cell 1 8,growx");

        diskDrivePanel.add(diskCapabilitiesLabel, "cell 0 9,alignx leading");
        diskDrivePanel.add(diskCapabilitiesTextField, "cell 1 9,growx");

        diskDrivePanel.add(diskStatusLabel, "cell 0 10,alignx leading");
        diskDrivePanel.add(diskStatusTextField, "cell 1 10,growx");

        diskPnpDeviceIdTextField.setEditable(false);
        diskCaptionTextField.setEditable(false);
        diskModelTextField.setEditable(false);
        diskFirmwareRevisionTextField.setEditable(false);
        diskInterfaceTypeTextField.setEditable(false);
        diskSerialNumberTextField.setEditable(false);
        diskSizeTextField.setEditable(false);
        diskPartitionsTextField.setEditable(false);
        diskCapabilitiesTextField.setEditable(false);
        diskStatusTextField.setEditable(false);

        return diskDrivePanel;
    }

    private JTabbedPane createDiskPartitionAndVolumeTabbedPane() {

        final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);

        diskPartitionEditorPane.setContentType("text/html");
        diskPartitionEditorPane.setEditable(false);

        diskVolumeEditorPane.setContentType("text/html");
        diskVolumeEditorPane.setEditable(false);

        // add editor panes to the tabbed pane
        tabbedPane.addTab(
                "Disk Partition",
                new FlatSVGIcon(WMIStoragePanel.class.getResource("/icons/tab_icons_material_green/DiskPartition.svg")),
                new JScrollPane(diskPartitionEditorPane),
                null
        );

        tabbedPane.addTab(
                "Disk Volume",
                new FlatSVGIcon(WMIStoragePanel.class.getResource("/icons/tab_icons_material_green/LogicalVolume.svg")),
                new JScrollPane(diskVolumeEditorPane),
                null
        );

        return tabbedPane;
    }

    public WMIStoragePanel initWorkers() {

        List<JTextField> storageFields = List.of(diskPnpDeviceIdTextField, diskCaptionTextField, diskModelTextField,
                diskFirmwareRevisionTextField, diskInterfaceTypeTextField, diskSerialNumberTextField, diskSizeTextField,
                diskPartitionsTextField, diskCapabilitiesTextField, diskStatusTextField);

        List<JEditorPane> editorPanes = List.of(diskPartitionEditorPane, diskVolumeEditorPane);

        new WMIStorageWorker(diskDeviceIdComboBox, storageFields, editorPanes).execute();

        return this;
    }

}
