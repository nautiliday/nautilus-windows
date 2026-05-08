/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker;

import io.github.eggy03.cimari.entity.compounded.HardwareId;
import io.github.eggy03.cimari.entity.compounded.ImmutableHardwareId;
import io.github.eggy03.cimari.service.compounded.HardwareIdService;
import io.github.eggy03.nautilus.windows.constant.TerminalConstant;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.JTextField;
import javax.swing.SwingWorker;
import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
public class WMIHardwareIdWorker extends SwingWorker<HardwareId, Void> {

    @NonNull
    private final JTextField hidField;

    @Override
    protected HardwareId doInBackground() {
        return new HardwareIdService()
                .get(TerminalConstant.TIMEOUT_SIXTY_SECONDS)
                .orElse(new ImmutableHardwareId.Builder().build());
    }

    @Override
    protected void done() {

        try {
            HardwareId hid = get();
            hidField.setText(hid.hashHWID());
        } catch (ExecutionException e) {
            log.error("HWID Fetch Failed", e);
        } catch (InterruptedException e) {
            log.error("HWID Fetch Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

}
