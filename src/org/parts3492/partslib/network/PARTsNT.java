/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.network;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.BooleanTopic;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.DoubleTopic;
import edu.wpi.first.networktables.IntegerEntry;
import edu.wpi.first.networktables.IntegerTopic;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.networktables.StringTopic;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.pathplanner.lib.util.PathPlannerLogging;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/**
 * PARTs NetworkTables Easy API.
 *
 * <p>{@code PARTsNT partsNT = new PARTsNT(this)}
 *
 * <p>This class is meant to be used as an instance for each class.
 */
public class PARTsNT {
    public String name = "Generic";

    private NetworkTableInstance nt_Instance = NetworkTableInstance.getDefault();
    private NetworkTable table;

    private final ConcurrentMap<String, EasyEntry> entries = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Sendable> smartDashboardSendables =
            new ConcurrentHashMap<>();

    private sealed interface EasyEntry
            permits EasyBooleanEntry, EasyIntegerEntry, EasyDoubleEntry, EasyStringEntry {
        String key();

        public boolean blockUpdates = false;

        public void close();
    }

    public static final class EasyBooleanEntry implements EasyEntry {
        private final String topicName;
        private final BooleanTopic topic;
        private final BooleanEntry entry;

        private volatile boolean cached;

        EasyBooleanEntry(String key, NetworkTable table, boolean initial) {
            this.topicName = key;
            this.topic = table.getBooleanTopic(key);
            this.entry = topic.getEntry(initial);
            this.cached = initial;
        }

        @Override
        public String key() {
            return topicName;
        }

        private boolean getFromEntry() {
            return entry.get();
        }

        public boolean get() {
            if (blockUpdates) {
                return cached;
            }
            return getFromEntry();
        }

        public void set(boolean value) {
            if (blockUpdates) {
                return;
            }

            if (cached != value) {
                entry.set(value);
                cached = value;
            }
        }

        @Override
        public void close() {
            entry.close();
        }
    }

    public static final class EasyIntegerEntry implements EasyEntry {
        private final String topicName;
        private final IntegerTopic topic;
        private final IntegerEntry entry;

        private volatile int cachedValue;

        EasyIntegerEntry(String key, NetworkTable table, int initial) {
            this.topicName = key;
            this.topic = table.getIntegerTopic(key);
            this.entry = topic.getEntry(initial);
            this.cachedValue = initial;
        }

        @Override
        public String key() {
            return topicName;
        }

        private int getFromEntry() {
            return Math.toIntExact(entry.get());
        }

        public int get() {
            if (blockUpdates) {
                return cachedValue;
            }
            return getFromEntry();
        }

        public void set(int value) {
            if (blockUpdates) {
                return;
            }

            if (cachedValue != value) {
                entry.set(value);
                cachedValue = value;
            }
        }

        @Override
        public void close() {
            entry.close();
        }
    }

    public static final class EasyDoubleEntry implements EasyEntry {
        private final String topicName;
        private final DoubleTopic topic;
        private final DoubleEntry entry;

        private volatile double cachedValue;

        EasyDoubleEntry(String key, NetworkTable table, double initial) {
            this.topicName = key;
            this.topic = table.getDoubleTopic(key);
            this.entry = topic.getEntry(initial);
            this.cachedValue = initial;
        }

        @Override
        public String key() {
            return topicName;
        }

        private double getFromEntry() {
            return entry.get();
        }

        public double get() {
            if (blockUpdates) {
                return cachedValue;
            }
            return getFromEntry();
        }

        public void set(double value) {
            if (blockUpdates) {
                return;
            }

            if (cachedValue != value) {
                entry.set(value);
                cachedValue = value;
            }
        }

        @Override
        public void close() {
            entry.close();
        }
    }

    public static final class EasyStringEntry implements EasyEntry {
        private final String topicName;
        private final StringTopic topic;
        private final StringEntry entry;

        private volatile String cachedValue;

        EasyStringEntry(String key, NetworkTable table, String initial) {
            this.topicName = key;
            this.topic = table.getStringTopic(key);
            this.entry = topic.getEntry(initial);
            this.cachedValue = initial;
        }

        @Override
        public String key() {
            return topicName;
        }

        private String getFromEntry() {
            return entry.get();
        }

        public String get() {
            if (blockUpdates) {
                return cachedValue;
            }
            return getFromEntry();
        }

        public void set(String value) {
            if (blockUpdates) {
                return;
            }

            if (!cachedValue.equals(value)) {
                entry.set(value);
                cachedValue = value;
            }
        }

        @Override
        public void close() {
            entry.close();
        }
    }

