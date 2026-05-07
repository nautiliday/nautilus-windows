/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.windows.utilities;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.time.DateFormatUtils;

@UtilityClass
public class WMIDateUtility {

    public static String toLocalDateTime (String epochDate) {

        if(epochDate==null || epochDate.isBlank())
            return "N/A";

        // some WMI date values are like this: "/Date(1703462400000)/" so stripping is required
        long millis = Long.parseLong(epochDate.replaceAll("\\D", ""));

        return DateFormatUtils.format(millis, "yyyy-MM-dd HH:mm:ss Z");
    }
}
