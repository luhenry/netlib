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

public class SsymmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        float[] expected, sgeCcopy;

        f2j.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 2.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "U", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "L", M, N, 1.0f, ssyA, M, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "U", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "L", M, N, 1.0f, ssyA, N, sgeB, M, 0.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "U", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("L", "L", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("L", "L", M, N, 0.0f, ssyA, M, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "U", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "U", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);

        f2j.ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, expected = sgeC.clone(), M);
        getImpl(blas).ssymm("R", "L", M, N, 0.0f, ssyA, N, sgeB, M, 1.0f, sgeCcopy = sgeC.clone(), M);
        assertArrayEquals(expected, sgeCcopy, sepsilon);
    }
}