    /**
     * Creates a new PARTsNT instance.
     *
     * <p>Creates/uses the subtable "Generic" instead of the class subtable.
     *
     * <p>The object variation should be used instead.
     */
    public PARTsNT() {
        table = nt_Instance.getTable("PARTs").getSubTable("Generic");
    }

    /**
     * Creates a new PARTsNT instance.
     *
     * <p>Creates/uses the class subtable.
     *
     * @param o The class object. (E.g. passing in 'this'.)
     */
    public PARTsNT(Object o) {
        name = o.getClass().getSimpleName();
        table = nt_Instance.getTable("PARTs").getSubTable(name);
    }

    /**
     * Creates a new PARTsNT instance.
     *
     * <p>Creates/uses the subtable of the class via its name.
     *
     * <p>If the name is empty, then the "Generic" table will be used instead.
     *
     * @param className The name of the class.
     */
    public PARTsNT(String className) {
        name = (className != "") ? className : "Generic";
        table = nt_Instance.getTable("PARTs").getSubTable(name);
    }

    /**
     * Creates a new PARTsNT instance.
     *
     * <p>Creating a custom NetworkTableInstance is not recommended and should only be used in
     * advanced use cases.
     *
     * @param className The name of the class.
     * @param instance The NetworkTableInstance to use. If null, the default instance will be used.
     */
    public PARTsNT(String className, NetworkTableInstance instance) {
        nt_Instance = (instance != null) ? instance : NetworkTableInstance.getDefault();
        name = (!className.isBlank()) ? className : "Generic";
        table = nt_Instance.getTable("PARTs").getSubTable(name);
    }

    // * -------- HELPER FUNCTIONS -------- *//

    // * -------- TYPE SPECIFIC ENTRY CHECKS -------- *//

    private EasyBooleanEntry getBooleanEntry(String name) {
        return entries.get(name) instanceof EasyBooleanEntry entry ? entry : null;
    }

    private EasyIntegerEntry getIntegerEntry(String name) {
        return entries.get(name) instanceof EasyIntegerEntry entry ? entry : null;
    }

    private EasyDoubleEntry getDoubleEntry(String name) {
        return entries.get(name) instanceof EasyDoubleEntry entry ? entry : null;
    }

    private EasyStringEntry getStringEntry(String name) {
        return entries.get(name) instanceof EasyStringEntry entry ? entry : null;
    }

    // * -------- BOOLEAN FUNCTIONS -------- *//

    /**
     * Gets the boolean value from the requested entry.
     *
     * @param name The topic name.
     * @return Returns the boolean value if entry is found, otherwise returns false.
     */
    public boolean getBoolean(String name, boolean pull) {
        EasyBooleanEntry entry = getBooleanEntry(name);
        return (entry == null) ? false : entry.get();
    }

    /**
     * Updates the boolean value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     */
    public void putBoolean(String name, boolean value) {
        EasyBooleanEntry entry = getBooleanEntry(name);

        if (entry == null) {
            entries.put(name, new EasyBooleanEntry(name, table, value));
        } else {
            entry.set(value);
        }
    }

    /**
     * Updates the boolean value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putBoolean(String name, boolean value, boolean submit) {
        EasyBooleanEntry entry = getBooleanEntry(name);
        if (submit)
            if (entry == null) {
                entries.put(name, new EasyBooleanEntry(name, table, value));
            } else {
                entry.set(value);
            }
    }

    // #region
    // * -------- INTEGER FUNCTIONS -------- *//

    /**
     * Gets the integer value from the requested entry.
     *
     * @param name The topic name.
     * @return Returns the integer value if entry is found, otherwise returns zero.
     */
    public int getInteger(String name, boolean pull) {
        EasyIntegerEntry entry = getIntegerEntry(name);
        return (entry == null) ? 0 : entry.get();
    }

    /**
     * Updates the integer value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     */
    public void putInteger(String name, int value) {
        EasyIntegerEntry entry = getIntegerEntry(name);

        if (entry == null) {
            entries.put(name, new EasyIntegerEntry(name, table, value));
        } else {
            entry.set(value);
        }
    }

    /**
     * Updates the boolean value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putInteger(String name, int value, boolean submit) {
        EasyIntegerEntry entry = getIntegerEntry(name);
        if (submit)
            if (entry == null) {
                entries.put(name, new EasyIntegerEntry(name, table, value));
            } else {
                entry.set(value);
            }
    }

    // #endregion

    // #region DOUBLE FUNCTIONS
    // * -------- DOUBLE FUNCTIONS -------- *//

    /**
     * Gets the double value from the requested entry.
     *
     * @param name The topic name.
     * @return Returns the double value if entry is found, otherwise returns zero.
     */
    public double getDouble(String name, boolean pull) {
        EasyDoubleEntry entry = getDoubleEntry(name);
        return (entry == null) ? 0 : entry.get();
    }

