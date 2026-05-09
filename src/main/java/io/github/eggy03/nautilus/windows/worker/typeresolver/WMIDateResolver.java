/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.typeresolver;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Utility class for resolving and formatting WMI date values into
 * human-readable local date-time strings.
 */
@UtilityClass
public class WMIDateResolver {

    /**
     * Converts a WMI epoch-based date string into a formatted local
     * date-time representation.
     *
     * <p>
     * Non-numeric characters are stripped before parsing the epoch value.
     * </p>
     *
     * @param epochDate the WMI date string to resolve
     * @return the formatted local date-time string,
     * or {@code "N/A"} if the input is null or blank
     * @throws NumberFormatException if the extracted epoch value
     *                               cannot be parsed as a valid long
     */
    @NonNull
    public static String toLocalDateTime(@Nullable String epochDate) {

        if (epochDate == null || epochDate.isBlank())
            return "N/A";

        // some WMI date values are like this: "/Date(1703462400000)/" so stripping is required
        long millis = Long.parseLong(epochDate.replaceAll("\\D", ""));

        return DateFormatUtils.format(millis, "yyyy-MM-dd HH:mm:ss Z");
    }
}
