/*
 * Copyright (c) Ludovic Henry. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Ludovic Henry designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Ludovic Henry in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact hi@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

import dev.ludovic.netlib.BLAS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class DrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        double[] expectedX, expectedY, dXcopy, dYcopy;

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 3.0);
        getImpl(blas).drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 3.0);
        getImpl(blas).drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 3.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 2.0, 0.0);
        getImpl(blas).drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 2.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);

        f2j.drot(M, expectedX = dX.clone(), 1, expectedY = dY.clone(), 1, 0.0, 0.0);
        getImpl(blas).drot(M, dXcopy = dX.clone(), 1, dYcopy = dY.clone(), 1, 0.0, 0.0);
        assertArrayEquals(expectedX, dXcopy, depsilon);
        assertArrayEquals(expectedY, dYcopy, depsilon);
    }
}
