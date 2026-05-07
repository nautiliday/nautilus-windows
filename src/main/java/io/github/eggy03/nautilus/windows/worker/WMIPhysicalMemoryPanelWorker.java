/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.memory.Win32PhysicalMemory;
import io.github.eggy03.cimari.service.memory.Win32PhysicalMemoryService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.ui.primary.utilities.WMISizeUtility;
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

import static io.github.eggy03.nautilus.windows.ui.primary.constant.WMIConstants.resolveWMIPhysicalMemoryFormFactor;

@RequiredArgsConstructor
@Slf4j
public class WMIPhysicalMemoryPanelWorker extends SwingWorker<Map<String, Win32PhysicalMemory>, Void> {

    private final JComboBox<String> memoryTagComboBox;
    private final List<JTextField> memoryFields;

    @Override
    protected Map<String, Win32PhysicalMemory> doInBackground() {

        List<Win32PhysicalMemory> memoryList = new Win32PhysicalMemoryService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32_PhysicalMemory entry(s)", memoryList.size());

        return memoryList.stream()
                .filter(Objects::nonNull)
                .filter(mem -> Objects.nonNull(mem.tag()))
                .collect(Collectors.toUnmodifiableMap(
                        mem -> Objects.requireNonNull(mem.tag()),
                        mem -> mem
                ));
    }

    @Override
    protected void done() {
        try {
            Map<String, Win32PhysicalMemory> memoryMap = get();
            // fill the combo box with memory tags
            memoryMap.keySet().stream().sorted().forEach(memoryTagComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateMemoryFields(memoryMap);
            // add a listener to the combo box to re-populate fields on new selection
            memoryTagComboBox.addActionListener(selectEvent -> populateMemoryFields(memoryMap));

        } catch (ExecutionException e) {
            log.error("Memory Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("Memory Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }


    private void populateMemoryFields(Map<String, Win32PhysicalMemory> memoryMap) {

        // get the current selected tag in the combo box
        String memoryTag = String.valueOf(memoryTagComboBox.getSelectedItem());

        Win32PhysicalMemory memory = memoryMap.get(memoryTag);
        if (memory == null) return;

        // populate the fields
        memoryFields.get(0).setText(memory.name());
        memoryFields.get(1).setText(memory.manufacturer());
        memoryFields.get(2).setText(memory.model());
        memoryFields.get(3).setText(memory.otherIdentifyingInfo());
        memoryFields.get(4).setText(memory.partNumber());
        memoryFields.get(5).setText(memory.serialNumber());
        memoryFields.get(6).setText(resolveWMIPhysicalMemoryFormFactor(memory.formFactor()));
        memoryFields.get(7).setText(memory.bankLabel());
        memoryFields.get(8).setText(WMISizeUtility.parseToGBString(memory.capacity()));
        memoryFields.get(9).setText(memory.dataWidth() + " Bits");
        memoryFields.get(10).setText(memory.speed() + " MHz");
        memoryFields.get(11).setText(memory.configuredClockSpeed() + " MHz");
        memoryFields.get(12).setText(memory.deviceLocator());

    }
}
