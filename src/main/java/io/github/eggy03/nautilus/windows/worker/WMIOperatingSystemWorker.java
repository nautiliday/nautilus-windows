/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.system.Win32OperatingSystem;
import io.github.eggy03.cimari.service.system.Win32OperatingSystemService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIBooleanResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIDateResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class WMIOperatingSystemWorker extends SwingWorker<Map<String, Win32OperatingSystem>, Void> {

    private final JComboBox<String> osNameComboBox;
    private final List<JTextField> osFields;
    private final JTextArea osConciseInfoArea;

    @Override
    protected Map<String, Win32OperatingSystem> doInBackground() {

        List<Win32OperatingSystem> osList = new Win32OperatingSystemService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32_OperatingSystem entry(s)", osList.size());

        return osList.stream()
                .filter(Objects::nonNull)
                .filter(os -> Objects.nonNull(os.name()))
                .collect(Collectors.toUnmodifiableMap(
                        os -> Objects.requireNonNull(os.name()),
                        os -> os
                ));
    }

    @Override
    protected void done() {
        try {
            Map<String, Win32OperatingSystem> osMap = get();
            // fill the combo box with os names
            osMap.keySet().stream().sorted().forEach(osNameComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(osMap);
            // add a listener to the combo box to re-populate fields on new selection
            osNameComboBox.addActionListener(selectEvent -> populateFields(osMap));

        } catch (InterruptedException e) {
            log.error("OS Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("OS Fetch Failed", e);
        }
    }

    private void populateFields(Map<String, Win32OperatingSystem> osMap) {

        String osName = String.valueOf(osNameComboBox.getSelectedItem());

        Win32OperatingSystem os = osMap.get(osName);
        if (os == null) return;

        osFields.get(0).setText(os.caption());
        osFields.get(1).setText(os.version());
        osFields.get(2).setText(os.buildNumber());
        osFields.get(3).setText(os.manufacturer());
        osFields.get(4).setText(os.osArchitecture());
        osFields.get(5).setText(WMIDateResolver.toLocalDateTime(os.installDate()));
        osFields.get(6).setText(WMIDateResolver.toLocalDateTime(os.lastBootUpTime()));
        osFields.get(7).setText(os.serialNumber());
        osFields.get(8).setText(os.muiLanguages() == null ? "N/A" : os.muiLanguages().toString());
        osFields.get(9).setText(WMIBooleanResolver.resolveBoolean(os.primary()));
        osFields.get(10).setText(WMIBooleanResolver.resolveBoolean(os.distributed()));
        osFields.get(11).setText(WMIBooleanResolver.resolveBoolean(os.portableOperatingSystem()));
        osFields.get(12).setText(os.csName());
        osFields.get(13).setText(String.valueOf(os.numberOfUsers()));
        osFields.get(14).setText(os.registeredUser());
        osFields.get(15).setText(os.systemDrive());
        osFields.get(16).setText(os.windowsDirectory());
        osFields.get(17).setText(os.systemDirectory());

        String conciseOsText = os.osArchitecture() + " edition of Windows" +
                System.lineSeparator() +
                "Version: " + os.version() +
                System.lineSeparator() +
                "Installed on: " + WMIDateResolver.toLocalDateTime(os.installDate()) +
                System.lineSeparator() +
                "Last started on: " + WMIDateResolver.toLocalDateTime(os.lastBootUpTime()) +
                System.lineSeparator() +
                "Registered to: " + os.registeredUser();

        osConciseInfoArea.setText(conciseOsText);
    }
}
