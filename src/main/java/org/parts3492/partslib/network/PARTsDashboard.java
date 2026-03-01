/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.network;

import org.parts3492.partslib.command.IPARTsSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.ArrayList;

public class PARTsDashboard {
    private static DashboardTab state = DashboardTab.AUTONOMOUS;

    public enum DashboardTab {
        AUTONOMOUS("Autonomous"),
        TELEOPERATED("Teleoperated"),
        DEBUG("Dashboard");

        String tabName;

        DashboardTab(String s) {
            this.tabName = s;
        }
    }

    public PARTsDashboard() {}

    public static void setSubsystems(ArrayList<IPARTsSubsystem> subsystems) {
        subsystems.forEach(s -> SmartDashboard.putData(s));
    }

    public static void setCommandScheduler() {
        SmartDashboard.putData(CommandScheduler.getInstance());
    }

    public static void setTab(DashboardTab dashboardState) {
        state = dashboardState;
        Elastic.selectTab(state.tabName);
    }
}
