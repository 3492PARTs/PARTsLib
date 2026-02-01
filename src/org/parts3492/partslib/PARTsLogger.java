/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import org.parts3492.partslib.game.FieldBase;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import com.pathplanner.lib.util.PathPlannerLogging;

public class PARTsLogger {
    private static DataLog log;
    private String name = "";
    private boolean loggingEnabled = false;

    /**
     * Create a new PARTsLogger.
     *
     * <p>By default, logging is disabled.
     */
    public PARTsLogger() {
        instantiate(false);
    }

    /**
     * Create a new PARTsLogger.
     *
     * <p>
     *
     * @param allowLogging Enables logging.
     */
    public PARTsLogger(boolean allowLogging) {
        instantiate(allowLogging);
    }

    /**
     * Create a new PARTsLogger with the following name.
     *
     * <p>By default, logging is disabled.
     */
    public PARTsLogger(String name) {
        instantiate(false);
        this.name = name;
    }

    /**
     * Create a new PARTsLogger with the following name.
     *
     * <p>
     *
     * @param allowLogging Enables logging.
     */
    public PARTsLogger(String name, boolean allowLogging) {
        instantiate(allowLogging);
        this.name = name;
    }

    /**
     * Create a new PARTsLogger with the object's class name.
     *
     * <p>E.g. <code>PARTsLogger</code>.
     *
     * <p>By default, logging is disabled.
     */
    public PARTsLogger(Object o) {
        name = o.getClass().getSimpleName();
        instantiate(false);
    }

    /**
     * Create a new PARTsLogger with the object's class name.
     *
     * <p>E.g. <code>PARTsLogger</code>.
     *
     * <p>
     *
     * @param allowLogging Enables logging.
     */
    public PARTsLogger(Object o, boolean allowLogging) {
        name = o.getClass().getSimpleName();
        instantiate(allowLogging);
    }

    private void instantiate(boolean allowLogging) {
        loggingEnabled = allowLogging;
        if (loggingEnabled) {
            // Starts recording to data log
            DataLogManager.start();

            if (log == null) log = DataLogManager.getLog();
        }
    }

    /** Enables the logging if it has been disabled. */
    public void enableLogging() {
        instantiate(true);
    }

    /** Disables the logging. */
    public void disableLogging() {
        instantiate(false);
    }

    public boolean logBoolean(String key, boolean b) {
        if (loggingEnabled) {
            new BooleanLogEntry(log, name.length() > 0 ? String.format("%s/%s", name, key) : key)
                    .append(b);
            return true;
        } else return false;
    }

    public boolean logDouble(String key, double d) {
        if (loggingEnabled) {
            new DoubleLogEntry(log, name.length() > 0 ? String.format("%s/%s", name, key) : key)
                    .append(d);
            return true;
        } else return false;
    }

    public boolean logString(String key, String s) {
        if (loggingEnabled) {
            new StringLogEntry(log, name.length() > 0 ? String.format("%s/%s", name, key) : key)
                    .append(s);
            return true;
        } else return false;
    }

    public void logCommandScheduler() {

        // Set the scheduler to log events for command initialize, interrupt, finish
        CommandScheduler.getInstance()
                .onCommandInitialize(
                        command -> {
                            logString(command.getName(), "Command initialized");
                        });
        CommandScheduler.getInstance()
                .onCommandInterrupt(
                        command -> {
                            logString(command.getName(), "Command interrupted");
                        });
        CommandScheduler.getInstance()
                .onCommandFinish(
                        command -> {
                            logString(command.getName(), "Command finished");
                        });
    }

    public void logPathPlanner() {
        // Logging callback for target robot pose
        PathPlannerLogging.setLogTargetPoseCallback(
                (pose) -> {
                    // Do whatever you want with the pose here
                    FieldBase.FIELD2D
                            .getObject("target pose")
                            .setPose(FieldBase.conditionallyTransformToOppositeAlliance(pose));
                });

        // Logging callback for the active path, this is sent as a list of poses
        PathPlannerLogging.setLogActivePathCallback(
                (poses) -> {
                    // Do whatever you want with the poses here
                    FieldBase.FIELD2D
                            .getObject("path")
                            .setPoses(FieldBase.conditionallyTransformToOppositeAlliance(poses));
                });
    }
}
