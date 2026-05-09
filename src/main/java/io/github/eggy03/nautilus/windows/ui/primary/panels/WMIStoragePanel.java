/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import io.github.eggy03.nautilus.windows.worker.WMIStorageWorker;

import net.miginfocom.swing.MigLayout;
import org.jspecify.annotations.NonNull;

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

    private final @NonNull JLabel diskDeviceIdLabel = new JLabel("Disk#");
    private final @NonNull JComboBox<String> diskDeviceIdComboBox = new JComboBox<>();

    private final @NonNull JLabel diskPnpDeviceIdLabel = new JLabel("PnP DeviceID");
    private final @NonNull JTextField diskPnpDeviceIdTextField = new JTextField();

    private final @NonNull JLabel diskCaptionLabel = new JLabel("Name");
    private final @NonNull JTextField diskCaptionTextField = new JTextField();

    private final @NonNull JLabel diskModelLabel = new JLabel("Model");
    private final @NonNull JTextField diskModelTextField = new JTextField();

    private final @NonNull JLabel diskFirmwareRevisionLabel = new JLabel("Firmware");
    private final @NonNull JTextField diskFirmwareRevisionTextField = new JTextField();

    private final @NonNull JLabel diskInterfaceTypeLabel = new JLabel("Interface");
    private final @NonNull JTextField diskInterfaceTypeTextField = new JTextField();

    private final @NonNull JLabel diskSerialNumberLabel = new JLabel("Serial Number");
    private final @NonNull JTextField diskSerialNumberTextField = new JTextField();

    private final @NonNull JLabel diskSizeLabel = new JLabel("Size");
    private final @NonNull JTextField diskSizeTextField = new JTextField();

    private final @NonNull JLabel diskPartitionsLabel = new JLabel("Partitions");
    private final @NonNull JTextField diskPartitionsTextField = new JTextField();

    private final @NonNull JLabel diskCapabilitiesLabel = new JLabel("Capabilities");
    private final @NonNull JTextField diskCapabilitiesTextField = new JTextField();

    private final @NonNull JLabel diskStatusLabel = new JLabel("Status");
    private final @NonNull JTextField diskStatusTextField = new JTextField();

    private final @NonNull JEditorPane diskPartitionEditorPane = new JEditorPane();
    private final @NonNull JEditorPane diskVolumeEditorPane = new JEditorPane();

    public @NonNull WMIStoragePanel initUI() {
        setLayout(new GridLayout(2, 1, 0, 0));

        return this;
    }

    public @NonNull WMIStoragePanel initComponents() {

        add(new JScrollPane(createDiskDrivePanel()));
        add(createDiskPartitionAndVolumeTabbedPane());

        return this;
    }

    private @NonNull JPanel createDiskDrivePanel() {

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

    private @NonNull JTabbedPane createDiskPartitionAndVolumeTabbedPane() {

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

    public @NonNull WMIStoragePanel initWorkers() {

        List<JTextField> storageFields = List.of(diskPnpDeviceIdTextField, diskCaptionTextField, diskModelTextField,
                diskFirmwareRevisionTextField, diskInterfaceTypeTextField, diskSerialNumberTextField, diskSizeTextField,
                diskPartitionsTextField, diskCapabilitiesTextField, diskStatusTextField);

        List<JEditorPane> editorPanes = List.of(diskPartitionEditorPane, diskVolumeEditorPane);

        new WMIStorageWorker(diskDeviceIdComboBox, storageFields, editorPanes).execute();

        return this;
    }

}