    /**
     * Updates the double value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     */
    public void putDouble(String name, double value) {
        EasyDoubleEntry entry = getDoubleEntry(name);

        if (entry == null) {
            entries.put(name, new EasyDoubleEntry(name, table, value));
        } else {
            entry.set(value);
        }
    }

    /**
     * Updates the double value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putDouble(String name, double value, boolean submit) {
        EasyDoubleEntry entry = getDoubleEntry(name);
        if (submit)
            if (entry == null) {
                entries.put(name, new EasyDoubleEntry(name, table, value));
            } else {
                entry.set(value);
            }
    }

    // #endregion

    // #region AMBIGUOUS NUMBER FUNCTIONS
    // * -------- AMBIGUOUS NUMBER FUNCTIONS -------- *//

    /**
     * Sets the double value for the requested entry.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putNumber(String name, double value, boolean submit) {
        putDouble(name, value, submit);
    }

    /**
     * Sets the integer value for the requested entry.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putNumber(String name, int value, boolean submit) {
        putInteger(name, value, submit);
    }

    // #endregion

    // #region STRING FUNCTIONS
    // * -------- STRING FUNCTIONS -------- *//

    /**
     * Gets the string value from the requested entry.
     *
     * @param name The topic name.
     * @return Returns the string value if entry is found, otherwise returns an empty string.
     */
    public String getString(String name, boolean pull) {
        EasyStringEntry entry = getStringEntry(name);
        return (entry == null) ? "" : entry.get();
    }

    /**
     * Updates the string value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     */
    public void putString(String name, String value) {
        EasyStringEntry entry = getStringEntry(name);

        if (entry == null) {
            entries.put(name, new EasyStringEntry(name, table, value));
        } else {
            entry.set(value);
        }
    }

    /**
     * Updates the string value for the requested entry. The entry is created if it doesn't exist.
     *
     * @param name The name of the entry.
     * @param value The new value to publish to the entry.
     * @param submit Whether to actually create or update the entry.
     */
    public void putString(String name, String value, boolean submit) {
        EasyStringEntry entry = getStringEntry(name);
        if (submit)
            if (entry == null) {
                entries.put(name, new EasyStringEntry(name, table, value));
            } else {
                entry.set(value);
            }
    }

    // #endregion

    // #region REMOVAL FUNCTIONS
    // * -------- REMOVAL FUNCTIONS -------- *//

    /** Removes all previously created entries. */
    public void removeAllEntries() {
        for (EasyEntry entry : entries.values()) {
            entry.close();
        }
        entries.clear();
    }

    /**
     * Removes a previously created entry.
     *
     * @param name The name of the entry to remove.
     */
    public void removeEntry(String name) {
        EasyEntry entry = entries.remove(name);
        if (entry != null) {
            entry.close();
        }
    }

    // #endregion

    // #region SENDABLE FUNCTIONS
    /**
     * Adds a sendable to smart dashboard network table entry. Extra checks are made to prevent
     * extra loop overhead because pushing sendables to the dashboard is very expensive.
     *
     * @param key The name of the sendable entry.
     * @param data The sendable to add.
     * @param submit Whether to actually publish the sendable. This is important to prevent loop
     *     overruns. That is also why this is the only method that does not have an overload without
     *     this parameter.
     */
    public void putSmartDashboardSendable(String key, Sendable data, boolean submit) {
        if (!submit || data == null) return;

        String topic = name.equals("Generic") ? key : String.format("%s/%s", name, key);

        /**
         * This is done to prevent multiple registrations of the same sendables which will
         * absoultely cause loop overruns.
         */
        smartDashboardSendables.compute(
                topic,
                (k, existing) -> {
                    if (existing == null) {
                        SmartDashboard.putData(k, data);
                        return data;
                    }

                    return existing;
                });
    }

    /**
     * Sets up the PathPlanner logging callbacks.
     *
     * @param logTargetPose A Consumer that accepts a Pose2d for logging the target robot pose.
     * @param logActivePath A Consumer that accepts a List of Pose2d for logging the active path.
     * @param logEntry If true, the logging callbacks will be set up; if false, they will not be set
     *     up.
     */
    public void logPathPlanner(
            Consumer<Pose2d> logTargetPose,
            Consumer<List<Pose2d>> logActivePath,
            boolean logEntry) {

        if (logEntry) {
            // Logging callback for target robot pose
            PathPlannerLogging.setLogTargetPoseCallback(logTargetPose);

            // Logging callback for the active path, this is sent as a list of poses
            PathPlannerLogging.setLogActivePathCallback(logActivePath);
        }
    }
    // #endregion
}
