/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import static org.junit.jupiter.api.Assertions.*;

import org.parts3492.partslib.PARTsUnit.PARTsUnitType;

import org.junit.jupiter.api.Test;

class PARTsUnitTest {
    @Test
    public void testConvertInchesToMeters() {
        PARTsUnit unit = new PARTsUnit(39.37008, PARTsUnitType.Inch);
        assertEquals(1.0, Math.floor(unit.to(PARTsUnitType.Meter)));
    }

    @Test
    public void testConvertMetersToInches() {
        PARTsUnit unit = new PARTsUnit(1, PARTsUnitType.Meter);
        assertEquals(39.37008, unit.to(PARTsUnitType.Inch), 1e-5);
    }
}
