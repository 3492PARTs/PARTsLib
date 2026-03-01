package org.parts3492.partslib;

import org.junit.jupiter.api.Test;
import org.parts3492.partslib.PARTsUnit.PARTsUnitType;

import static org.junit.jupiter.api.Assertions.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
