/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.utility;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.SystemUtils;

@UtilityClass
public class OSDetection {

    public static boolean isWindows() {

        if (SystemUtils.IS_OS_WINDOWS) {
            return SystemUtils.IS_OS_WINDOWS_7 || SystemUtils.IS_OS_WINDOWS_8 ||
                    SystemUtils.IS_OS_WINDOWS_10 || SystemUtils.IS_OS_WINDOWS_11;
        }
        return false;
    }

    public static String getCurrentOS() {
        return System.getProperty("os.name");
    }

}
