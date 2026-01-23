/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.command;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj2.command.Subsystem;

/** PARTsSubsystem Interface. The base subsystem class for PARTs. */
public interface IPARTsSubsystem extends Subsystem, Sendable {

    public void outputTelemetry();

    public void stop();

    public void reset();

    public void log();
}
