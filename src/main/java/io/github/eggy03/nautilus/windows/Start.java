/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows;

import io.github.eggy03.nautilus.windows.ui.primary.MainUI;
import io.github.eggy03.nautilus.windows.ui.secondary.ExceptionUI;
import io.github.eggy03.nautilus.windows.utility.GlobalUIConfiguration;
import io.github.eggy03.nautilus.windows.utility.OSDetection;
import lombok.extern.slf4j.Slf4j;

import java.awt.EventQueue;

@Slf4j
public class Start {

    /**
     * Launch the application.
     */
    @SuppressWarnings("java:S1192")
    public static void main(String[] args) {

        log.info("Detected OS: {}", OSDetection.getCurrentOS());

        new GlobalUIConfiguration().applyFlatLafLookAndFeel(
                "io.github.eggy03.nautilus.windows.ui.themes.StandardDarkTheme",
                "themes"
        ).enableRoundComponents().enableTabSeparators(true);

        EventQueue.invokeLater(Start::launchUIBasedOnOS);
    }

    private static void launchUIBasedOnOS() {
        if (OSDetection.isWindows()) {
            new MainUI().initUI().initComponents().setVisible(true);
        } else {
            new ExceptionUI("Unsupported OS", OSDetection.getCurrentOS() + " is not supported");
        }
    }
}
