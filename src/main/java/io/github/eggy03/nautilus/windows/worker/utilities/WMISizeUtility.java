/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.utilities;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@UtilityClass
public class WMISizeUtility {

    private static final BigDecimal BYTES_PER_MB = BigDecimal.valueOf(1024L * 1024L);
    private static final BigDecimal BYTES_PER_GB = BigDecimal.valueOf(1024L * 1024L * 1024L);

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final int SCALE = 2;

    @NotNull
    public static String parseToMBString (Long bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_MB, SCALE, ROUNDING_MODE);
        return decimal+" MB";
    }

    @NotNull
    public static String parseToMBString(BigInteger bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_MB, SCALE, ROUNDING_MODE);
        return decimal+" MB";
    }

    @NotNull
    public static String parseToGBString(Long bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_GB, SCALE, ROUNDING_MODE);
        return decimal+" GB";
    }

    @NotNull
    public static String parseToGBString(BigInteger bytes) {
        if(bytes==null)
            return "N/A";

        BigDecimal decimal = new BigDecimal(bytes).divide(BYTES_PER_GB, SCALE, ROUNDING_MODE);
        return decimal+" GB";
    }
}
