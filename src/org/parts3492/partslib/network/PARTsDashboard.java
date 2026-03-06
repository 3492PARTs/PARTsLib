/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.network;

import org.parts3492.partslib.command.IPARTsSubsystem;

import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.ArrayList;

public class PARTsDashboard {
    private static DashboardTab state = DashboardTab.AUTONOMOUS;
    private static final PARTsNT partsNT = new PARTsNT();

    public enum DashboardTab {
        AUTONOMOUS("Autonomous"),
        TELEOPERATED("Teleoperated"),
        DEBUG("Dashboard");

        String tabName;

        DashboardTab(String s) {
            this.tabName = s;
        }
    }

    public PARTsDashboard() {
    }

    public static void setSubsystems(ArrayList<IPARTsSubsystem> subsystems, boolean post) {
        subsystems.forEach(
                s -> partsNT.putSmartDashboardSendable(
                        s.getName().replace("Phys", "").replace("Sim", ""), s, post));
    }

    public static void setCommandScheduler(boolean post) {
        partsNT.putSmartDashboardSendable("Command Scheduler", CommandScheduler.getInstance(), post);
    }

    public static void setTab(DashboardTab dashboardState) {
        state = dashboardState;
        Elastic.selectTab(state.tabName);
    }
}
