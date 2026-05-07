/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.typeresolver;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Utility class for resolving and formatting WMI byte-based size values
 * into human-readable storage units.
 */
@UtilityClass
public class WMISizeResolver {

    /**
     * Number of bytes in one megabyte using binary conversion.
     */
    private static final BigDecimal BYTES_PER_MB = BigDecimal.valueOf(1024L * 1024L);

    /**
     * Number of bytes in one gigabyte using binary conversion.
     */
    private static final BigDecimal BYTES_PER_GB = BigDecimal.valueOf(1024L * 1024L * 1024L);

    /**
     * Rounding mode used for decimal conversions.
     */
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * Number of decimal places used in formatted output.
     */
    private static final int SCALE = 2;

    /**
     * Converts a byte value into a formatted megabyte (MB) string.
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * 1048576 -> 1.00 MB
     * </pre>
     *
     * @param bytes the size value in bytes
     * @return the formatted size in MB,
     *         or {@code "N/A"} if the value is null
     */
    @NotNull
    public static String toMBString (Long bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_MB, SCALE, ROUNDING_MODE);
        return decimal+" MB";
    }

    /**
     * Converts a byte value into a formatted megabyte (MB) string.
     *
     * <p>
     * Supports large values using {@link BigInteger}.
     * </p>
     *
     * @param bytes the size value in bytes
     * @return the formatted size in MB,
     *         or {@code "N/A"} if the value is null
     */
    @NotNull
    public static String toMBString(BigInteger bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_MB, SCALE, ROUNDING_MODE);
        return decimal+" MB";
    }

    /**
     * Converts a byte value into a formatted gigabyte (GB) string.
     *
     * <p>
     * Example:
     * </p>
     *
     * <pre>
     * 1073741824 -> 1.00 GB
     * </pre>
     *
     * @param bytes the size value in bytes
     * @return the formatted size in GB,
     *         or {@code "N/A"} if the value is null
     */
    @NotNull
    public static String toGBString(Long bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_GB, SCALE, ROUNDING_MODE);
        return decimal+" GB";
    }

    /**
     * Converts a byte value into a formatted gigabyte (GB) string.
     *
     * <p>
     * Supports large values using {@link BigInteger}.
     * </p>
     *
     * @param bytes the size value in bytes
     * @return the formatted size in GB,
     *         or {@code "N/A"} if the value is null
     */
    @NotNull
    public static String toGBString(BigInteger bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_GB, SCALE, ROUNDING_MODE);
        return decimal+" GB";
    }
}
