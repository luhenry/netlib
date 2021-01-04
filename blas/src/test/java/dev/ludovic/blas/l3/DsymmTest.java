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

import dev.ludovic.blas.BLAS;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

public class DsymmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int m = 5;
        int n = 4;
        double[] aR = new double[] {
            0.0,  2.0,  0.0,  0.0,
            2.0,  0.0,  1.0,  0.0,
            0.0,  1.0,  0.0,  3.0,
            0.0,  0.0,  3.0,  2.5 };
        double[] aL = new double[] {
            0.0,  2.0,  0.0,  0.0,  1.5,
            2.0,  0.0,  1.0,  0.0,  1.5,
            0.0,  1.0,  0.0,  3.0,  1.5,
            0.0,  0.0,  3.0,  2.5,  1.5,
            1.5,  1.5,  1.5,  1.5,  1.5, };
        double[] b = new double[] {
             1.0,  0.0,  0.0,  5.0, -2.0,
             0.0,  2.0,  1.0,  1.0,  5.0,
             0.0,  5.0,  2.0, -2.0,  3.0,
            -1.0,  3.0, -1.0, -2.0,  0.0 };
        double[] c = new double[] {
             1.0,  0.0,  2.0,  1.0, 3.0,
             0.0,  0.0,  1.0,  0.0, 3.0,
            -4.0,  0.0,  3.0, -2.0, 1.0,
             2.0, -5.0, -4.0, -1.0, 3.0 };
        double[] expected1 = new double[] {
             -1.0,  -1.0,  16.0,  11.5,  12.0,
             11.5,   8.5,  14.5,  13.0,  19.5,
              6.5,   6.5,   9.5,   1.5,  14.0,
             10.0, -13.0, -11.0, -10.0,   4.5 };
        double[] expected2 = new double[] {
              2.0,   4.0,   6.0,   4.0,  16.0,
              2.0,   5.0,   4.0,   8.0,   5.0,
            -11.0,  11.0,   4.0,  -9.0,   7.0,
              1.5,  12.5,  -4.5, -13.0,  15.0 };
        double[] expected3 = new double[] {
             -3.0,  -1.0,  12.0,   9.5,   6.0,
             11.5,   8.5,  12.5,  13.0,  13.5,
             14.5,   6.5,   3.5,   5.5,  12.0,
              6.0,  -3.0,  -3.0,  -8.0,  -1.5 };
        double[] expected4 = new double[] {
              0.0,   4.0,   2.0,   2.0,  10.0,
              2.0,   5.0,   2.0,   8.0,  -1.0,
             -3.0,  11.0,  -2.0,  -5.0,   5.0,
             -2.5,  22.5,   3.5, -11.0,   9.0 };
        double[] expected5 = new double[] {
              1.0,   0.0,   2.0,   1.0,   3.0,
              0.0,   0.0,   1.0,   0.0,   3.0,
             -4.0,   0.0,   3.0,  -2.0,   1.0,
              2.0,  -5.0,  -4.0,  -1.0,   3.0 };

        double[] c1 = c.clone();
        blas.dsymm("L", "U", m, n, 1.0, aL, m, b, m, 2.0, c1, m);
        assertArrayEquals(expected1, c1, depsilon);

        double[] c2 = c.clone();
        blas.dsymm("L", "L", m, n, 1.0, aL, m, b, m, 2.0, c2, m);
        assertArrayEquals(expected1, c2, depsilon);

        double[] c3 = c.clone();
        blas.dsymm("R", "U", m, n, 1.0, aR, n, b, m, 2.0, c3, m);
        assertArrayEquals(expected2, c3, depsilon);

        double[] c4 = c.clone();
        blas.dsymm("R", "L", m, n, 1.0, aR, n, b, m, 2.0, c4, m);
        assertArrayEquals(expected2, c4, depsilon);

        double[] c5 = c.clone();
        blas.dsymm("L", "U", m, n, 1.0, aL, m, b, m, 0.0, c5, m);
        assertArrayEquals(expected3, c5, depsilon);

        double[] c6 = c.clone();
        blas.dsymm("L", "L", m, n, 1.0, aL, m, b, m, 0.0, c6, m);
        assertArrayEquals(expected3, c6, depsilon);

        double[] c7 = c.clone();
        blas.dsymm("R", "U", m, n, 1.0, aR, n, b, m, 0.0, c7, m);
        assertArrayEquals(expected4, c7, depsilon);

        double[] c8 = c.clone();
        blas.dsymm("R", "L", m, n, 1.0, aR, n, b, m, 0.0, c8, m);
        assertArrayEquals(expected4, c8, depsilon);

        double[] c9 = c.clone();
        blas.dsymm("L", "U", m, n, 0.0, aL, m, b, m, 1.0, c9, m);
        assertArrayEquals(expected5, c9, depsilon);

        double[] c10 = c.clone();
        blas.dsymm("L", "L", m, n, 0.0, aL, m, b, m, 1.0, c10, m);
        assertArrayEquals(expected5, c10, depsilon);

        double[] c11 = c.clone();
        blas.dsymm("R", "U", m, n, 0.0, aR, n, b, m, 1.0, c11, m);
        assertArrayEquals(expected5, c11, depsilon);

        double[] c12 = c.clone();
        blas.dsymm("R", "L", m, n, 0.0, aR, n, b, m, 1.0, c12, m);
        assertArrayEquals(expected5, c12, depsilon);
    }
}
