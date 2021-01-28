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

public class SrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expectedX, expectedY, sXcopy, sYcopy;

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 2.0f, 3.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 2.0f, 3.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 0.0f, 3.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 0.0f, 3.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 2.0f, 0.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 2.0f, 0.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);

        f2j.srot(M, expectedX = sX.clone(), 1, expectedY = sY.clone(), 1, 0.0f, 0.0f);
        blas.srot(M, sXcopy = sX.clone(), 1, sYcopy = sY.clone(), 1, 0.0f, 0.0f);
        assertArrayEquals(expectedX, sXcopy, sepsilon);
        assertArrayEquals(expectedY, sYcopy, sepsilon);
    }
}
