/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.command;

// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import org.parts3492.partslib.PARTsLogger;
import org.parts3492.partslib.PARTsPreferences;
import org.parts3492.partslib.network.PARTsNT;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Command based subsystem with built-in PARTs tools and features. */
public abstract class PARTsSubsystem extends SubsystemBase implements IPARTsSubsystem {
    protected PARTsNT partsNT;
    protected PARTsLogger partsLogger;
    protected PARTsPreferences partsPrefrences;

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use the "Generic" name.
     */
    public PARTsSubsystem() {
        partsNT = new PARTsNT(this.getName());
        partsLogger = new PARTsLogger(this.getName());
        partsPrefrences = new PARTsPreferences();
    }

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use the "Generic" name.
     *
     * @param enableLogging Enable the {@link org.parts3492.partslib.PARTsLogger PARTsLogger}
     *     instance for this subsystem.
     */
    public PARTsSubsystem(boolean enableLogging) {
        partsNT = new PARTsNT(this.getName());
        partsLogger = new PARTsLogger(this.getName(), enableLogging);
        partsPrefrences = new PARTsPreferences();
    }

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use given class name.
     *
     * @param o The object that the subsystem will use for the name.
     */
    public PARTsSubsystem(Object o) {
        partsNT = new PARTsNT(o);
        partsLogger = new PARTsLogger(o);
        partsPrefrences = new PARTsPreferences();
    }

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use given class name.
     *
     * @param o The object that the subsystem will use for the name.
     * @param enableLogging Enable the {@link org.parts3492.partslib.PARTsLogger PARTsLogger}
     *     instance for this subsystem.
     */
    public PARTsSubsystem(Object o, boolean enableLogging) {
        partsNT = new PARTsNT(o);
        partsLogger = new PARTsLogger(o, enableLogging);
        partsPrefrences = new PARTsPreferences();
    }

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use given class name.
     *
     * @param className The name that this subsystem should use as it's name.
     */
    public PARTsSubsystem(String className) {
        partsNT = new PARTsNT(className);
        partsLogger = new PARTsLogger(className);
        partsPrefrences = new PARTsPreferences();
    }

    /**
     * Creates a new PARTsSubsystem.
     *
     * <p>Comes with instances of PARTs tools that use given class name.
     *
     * @param className The name that this subsystem should use as it's name.
     * @param enableLogging Enable the {@link org.parts3492.partslib.PARTsLogger PARTsLogger}
     *     instance for this subsystem.
     */
    public PARTsSubsystem(String className, boolean enableLogging) {
        partsNT = new PARTsNT(className);
        partsLogger = new PARTsLogger(className);
        partsPrefrences = new PARTsPreferences();
    }
}
