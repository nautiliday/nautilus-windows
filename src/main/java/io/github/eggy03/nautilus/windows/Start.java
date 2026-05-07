/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows;

import com.formdev.flatlaf.FlatLaf;
import io.github.eggy03.nautilus.windows.ui.primary.MainUI;
import io.github.eggy03.nautilus.windows.ui.secondary.ExceptionUI;
import io.github.eggy03.nautilus.windows.utility.OSDetection;
import io.github.eggy03.nautilus.windows.utility.UIManagerConfigurations;
import lombok.extern.slf4j.Slf4j;

import java.awt.EventQueue;

@Slf4j
public class Start {

    /**
     * Launch the application.
     */
    @SuppressWarnings("java:S1192")
    public static void main(String[] args) {

        log.info("java.home found at: {}", System.getProperty("java.home"));


        log.info("Detected OS: {}", OSDetection.getCurrentOS());
        FlatLaf.registerCustomDefaultsSource("themes"); // for maven build, this points towards src/main/resources/themes

        EventQueue.invokeLater(() -> {
            UIManagerConfigurations.enableRoundComponents();
            UIManagerConfigurations.enableTabSeparators(true);

            launchUIBasedOnOS();
        });
    }

    private static void launchUIBasedOnOS() {
        if (OSDetection.isWindows()) {
            new MainUI().setVisible(true);
        } else {
            new ExceptionUI("Unsupported OS", OSDetection.getCurrentOS() + " is not supported");
        }
    }
}
