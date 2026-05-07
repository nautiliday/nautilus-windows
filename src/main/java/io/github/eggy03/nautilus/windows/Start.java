/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows;

import com.formdev.flatlaf.FlatLaf;
import io.github.eggy03.nautilus.windows.constant.OSConstants;
import io.github.eggy03.nautilus.windows.ui.secondary.ExceptionUI;
import io.github.eggy03.nautilus.windows.utility.UIManagerConfigurations;
import io.github.eggy03.nautilus.windows.ui.primary.MainUI;
import io.github.eggy03.theme.manager.ThemeManager;
import lombok.extern.slf4j.Slf4j;

import java.awt.EventQueue;
import java.util.Objects;

@Slf4j
public class Start {

    /**
     * Launch the application.
     */
    @SuppressWarnings("java:S1192")
    public static void main(String[] args) {

        // for native builds, 'java.home' stays null
        // in such cases, we need to provide a custom path cause our codebase depends on java.home usage
        String defaultJavaHome = System.getProperty("java.home");
        if (defaultJavaHome == null || defaultJavaHome.isBlank()) {
            if (args.length > 0 && !args[0].isBlank())
                System.setProperty("java.home", args[0]);
            else
                throw new IllegalStateException("java.home must be provided via arg or JAVA_HOME");
        }
        log.info("java.home found at: {}", System.getProperty("java.home"));


        log.info("Detected OS: {}", OSConstants.getCurrentOS());
        FlatLaf.registerCustomDefaultsSource("themes"); // for maven build, this points towards src/main/resources/themes

        EventQueue.invokeLater(()-> {
            UIManagerConfigurations.enableRoundComponents();
            UIManagerConfigurations.enableTabSeparators(true);
            
            launchUIBasedOnOS();
        });
    }

    private static void launchUIBasedOnOS() {
        if (Objects.requireNonNull(OSConstants.detectOs()) == OSConstants.WINDOWS) {
            new MainUI().setVisible(true);
        } else {
            new ExceptionUI("Unsupported OS", OSConstants.getCurrentOS() + " is not supported");
        }
    }
}
