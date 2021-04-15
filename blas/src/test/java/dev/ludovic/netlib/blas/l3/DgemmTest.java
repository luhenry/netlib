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

public class DgemmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        double[] expected, dgeCcopy;

        f2j.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 2.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 2.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 2.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 0.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "N", M, N, K, 1.0, dgeA, M, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 0.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "T", M, N, K, 1.0, dgeA, M, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "N", M, N, K, 1.0, dgeAT, K, dgeB, K, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "T", M, N, K, 1.0, dgeAT, K, dgeBT, N, 0.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "N", M, N, K, 0.0, dgeA, M, dgeB, K, 1.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "N", M, N, K, 0.0, dgeA, M, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("N", "T", M, N, K, 0.0, dgeA, M, dgeBT, N, 1.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("N", "T", M, N, K, 0.0, dgeA, M, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "N", M, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "N", M, N, K, 0.0, dgeAT, K, dgeB, K, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);

        f2j.dgemm("T", "T", M, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, expected = dgeC.clone(), M);
        getImpl(blas).dgemm("T", "T", M, N, K, 0.0, dgeAT, K, dgeBT, N, 1.0, dgeCcopy = dgeC.clone(), M);
        assertArrayEquals(expected, dgeCcopy, depsilon);
    }
}
