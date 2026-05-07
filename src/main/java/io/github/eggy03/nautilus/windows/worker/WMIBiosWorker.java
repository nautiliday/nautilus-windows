/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.mainboard.Win32Bios;
import io.github.eggy03.cimari.service.mainboard.Win32BiosService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIDateResolver;
import lombok.NonNull;
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
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
public class WMIBiosWorker extends SwingWorker<Map<Integer, Win32Bios>, Void> {

    @NonNull
    private final JComboBox<Integer> biosNumberComboBox;
    @NonNull
    private final List<JTextField> biosFields;

    @Override
    protected Map<Integer, Win32Bios> doInBackground() {

        List<Win32Bios> biosList = new Win32BiosService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS)
                .stream()
                .filter(Objects::nonNull)
                .toList();

        log.info("Found {} Win32_BIOS entry(s)", biosList.size());

        return IntStream.range(0, biosList.size())
                .boxed()
                .collect(Collectors.toUnmodifiableMap(index -> index + 1, biosList::get));

    }

    @Override
    protected void done() {

        try {
            Map<Integer, Win32Bios> biosMap = get();
            // populate the combo box
            biosMap.keySet().stream().sorted().forEach(biosNumberComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateBiosFields(biosMap);
            // add a listener to the combo box to re-populate fields on new selection
            biosNumberComboBox.addActionListener(selectEvent -> populateBiosFields(biosMap));
        } catch (InterruptedException e) {
            log.error("BIOS Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("BIOS Fetch Failed", e);
        }

    }

    private void populateBiosFields(Map<Integer, Win32Bios> biosMap) {

        Integer selectedBiosNumber = (Integer) biosNumberComboBox.getSelectedItem();

        Win32Bios bios = biosMap.get(selectedBiosNumber);
        if (bios == null) return;

        biosFields.get(0).setText(bios.caption());
        biosFields.get(1).setText(bios.currentLanguage());
        biosFields.get(2).setText(bios.manufacturer());
        biosFields.get(3).setText(bios.name());

        biosFields.get(5).setText(WMIDateResolver.toLocalDateTime(bios.releaseDate()));

        biosFields.get(7).setText(bios.smbiosBiosVersion());
        biosFields.get(8).setText(bios.status());
        biosFields.get(9).setText(bios.version());

        Boolean primaryBios = bios.primaryBios();
        if (primaryBios != null) {
            if (primaryBios) biosFields.get(4).setText("Yes");
            else biosFields.get(4).setText("No");
        } else biosFields.get(4).setText("N/A");

        Boolean smbiosPresent = bios.smbiosPresent();
        if (smbiosPresent != null) {
            if (smbiosPresent) biosFields.get(6).setText("Yes");
            else biosFields.get(6).setText("No");
        } else biosFields.get(6).setText("N/A");
    }
}
