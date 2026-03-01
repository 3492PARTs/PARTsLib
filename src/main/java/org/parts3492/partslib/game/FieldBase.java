/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.game;

import org.parts3492.partslib.RobotUtils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.FieldObject2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Base field class for generic info.
 *
 * <p>Extend this class in your project to add season specific field data.
 */
public interface FieldBase {

    public static final Field2d FIELD2D = new Field2d();

    /** The width of the field. */
    double WIDTH = Units.inchesToMeters(317.000);

    /** The height of the field. */
    double LENGTH = Units.inchesToMeters(690.876);

    // #region Conditional Transforms
    /**
     * Conditionally transform a pose to the opposite of the current alliance.
     *
     * @param pose The pose to transform.
     * @return The transformed pose.
     */
    public static Pose3d conditionallyTransformToOppositeAlliance(Pose3d pose) {
        return RobotUtils.isBlue() ? pose : transformToOppositeAlliance(pose);
    }

    /**
     * Conditionally transform a pose to the opposite of the current alliance.
     *
     * @param pose The pose to transform.
     * @return The transformed pose.
     */
    public static Pose2d conditionallyTransformToOppositeAlliance(Pose2d pose) {
        return RobotUtils.isBlue() ? pose : transformToOppositeAlliance(pose);
    }

    /**
     * Conditionally transform a translation to the opposite of the current alliance.
     *
     * @param translation The translation to transform.
     * @return The transformed translation.
     */
    public static Translation2d conditionallyTransformToOppositeAlliance(
            Translation2d translation) {
        return RobotUtils.isBlue() ? translation : transformToOppositeAlliance(translation);
    }

    /**
     * Conditionally transform a list of poses to the opposite of the current alliance.
     *
     * @param poses The poses to transform.
     * @return The transformed poses.
     */
    public static List<Pose2d> conditionallyTransformToOppositeAlliance(List<Pose2d> poses) {
        return RobotUtils.isBlue() ? poses : transformToOppositeAlliance(poses);
    }

    // #endregion

    // #region Transforms
    /**
     * Transform a pose to the opposite of the current alliance.
     *
     * @param pose The pose to transform.
     * @return The transformed pose.
     */
    public static Pose3d transformToOppositeAlliance(Pose3d pose) {
        Pose3d rotated = pose.rotateBy(new Rotation3d(0, 0, Math.PI));

        return new Pose3d(
                rotated.getTranslation().plus(new Translation3d(LENGTH, WIDTH, 0)),
                rotated.getRotation());
    }

    /**
     * Transform a pose to the opposite of the current alliance.
     *
     * @param pose The pose to transform.
     * @return The transformed pose.
     */
    public static Pose2d transformToOppositeAlliance(Pose2d pose) {
        Pose2d rotated = pose.rotateBy(Rotation2d.fromDegrees(180));
        return new Pose2d(
                rotated.getTranslation().plus(new Translation2d(LENGTH, WIDTH)),
                rotated.getRotation());
    }

    /**
     * Transform a translation to the opposite of the current alliance.
     *
     * @param translation The translation to transform.
     * @return The transformed translation.
     */
    public static Translation2d transformToOppositeAlliance(Translation2d translation) {
        return new Translation2d(LENGTH - translation.getX(), WIDTH - translation.getY());
    }

    /**
     * Transform a list of poses to the opposite of the current alliance.
     *
     * @param poses The poses to transform.
     * @return The transformed poses.
     */
    public static List<Pose2d> transformToOppositeAlliance(List<Pose2d> poses) {
        List<Pose2d> newPoses = new ArrayList<>();
        for (Pose2d pose : poses) {
            newPoses.add(transformToOppositeAlliance(pose));
        }
        return newPoses;
    }

    // #endregion

    /*** APRILTAGS ***/

    /** Array of all AprilTags on the field. */
    AprilTag APRILTAGS[] = {};

    /**
     * Check if an AprilTag is valid.
     *
     * @param id The ID of the AprilTag.
     * @return True if the tag is valid.
     */
    public static boolean isValidTag(int id) {
        for (AprilTag tag : APRILTAGS) {
            if (tag.getID() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get an {@link org.parts3492.partslib.game.AprilTag AprilTag} by its ID.
     *
     * @param id The AprilTag ID.
     * @return The AprilTag with the matching ID if the ID is valid, otherwise null.
     */
    public static AprilTag getTag(int id) {
        for (AprilTag tag : APRILTAGS) {
            if (tag.getID() == id) {
                return tag;
            }
        }
        return null;
    }

    /**
     * Get all AprilTag IDs.
     *
     * @return Array of AprilTag IDs.
     */
    public static int[] getAllTagIDs() {
        int[] ids = new int[APRILTAGS.length];

        for (int i = 0; i < APRILTAGS.length; i++) {
            ids[i] = APRILTAGS[i].getID();
        }

        return ids;
    }

    /**** EMPTY FIELD POSES ****/

    Pose2d EMPTY_FIELD_POSE2D = new Pose2d(new Translation2d(-1, -1), new Rotation2d());

    Pose3d EMPTY_FIELD_POSE3D = new Pose3d(-1, -1, 0, new Rotation3d());

    public static void clearFieldObject(FieldObject2d fieldObject) {
        fieldObject.setPose(EMPTY_FIELD_POSE2D);
    }
}
