/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.utility;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;
import org.jspecify.annotations.Nullable;

/**
 * Utility class for detecting the current operating system.
 *
 */
@UtilityClass
public class OSDetection {

    /**
     * Determines whether the current operating system is
     * a supported Windows platform.
     *
     * <p>
     * The following Windows versions are recognized:
     * </p>
     *
     * <ul>
     *     <li>Windows 7</li>
     *     <li>Windows 8</li>
     *     <li>Windows 10</li>
     *     <li>Windows 11</li>
     * </ul>
     *
     * @return {@code true} if the current operating system
     * is a supported Windows version, otherwise {@code false}
     */
    public static boolean isWindows() {

        if (SystemUtils.IS_OS_WINDOWS) {
            return SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8 ||
                    SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11;
        }
        return false;
    }

    /**
     * Returns the operating system name reported by the JVM.
     *
     * <p>
     * This value is retrieved from:
     * </p>
     *
     * <pre>
     * System.getProperty("os.name")
     * </pre>
     *
     * @return the current operating system name
     */
    @Nullable
    public static String getCurrentOS() {
        return System.getProperty("os.name");
    }

}
