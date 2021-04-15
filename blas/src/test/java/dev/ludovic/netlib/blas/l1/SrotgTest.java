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

import org.netlib.util.floatW;

public class SrotgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(String blas) {
        floatW sa = new floatW(2.0f);
        floatW sb = new floatW(3.0f);
        floatW c = new floatW(0.0f);
        floatW s = new floatW(0.0f);

        getImpl(blas).srotg(sa, sb, c, s);
        assertEquals(3.6055514812469482f, sa.val);
        assertEquals(1.8027758598327637f, sb.val);
        assertEquals(0.5547001361846924f, c.val);
        assertEquals(0.8320502638816833f, s.val);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeros(String blas) {
        floatW da = new floatW(0.0f);
        floatW db = new floatW(0.0f);
        floatW c = new floatW(0.0f);
        floatW s = new floatW(0.0f);

        getImpl(blas).srotg(da, db, c, s);
        assertEquals(0.0f, da.val);
        assertEquals(0.0f, db.val);
        assertEquals(1.0f, c.val);
        assertEquals(0.0f, s.val);
    }
}
