/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.ui.primary.utilities;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class WMINetworkUtility {

    /**
     * Parses raw speed in bits/s and returns Mbps
     * @param speedInBitsPerSecond raw speed in bits/s
     * @return parsed speed in Mbps
     */
    @NotNull
    public static String resolveNetworkSpeedInMbps(@Nullable Long speedInBitsPerSecond) {
        if(speedInBitsPerSecond==null)
            return "N/A";

        BigDecimal mbpsDivisor = new BigDecimal(1000L*1000L); // to Mbps
        BigDecimal speed = new BigDecimal(speedInBitsPerSecond);
        return speed.divide(mbpsDivisor, 2, RoundingMode.HALF_UP)+" Mbps";
    }
}
