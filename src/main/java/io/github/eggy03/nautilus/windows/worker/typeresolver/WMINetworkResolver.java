/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.typeresolver;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for resolving and formatting WMI network-related values.
 */
@UtilityClass
public class WMINetworkResolver {

    /**
     * Converts a raw network speed value from bits per second into
     * megabits per second (Mbps).
     *
     * <p>
     * The conversion uses decimal megabits:
     * </p>
     *
     * <pre>
     * 1 Mbps = 1,000,000 bits per second
     * </pre>
     *
     * <p>
     * The resulting value is rounded to two decimal places using
     * {@link RoundingMode#HALF_UP}.
     * </p>
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * 1000000000 -> 1000.00 Mbps
     * </pre>
     *
     * @param speedInBitsPerSecond the raw network speed in bits per second
     * @return the formatted speed in Mbps,
     * or {@code "N/A"} if the value is null
     */
    @NotNull
    public static String resolveNetworkSpeedInMbps(@Nullable Long speedInBitsPerSecond) {
        if (speedInBitsPerSecond == null)
            return "N/A";

        // Decimal conversion:
        // 1 Mbps = 1,000,000 bits per second
        BigDecimal mbpsDivisor = new BigDecimal(1_000_000L); // to Mbps
        BigDecimal speed = BigDecimal.valueOf(speedInBitsPerSecond);
        return speed.divide(mbpsDivisor, 2, RoundingMode.HALF_UP) + " Mbps";
    }
}
