/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.utilities;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class WMIBooleanUtility {
    /**
     * Will resolve to Yes for True, No for False and N/A for null
     * @return the resolved boolean value in string
     */
    @NotNull
    public static String resolveBoolean(@Nullable Boolean value) {
        if(value==null)
            return "N/A";

        if(value)
            return "Yes";
        else return "No";
    }
}
