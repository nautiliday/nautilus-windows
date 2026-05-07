/*
 * SPDX-License-Identifier: GPL-3.0-or-later
 * Copyright (C) 2026 Egg-03
 */
package io.github.eggy03.nautilus.windows.worker.typeresolver;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Utility class for resolving nullable boolean WMI values into
 * human-readable string representations.
 *
 */
@UtilityClass
public class WMIBooleanResolver {

    /**
     * Resolves a nullable boolean value into a human-readable string.
     *
     * <p>
     * The returned value will be:
     * </p>
     *
     * <ul>
     *     <li>{@code "Yes"} for {@code true}</li>
     *     <li>{@code "No"} for {@code false}</li>
     *     <li>{@code "N/A"} for {@code null}</li>
     * </ul>
     *
     * @param value the boolean value to resolve
     * @return the resolved string representation of the boolean value
     */
    @NotNull
    public static String resolveBoolean(@Nullable Boolean value) {
        if (value == null)
            return "N/A";

        if (value)
            return "Yes";
        else return "No";
    }
}
