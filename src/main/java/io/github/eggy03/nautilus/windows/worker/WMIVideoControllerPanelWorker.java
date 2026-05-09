/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.display.Win32VideoController;
import io.github.eggy03.cimari.service.display.Win32VideoControllerService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMIDateResolver;
import io.github.eggy03.nautilus.windows.worker.typeresolver.WMISizeResolver;
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

@Slf4j
@RequiredArgsConstructor
public class WMIVideoControllerPanelWorker extends SwingWorker<Map<String, Win32VideoController>, Void> {

    private final @NonNull JComboBox<String> gpuDeviceIdComboBox;
    private final @NonNull List<JTextField> gpuFields;

    @Override
    protected @NonNull Map<String, Win32VideoController> doInBackground() {

        List<Win32VideoController> videoList = new Win32VideoControllerService().get(TerminalConstant.TIMEOUT_SIXTY_SECONDS);
        log.info("Found {} Win32_VideoController entry(s)", videoList.size());

        return videoList.stream()
                .filter(Objects::nonNull)
                .filter(video -> Objects.nonNull(video.deviceId()))
                .collect(Collectors.toUnmodifiableMap(
                        video -> Objects.requireNonNull(video.deviceId()),
                        video -> video
                ));
    }

    @Override
    protected void done() {
        try {
            Map<String, Win32VideoController> videoMap = get();
            // fill video controller IDs in the combo box
            videoMap.keySet().stream().sorted().forEach(gpuDeviceIdComboBox::addItem);
            // populate fields for the first entry in the combo box
            populateFields(videoMap);
            // add a listener to the combo box to re-populate fields on new selection
            gpuDeviceIdComboBox.addActionListener(selectEvent -> populateFields(videoMap));

        } catch (InterruptedException e) {
            log.error("Video Controller Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            log.error("Video Controller Fetch Failed", e);
        }
    }

    private void populateFields(@NonNull Map<String, Win32VideoController> videoMap) {

        String gpuDeviceId = String.valueOf(gpuDeviceIdComboBox.getSelectedItem());

        Win32VideoController gpu = videoMap.get(gpuDeviceId);
        if (gpu == null) return;

        gpuFields.get(0).setText(gpu.name());
        gpuFields.get(1).setText(gpu.pnpDeviceId());
        gpuFields.get(2).setText(gpu.currentHorizontalResolution() + " px");
        gpuFields.get(3).setText(gpu.currentVerticalResolution() + " px");
        gpuFields.get(4).setText(gpu.currentBitsPerPixel() + " bit");
        gpuFields.get(5).setText(gpu.minRefreshRate() + " Hz");
        gpuFields.get(6).setText(gpu.maxRefreshRate() + " Hz");
        gpuFields.get(7).setText(gpu.currentRefreshRate() + " Hz");
        gpuFields.get(8).setText(gpu.adapterDacType());
        gpuFields.get(9).setText(WMISizeResolver.toGBString(gpu.adapterRam()));
        gpuFields.get(10).setText(gpu.driverVersion());
        gpuFields.get(11).setText(WMIDateResolver.toLocalDateTime(gpu.driverDate()));
        gpuFields.get(12).setText(gpu.videoProcessor());
    }
}
