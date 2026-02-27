/*
 * Copyright 2020, 2021, Ludovic Henry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Please contact git@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.blas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import org.netlib.util.doubleW;

import static dev.ludovic.netlib.test.TestHelpers.*;

public class DrotgTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        doubleW da = new doubleW(2.0);
        doubleW db = new doubleW(3.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        blas.drotg(da, db, c, s);
        assertEquals(3.6055512754639896, da.val);
        assertEquals(1.8027756377319950, db.val);
        assertEquals(0.5547001962252290, c.val);
        assertEquals(0.8320502943378436, s.val);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testZeros(BLAS blas) {
        doubleW da = new doubleW(0.0);
        doubleW db = new doubleW(0.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        blas.drotg(da, db, c, s);
        assertEquals(0.0, da.val);
        assertEquals(0.0, db.val);
        assertEquals(1.0, c.val);
        assertEquals(0.0, s.val);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testLargeValues(BLAS blas) {
        doubleW da = new doubleW(1e15);
        doubleW db = new doubleW(1e15);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        doubleW daRef = new doubleW(1e15);
        doubleW dbRef = new doubleW(1e15);
        doubleW cRef = new doubleW(0.0);
        doubleW sRef = new doubleW(0.0);

        f2j.drotg(daRef, dbRef, cRef, sRef);
        blas.drotg(da, db, c, s);
        assertEquals(daRef.val, da.val, depsilon);
        assertEquals(dbRef.val, db.val, depsilon);
        assertEquals(cRef.val, c.val, depsilon);
        assertEquals(sRef.val, s.val, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testNegativeValues(BLAS blas) {
        doubleW da = new doubleW(-3.0);
        doubleW db = new doubleW(-4.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        doubleW daRef = new doubleW(-3.0);
        doubleW dbRef = new doubleW(-4.0);
        doubleW cRef = new doubleW(0.0);
        doubleW sRef = new doubleW(0.0);

        f2j.drotg(daRef, dbRef, cRef, sRef);
        blas.drotg(da, db, c, s);
        assertEquals(daRef.val, da.val, depsilon);
        assertEquals(dbRef.val, db.val, depsilon);
        assertEquals(cRef.val, c.val, depsilon);
        assertEquals(sRef.val, s.val, depsilon);
    }

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testOneZero(BLAS blas) {
        // a=0, b!=0
        doubleW da = new doubleW(0.0);
        doubleW db = new doubleW(5.0);
        doubleW c = new doubleW(0.0);
        doubleW s = new doubleW(0.0);

        doubleW daRef = new doubleW(0.0);
        doubleW dbRef = new doubleW(5.0);
        doubleW cRef = new doubleW(0.0);
        doubleW sRef = new doubleW(0.0);

        f2j.drotg(daRef, dbRef, cRef, sRef);
        blas.drotg(da, db, c, s);
        assertEquals(daRef.val, da.val, depsilon);
        assertEquals(dbRef.val, db.val, depsilon);
        assertEquals(cRef.val, c.val, depsilon);
        assertEquals(sRef.val, s.val, depsilon);

        // a!=0, b=0
        da = new doubleW(5.0);
        db = new doubleW(0.0);
        c = new doubleW(0.0);
        s = new doubleW(0.0);

        daRef = new doubleW(5.0);
        dbRef = new doubleW(0.0);
        cRef = new doubleW(0.0);
        sRef = new doubleW(0.0);

        f2j.drotg(daRef, dbRef, cRef, sRef);
        blas.drotg(da, db, c, s);
        assertEquals(daRef.val, da.val, depsilon);
        assertEquals(dbRef.val, db.val, depsilon);
        assertEquals(cRef.val, c.val, depsilon);
        assertEquals(sRef.val, s.val, depsilon);
    }
}
