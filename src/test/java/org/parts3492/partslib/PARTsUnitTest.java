/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import static org.junit.jupiter.api.Assertions.*;

import org.parts3492.partslib.PARTsUnit.PARTsUnitType;

import org.junit.jupiter.api.Test;

class PARTsUnitTest {

    //#region From INCHES
    @Test
    /**
     * Test inchest to meter in PARTsUnit.
     */
    public void testConvertInchesToMeters() {
        PARTsUnit unit = new PARTsUnit(39.37008, PARTsUnitType.Inch);
        assertEquals(1.0, Math.floor(unit.to(PARTsUnitType.Meter)), 1e-5);
    }

    @Test
    public void testConvertInchesToFeet() {
        PARTsUnit unit = new PARTsUnit(12, PARTsUnitType.Inch);
        assertEquals(1.0, Math.floor(unit.to(PARTsUnitType.Foot)), 1e-5);
    }

    @Test
    public void testConvertInchesToInches() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Inch);
        assertEquals(1.0, Math.floor(unit.to(PARTsUnitType.Inch)), 1e-5);
    }
    //#endregion

    //#region From METERS
    @Test
    public void testConvertMetersToInches() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Meter);
        assertEquals(39.37008, unit.to(PARTsUnitType.Inch), 1e-5);
    }

    @Test
    public void testConvertMetersToFeet() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Meter);
        assertEquals(3.28084, unit.to(PARTsUnitType.Foot), 1e-5);
    }

    @Test
    public void testConvertMetersToMeters() {
        PARTsUnit unit = new PARTsUnit(1, PARTsUnitType.Meter);
        assertEquals(1.0, unit.to(PARTsUnitType.Meter), 1e-5);
    }
    //#endregion

    //#region From ANGLE
    @Test
    public void testConvertAngleToRadian() {
        PARTsUnit unit = new PARTsUnit(57.29578, PARTsUnitType.Angle);
        assertEquals(1.0, unit.to(PARTsUnitType.Radian), 1e-5);
    }

    @Test
    public void testConvertAngleToAngle() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Angle);
        assertEquals(1.0, unit.to(PARTsUnitType.Angle));
    }
    //#endregion

    //#region From RADIAN
    @Test
    public void testConvertRadianToAngle() {
        PARTsUnit unit = new PARTsUnit(0.01745329, PARTsUnitType.Radian);
        assertEquals(1.0, unit.to(PARTsUnitType.Angle), 1e-5);
    }

    @Test
    public void testConvertRadianToRadian() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Radian);
        assertEquals(1.0, unit.to(PARTsUnitType.Radian), 1e-5);
    }
    //#endregion

    //#region From ROTATIONS
    @Test
    public void testConvertRotationToAngle() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Rotations);
        assertEquals(360, unit.to(PARTsUnitType.Angle), 1e-5);
    }

    @Test
    public void testConvertRotationToRotation() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Rotations);
        assertEquals(1.0, unit.to(PARTsUnitType.Rotations), 1e-5);
    }
    //#endregion

    //#region From FOOT
    @Test
    public void testConvertFootToMeter() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Foot);
        assertEquals(0.3048, unit.to(PARTsUnitType.Meter), 1e-5);
    }

    @Test
    public void testConvertFootToInch() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Foot);
        assertEquals(12, unit.to(PARTsUnitType.Inch), 1e-5);
    }

    @Test
    public void testConvertFootToFoot() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Foot);
        assertEquals(1.0, unit.to(PARTsUnitType.Foot), 1e-5);
    }
    //#region

    //#region From PERCENT
    @Test
    public void testConvertPercentToPercent() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Percent);
        assertEquals(1.0, unit.to(PARTsUnitType.Percent), 1e-5);
    }
    //#region

    //#region From POUND
    @Test
    public void testConvertPoundToKilogram() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Pound);
        assertEquals(0.4535924, unit.to(PARTsUnitType.Kilogram), 1e-5);
    }

    @Test
    public void testConvertPoundToPound() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Pound);
        assertEquals(1.0, unit.to(PARTsUnitType.Pound), 1e-5);
    }
    //#region

    //#region From KILOGRAM
    @Test
    public void testConvertKilogramToPound() {
        PARTsUnit unit = new PARTsUnit(1, PARTsUnitType.Kilogram);
        assertEquals(2.204623, unit.to(PARTsUnitType.Pound), 1e-5);
    }

    @Test
    public void testConvertKilogramToKilogram() {
        PARTsUnit unit = new PARTsUnit(1.0, PARTsUnitType.Kilogram);
        assertEquals(1.0, unit.to(PARTsUnitType.Kilogram), 1e-5);
    }
    //#endregion

    //#region Static Conversions
    @Test
    public void testConvertStaticInchesToMeters() {
        assertEquals(0.0254, PARTsUnit.InchesToMeters.apply(1.0), 1e-5);
    }

    @Test
    public void testConvertStaticMetersToInches() {
        assertEquals(39.37008, PARTsUnit.MetersToInches.apply(1.0), 1e-5);
    }

    @Test
    public void testConvertStaticDegreesToRadians() {
        assertEquals(0.01745329, PARTsUnit.DegreesToRadians.apply(1.0), 1e-5);
    }
    //#endregion

    //#region Misc Tests
    @Test
    public void testGetMagnitude() {
        PARTsUnit unit = new PARTsUnit(-45, PARTsUnitType.Inch);
        assertEquals(45, unit.getMagnitude(), 1e-5);
    }

    @Test
    public void testGetValue() {
        PARTsUnit unit = new PARTsUnit(45, PARTsUnitType.Inch);
        assertEquals(45, unit.getValue(), 1e-5);
    }
    //#endregion
}
