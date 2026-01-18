/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib.command;

import edu.wpi.first.wpilibj2.command.Command;

/** Add your docs here. */
public class PARTsCommandUtils {

    public static Command setCommandName(String name, Command c) {
        c.setName(name);
        return c;
    }
}
