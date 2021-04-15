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

public class DdotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        assertEquals(f2j.ddot(M, dX, 1, dY, 1), getImpl(blas).ddot(M, dX, 1, dY, 1), depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBound(String blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            getImpl(blas).ddot(M + 1, dX, 1, dY, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBoundBecauseOfOffset(String blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            getImpl(blas).ddot(M, dX, 1, 1, dY, 1, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOutOfBoundOnlyForX(String blas) {
        assertThrows(java.lang.IndexOutOfBoundsException.class, () -> {
            getImpl(blas).ddot(M, dX, 2, dY, 1);
        });
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testXAndYAreNullAndNIsZero(String blas) {
        assertEquals(0.0, getImpl(blas).ddot(0, null, 1, null, 1));
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testXAndYAreNullAndNIsOne(String blas) {
        assertThrows(java.lang.NullPointerException.class, () -> {
            getImpl(blas).ddot(M, null, 1, null, 1);
        });
    }
}
