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

import org.netlib.util.doubleW;

public class DrotgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        doubleW da = new doubleW(2.0);
        doubleW db = new doubleW(3.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        getImpl(blas).drotg(da, db, c, s);
        assertEquals(3.6055512754639896, da.val);
        assertEquals(1.8027756377319950, db.val);
        assertEquals(0.5547001962252290, c.val);
        assertEquals(0.8320502943378436, s.val);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeros(String blas) {
        doubleW da = new doubleW(0.0);
        doubleW db = new doubleW(0.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        getImpl(blas).drotg(da, db, c, s);
        assertEquals(0.0, da.val);
        assertEquals(0.0, db.val);
        assertEquals(1.0, c.val);
        assertEquals(0.0, s.val);
    }
}
