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

public class DspmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        double[] expected, dYcopy;

        f2j.dspmv("U", M, 2.0, dgeAU, dX, 1, 2.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("U", M, 2.0, dgeAU, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dspmv("U", M, 2.0, dgeAU, dX, 1, 1.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("U", M, 2.0, dgeAU, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dspmv("U", M, 0.0, dgeAU, dX, 1, 2.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("U", M, 0.0, dgeAU, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dspmv("L", M, 2.0, dgeAL, dX, 1, 2.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("L", M, 2.0, dgeAL, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dspmv("L", M, 2.0, dgeAL, dX, 1, 1.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("L", M, 2.0, dgeAL, dX, 1, 1.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);

        f2j.dspmv("L", M, 0.0, dgeAL, dX, 1, 2.0, expected = dY.clone(), 1);
        getImpl(blas).dspmv("L", M, 0.0, dgeAL, dX, 1, 2.0, dYcopy = dY.clone(), 1);
        assertArrayEquals(expected, dYcopy, depsilon);
    }
}
