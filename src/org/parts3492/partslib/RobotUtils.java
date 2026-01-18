/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RuntimeType;

public class RobotUtils {
    private static Alliance alliance;

    public static boolean isBlue() {
        return alliance == Alliance.Blue;
    }

    public static boolean isReal() {
        RuntimeType runtimeType = RobotBase.getRuntimeType();
        return runtimeType == RuntimeType.kRoboRIO || runtimeType == RuntimeType.kRoboRIO2;
    }

    public void getAlliance() {
        if (DriverStation.getAlliance().isPresent()) {
            alliance = DriverStation.getAlliance().get();
        }
    }
}
