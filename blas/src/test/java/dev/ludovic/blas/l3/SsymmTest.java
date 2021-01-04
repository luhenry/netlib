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

public class SsymmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int m = 5;
        int n = 4;
        float[] aR = new float[] {
            0.0f,  2.0f,  0.0f,  0.0f,
            2.0f,  0.0f,  1.0f,  0.0f,
            0.0f,  1.0f,  0.0f,  3.0f,
            0.0f,  0.0f,  3.0f,  2.5f, };
        float[] aL = new float[] {
            0.0f,  2.0f,  0.0f,  0.0f,  1.5f,
            2.0f,  0.0f,  1.0f,  0.0f,  1.5f,
            0.0f,  1.0f,  0.0f,  3.0f,  1.5f,
            0.0f,  0.0f,  3.0f,  2.5f,  1.5f,
            1.5f,  1.5f,  1.5f,  1.5f,  1.5f, };
        float[] b = new float[] {
             1.0f,  0.0f,  0.0f,  5.0f, -2.0f,
             0.0f,  2.0f,  1.0f,  1.0f,  5.0f,
             0.0f,  5.0f,  2.0f, -2.0f,  3.0f,
            -1.0f,  3.0f, -1.0f, -2.0f,  0.0f, };
        float[] c = new float[] {
             1.0f,  0.0f,  2.0f,  1.0f, 3.0f,
             0.0f,  0.0f,  1.0f,  0.0f, 3.0f,
            -4.0f,  0.0f,  3.0f, -2.0f, 1.0f,
             2.0f, -5.0f, -4.0f, -1.0f, 3.0f, };
        float[] expected1 = new float[] {
             -1.0f,  -1.0f,  16.0f,  11.5f,  12.0f,
             11.5f,   8.5f,  14.5f,  13.0f,  19.5f,
              6.5f,   6.5f,   9.5f,   1.5f,  14.0f,
             10.0f, -13.0f, -11.0f, -10.0f,   4.5f, };
        float[] expected2 = new float[] {
              2.0f,   4.0f,   6.0f,   4.0f,  16.0f,
              2.0f,   5.0f,   4.0f,   8.0f,   5.0f,
            -11.0f,  11.0f,   4.0f,  -9.0f,   7.0f,
              1.5f,  12.5f,  -4.5f, -13.0f,  15.0f, };
        float[] expected3 = new float[] {
             -3.0f,  -1.0f,  12.0f,   9.5f,   6.0f,
             11.5f,   8.5f,  12.5f,  13.0f,  13.5f,
             14.5f,   6.5f,   3.5f,   5.5f,  12.0f,
              6.0f,  -3.0f,  -3.0f,  -8.0f,  -1.5f, };
        float[] expected4 = new float[] {
              0.0f,   4.0f,   2.0f,   2.0f,  10.0f,
              2.0f,   5.0f,   2.0f,   8.0f,  -1.0f,
             -3.0f,  11.0f,  -2.0f,  -5.0f,   5.0f,
             -2.5f,  22.5f,   3.5f, -11.0f,   9.0f, };
        float[] expected5 = new float[] {
              1.0f,   0.0f,   2.0f,   1.0f,   3.0f,
              0.0f,   0.0f,   1.0f,   0.0f,   3.0f,
             -4.0f,   0.0f,   3.0f,  -2.0f,   1.0f,
              2.0f,  -5.0f,  -4.0f,  -1.0f,   3.0f, };

        float[] c1 = c.clone();
        blas.ssymm("L", "U", m, n, 1.0f, aL, m, b, m, 2.0f, c1, m);
        assertArrayEquals(expected1, c1, sepsilon);

        float[] c2 = c.clone();
        blas.ssymm("L", "L", m, n, 1.0f, aL, m, b, m, 2.0f, c2, m);
        assertArrayEquals(expected1, c2, sepsilon);

        float[] c3 = c.clone();
        blas.ssymm("R", "U", m, n, 1.0f, aR, n, b, m, 2.0f, c3, m);
        assertArrayEquals(expected2, c3, sepsilon);

        float[] c4 = c.clone();
        blas.ssymm("R", "L", m, n, 1.0f, aR, n, b, m, 2.0f, c4, m);
        assertArrayEquals(expected2, c4, sepsilon);

        float[] c5 = c.clone();
        blas.ssymm("L", "U", m, n, 1.0f, aL, m, b, m, 0.0f, c5, m);
        assertArrayEquals(expected3, c5, sepsilon);

        float[] c6 = c.clone();
        blas.ssymm("L", "L", m, n, 1.0f, aL, m, b, m, 0.0f, c6, m);
        assertArrayEquals(expected3, c6, sepsilon);

        float[] c7 = c.clone();
        blas.ssymm("R", "U", m, n, 1.0f, aR, n, b, m, 0.0f, c7, m);
        assertArrayEquals(expected4, c7, sepsilon);

        float[] c8 = c.clone();
        blas.ssymm("R", "L", m, n, 1.0f, aR, n, b, m, 0.0f, c8, m);
        assertArrayEquals(expected4, c8, sepsilon);

        float[] c9 = c.clone();
        blas.ssymm("L", "U", m, n, 0.0f, aL, m, b, m, 1.0f, c9, m);
        assertArrayEquals(expected5, c9, sepsilon);

        float[] c10 = c.clone();
        blas.ssymm("L", "L", m, n, 0.0f, aL, m, b, m, 1.0f, c10, m);
        assertArrayEquals(expected5, c10, sepsilon);

        float[] c11 = c.clone();
        blas.ssymm("R", "U", m, n, 0.0f, aR, n, b, m, 1.0f, c11, m);
        assertArrayEquals(expected5, c11, sepsilon);

        float[] c12 = c.clone();
        blas.ssymm("R", "L", m, n, 0.0f, aR, n, b, m, 1.0f, c12, m);
        assertArrayEquals(expected5, c12, sepsilon);
    }
}
