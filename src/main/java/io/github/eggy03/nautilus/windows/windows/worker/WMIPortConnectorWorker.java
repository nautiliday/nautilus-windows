/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.windows.worker;

import io.github.eggy03.cimari.entity.mainboard.Win32PortConnector;
import io.github.eggy03.cimari.service.mainboard.Win32PortConnectorService;
import io.github.eggy03.nautilus.windows.common.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static io.github.eggy03.nautilus.windows.windows.constant.WMIConstants.resolveWMIPortType;

@Slf4j
@RequiredArgsConstructor
public class WMIPortConnectorWorker extends SwingWorker<Map<String, Win32PortConnector>, Void> {

    private final JComboBox<String> tagComboBox;
    private final List<JTextField> portFields;

    @Override
    protected Map<String, Win32PortConnector> doInBackground() {
        List<Win32PortConnector> portList = new Win32PortConnectorService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32_PortConnector entry(s)", portList.size());

        return portList.stream()
                .filter(Objects::nonNull)
                .filter(port -> Objects.nonNull(port.tag()))
                .collect(Collectors.toUnmodifiableMap(
                        port -> Objects.requireNonNull(port.tag()),
                        port -> port
                ));
    }

    @Override
    protected void done() {

        try {
            Map<String, Win32PortConnector> portMap = get();
            //fill the combo box with port connector tags
            portMap.keySet().stream().sorted().forEach(tagComboBox::addItem);
            // populate fields for the first entry in the combo box
            populatePortConnectorFields(portMap);
            // add a listener to the combo box to re-populate fields on new selection
            tagComboBox.addActionListener(selectEvent -> populatePortConnectorFields(portMap));

        } catch (InterruptedException e) {
            log.error("Baseboard Port Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("Baseboard Port Fetch Failed", e);
        }

    }

    private void populatePortConnectorFields(Map<String, Win32PortConnector> portMap) {

        String selectedTag = String.valueOf(tagComboBox.getSelectedItem());

        Win32PortConnector port = portMap.get(selectedTag);
        if (port == null) return;

        portFields.get(0).setText(resolveWMIPortType(port.portType()));
        portFields.get(1).setText(port.internalReferenceDesignator());
        portFields.get(2).setText(port.externalReferenceDesignator());
    }
}
