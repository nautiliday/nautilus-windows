/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.compounded.Win32DiskDriveToPartitionAndLogicalDisk;
import io.github.eggy03.cimari.entity.storage.Win32DiskDrive;
import io.github.eggy03.cimari.entity.storage.Win32DiskPartition;
import io.github.eggy03.cimari.entity.storage.Win32LogicalDisk;
import io.github.eggy03.cimari.service.compounded.Win32DiskDriveToPartitionAndLogicalDiskService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIValueResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIBooleanResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMISizeResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class WMIStorageWorker extends SwingWorker<Map<String, Win32DiskDriveToPartitionAndLogicalDisk>, Void> {

    private final JComboBox<String> diskIdComboBox;
    private final List<JTextField> diskFields;
    private final List<JEditorPane> diskEditorPanes;

    @Override
    protected Map<String, Win32DiskDriveToPartitionAndLogicalDisk> doInBackground() {
        List<Win32DiskDriveToPartitionAndLogicalDisk> diskList = new Win32DiskDriveToPartitionAndLogicalDiskService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32DiskDrive entry/entries", diskList.size());

        return diskList.stream()
                .filter(Objects::nonNull)
                .filter(disk -> Objects.nonNull(disk.deviceId()))
                .collect(Collectors.toUnmodifiableMap(
                        disk -> Objects.requireNonNull(disk.deviceId()).replace("\\\\.\\", ""), // the device IDs have backslashes
                        disk -> disk
                ));
    }

    @Override
    protected void done() {
        try {
            Map<String, Win32DiskDriveToPartitionAndLogicalDisk> diskMap = get();
            // populate the combo box with disk deviceIDs
            diskMap.keySet().stream().sorted().forEach(diskIdComboBox::addItem);
            // populate fields and editor panes for the first entry in the combo box
            populate(diskMap);
            // add a listener to the combo box to re-populate fields on new selection
            diskIdComboBox.addActionListener(selectAction -> populate(diskMap));

        } catch (InterruptedException e) {
            log.error("Disk Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("Disk Fetch Failed", e);
        }
    }

    private void populate(Map<String, Win32DiskDriveToPartitionAndLogicalDisk> diskMap) {

        String selectedDiskId = String.valueOf(diskIdComboBox.getSelectedItem());

        Win32DiskDriveToPartitionAndLogicalDisk disk = diskMap.get(selectedDiskId);
        if (disk == null) return;

        Win32DiskDrive diskDrive = disk.diskDrive();
        List<Win32DiskPartition> diskPartitionList = disk.diskPartitionList();
        List<Win32LogicalDisk> logicalDiskList = disk.logicalDiskList();

        if (diskDrive != null) {
            diskFields.get(0).setText(diskDrive.pnpDeviceId());
            diskFields.get(1).setText(diskDrive.caption());
            diskFields.get(2).setText(diskDrive.model());
            diskFields.get(3).setText(diskDrive.firmwareRevision());
            diskFields.get(4).setText(diskDrive.interfaceType());
            diskFields.get(5).setText(String.valueOf(diskDrive.serialNumber()).trim());
            diskFields.get(6).setText(WMISizeResolver.toGBString(diskDrive.size()));
            diskFields.get(7).setText(String.valueOf(diskDrive.partitions()));
            diskFields.get(8).setText(diskDrive.capabilityDescriptions() == null ? "N/A" : diskDrive.capabilityDescriptions().toString());
            diskFields.get(9).setText(diskDrive.status());
        }

        JEditorPane partitionPane = diskEditorPanes.get(0);
        JEditorPane volumePane = diskEditorPanes.get(1);
        // reset their contents on every invoke of this function
        partitionPane.setText(null);
        volumePane.setText(null);

        if (diskPartitionList != null && !diskPartitionList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body>");

            diskPartitionList.forEach(partition -> stringBuilder
                    .append("<b>Device ID:</b> ").append(partition.deviceId()).append("<br>")
                    .append("<b>Name:</b> ").append(partition.name()).append("<br>")
                    .append("<b>Description:</b> ").append(partition.description()).append("<br>")
                    .append("<b>Disk Index:</b> ").append(partition.diskIndex()).append("<br>")
                    .append("<b>Type:</b> ").append(partition.type()).append("<br>")
                    .append("<b>Bootable:</b> ").append(WMIBooleanResolver.resolveBoolean(partition.bootable())).append("<br>")
                    .append("<b>Primary Partition:</b> ").append(WMIBooleanResolver.resolveBoolean(partition.primaryPartition())).append("<br>")
                    .append("<b>Boot Partition:</b> ").append(WMIBooleanResolver.resolveBoolean(partition.bootPartition())).append("<br>")
                    .append("<b>Block Size (bytes):</b> ").append(partition.blockSize()).append("<br>")
                    .append("<b>Number of Blocks:</b> ").append(partition.numberOfBlocks()).append("<br>")
                    .append("<b>Total Size:</b> ").append(WMISizeResolver.toGBString(partition.size()))
                    .append("<br><br>")
            );

            stringBuilder.append("</body></html>");
            partitionPane.setText(stringBuilder.toString());
        }

        if (logicalDiskList != null && !logicalDiskList.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><body>");

            logicalDiskList.forEach(logicalDisk -> stringBuilder
                    .append("<b>Device ID:</b> ").append(logicalDisk.deviceId()).append("<br>")
                    .append("<b>Volume Name:</b> ").append(logicalDisk.volumeName()).append("<br>")
                    .append("<b>Description:</b> ").append(logicalDisk.description()).append("<br>")
                    .append("<b>Drive Type:</b> ").append(WMIValueResolver.resolveWMILogicalDiskDriveType(logicalDisk.driveType())).append("<br>")
                    .append("<b>Media Type:</b> ").append(WMIValueResolver.resolveWMILogicalDiskMediaType(logicalDisk.mediaType())).append("<br>")
                    .append("<b>File System:</b> ").append(logicalDisk.fileSystem()).append("<br>")
                    .append("<b>Total Size:</b> ").append(WMISizeResolver.toGBString(logicalDisk.size())).append("<br>")
                    .append("<b>Free Space:</b> ").append(WMISizeResolver.toGBString(logicalDisk.freeSpace())).append("<br>")
                    .append("<b>Compressed:</b> ").append(WMIBooleanResolver.resolveBoolean(logicalDisk.compressed())).append("<br>")
                    .append("<b>Supports File Compression:</b> ").append(WMIBooleanResolver.resolveBoolean(logicalDisk.supportsFileBasedCompression())).append("<br>")
                    .append("<b>Supports Disk Quotas:</b> ").append(WMIBooleanResolver.resolveBoolean(logicalDisk.supportsDiskQuotas())).append("<br>")
                    .append("<b>Volume Serial Number:</b> ").append(logicalDisk.volumeSerialNumber())
                    .append("<br><br>")
            );

            stringBuilder.append("</body></html>");
            volumePane.setText(stringBuilder.toString());
        }

    }
}
