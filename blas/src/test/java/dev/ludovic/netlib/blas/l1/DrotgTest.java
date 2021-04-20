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
}
