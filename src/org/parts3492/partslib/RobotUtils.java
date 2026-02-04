/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.RuntimeType;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * Robot Utility Functions
 *
 * <p>Miscellaneous functions that are needed for PARTsLib.
 */
public class RobotUtils {
    private static Alliance alliance;

    /**
     * Check if the robot is on the blue alliance.
     *
     * @return True if the robot is on the blue alliance, else false.
     */
    public static boolean isBlue() {
        return alliance == Alliance.Blue;
    }

    /**
     * Check if the robot is real or in sim.
     *
     * @return True if real, else false.
     */
    public static boolean isReal() {
        RuntimeType runtimeType = TimedRobot.getRuntimeType();
        return runtimeType == RuntimeType.kRoboRIO || runtimeType == RuntimeType.kRoboRIO2;
    }

    /** Get the alliance the robot is currently on. */
    public void getAlliance() {
        if (DriverStation.getAlliance().isPresent()) {
            alliance = DriverStation.getAlliance().get();
        }
    }
}
