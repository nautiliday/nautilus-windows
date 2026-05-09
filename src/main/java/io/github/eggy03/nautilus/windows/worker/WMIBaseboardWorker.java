/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.mainboard.Win32Baseboard;
import io.github.eggy03.cimari.service.mainboard.Win32BaseboardService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
public class WMIBaseboardWorker extends SwingWorker<Map<Integer, Win32Baseboard>, Void> {

    @NonNull
    private final JComboBox<Integer> baseboardNumberComboBox;
    @NonNull
    private final List<JTextField> baseboardFields;

    @Override
    protected @NonNull Map<Integer, Win32Baseboard> doInBackground() {
        // cimari returns a list of Win32Baseboard objects, so we need to index them
        List<Win32Baseboard> baseboardList = new Win32BaseboardService()
                .get(TerminalConstant.TIMEOUT_SIXTY_SECONDS)
                .stream()
                .filter(Objects::nonNull) // although cimari is designed to return never null objects in the list, this is just defensive
                .toList();

        log.info("Found {} Win32_Baseboard entry(s)", baseboardList.size());

        // note that an integer stream is used here only because there is no valid identifier to separate Win32Baseboard
        // instances
        return IntStream.range(0, baseboardList.size())
                .boxed()
                .collect(Collectors.toUnmodifiableMap(index -> index + 1, baseboardList::get));
    }

    @Override
    protected void done() {
        try {
            Map<Integer, Win32Baseboard> baseboardMap = get();
            // populate the combo box with the baseboardMap keys
            baseboardMap.keySet().stream().sorted().forEach(baseboardNumberComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(baseboardMap);
            // add a listener to the combo box to re-populate fields on new selection
            baseboardNumberComboBox.addActionListener(selectEvent -> populateFields(baseboardMap));
        } catch (InterruptedException e) {
            log.error("Baseboard Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("Baseboard Fetch Failed", e);
        }
    }

    private void populateFields(@NonNull Map<Integer, Win32Baseboard> baseboardMap) {

        Integer selectedBaseboardChoice = (Integer) baseboardNumberComboBox.getSelectedItem();
        Win32Baseboard baseboard = baseboardMap.get(selectedBaseboardChoice);
        if (baseboard == null) return;

        baseboardFields.get(0).setText(baseboard.manufacturer());
        baseboardFields.get(1).setText(baseboard.model());
        baseboardFields.get(2).setText(baseboard.product());
        baseboardFields.get(3).setText(baseboard.serialNumber());
        baseboardFields.get(4).setText(baseboard.version());

    }

}
