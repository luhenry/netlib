/*
* Copyright 2020, Ludovic Henry
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

public class DgerTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int m = 5, n = 4;
        double alpha = 0.1;
        double[] x = new double[] {
            1.0, 2.0, 2.1,  4.0, 5.0 };
        double[] y = new double[] {
            3.0, 4.0, 5.1, -1.0 };
        double[] a = new double[] {
            1.0, 2.0, 3.0, 4.0, 5.0,
            2.0, 2.0, 3.0, 4.0, 5.0,
            3.0, 3.0, 3.0, 4.0, 5.0,
            4.0, 4.0, 4.0, 4.0, 4.0 };
        double[] expected = new double[] {
             1.3,  2.6,  3.63,  5.2,  6.5,
             2.4,  2.8,  3.84,  5.6,  7.0,
            3.51, 4.02, 4.071, 6.04, 7.55,
             3.9,  3.8,  3.79,  3.6,  3.5 };

        double[] a1 = a.clone();
        blas.dger(m, n, alpha, x, 1, y, 1, a1, m);
        assertArrayEquals(expected, a1, depsilon);
    }
}
