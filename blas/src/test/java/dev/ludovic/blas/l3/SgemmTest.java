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

public class SgemmTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int m = 5;
        int n = 4;
        int k = 4;
        float[] a = new float[] {
            0.0f, 1.0f, 0.0f, 0.0f, 1.5f,
            2.0f, 0.0f, 1.0f, 0.0f, 1.5f,
            0.0f, 0.0f, 0.0f, 3.0f, 1.5f,
            0.0f, 0.0f, 0.0f, 3.0f, 1.5f };
        float[] aT = new float[] {
            0.0f, 2.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 3.0f, 3.0f,
            1.5f, 1.5f, 1.5f, 1.5f };
        float[] b = new float[] {
             1.0f, 0.0f,  0.0f,  5.0f,
             0.0f, 2.0f,  1.0f,  1.0f,
             0.0f, 5.0f,  2.0f, -2.0f,
            -1.0f, 3.0f, -1.0f, -2.0f };
        float[] bT = new float[] {
            1.0f, 0.0f,  0.0f, -1.0f,
            0.0f, 2.0f,  5.0f,  3.0f,
            0.0f, 1.0f,  2.0f, -1.0f,
            5.0f, 1.0f, -2.0f, -2.0f };
        float[] c = new float[] {
             1.0f,  0.0f,  2.0f,  1.0f, 3.0f,
             0.0f,  0.0f,  1.0f,  0.0f, 3.0f,
            -4.0f,  0.0f,  3.0f, -2.0f, 1.0f,
             2.0f, -5.0f, -4.0f, -1.0f, 3.0f };
        float[] expected1 = new float[] {
             2.0f,    1.0f,   4.0f,  17.0f, 15.0f,
             4.0f,    0.0f,   4.0f,   6.0f, 12.0f,
             2.0f,    0.0f,  11.0f,  -4.0f,  9.5f,
             10.0f, -11.0f,  -5.0f, -11.0f,  4.5f };
        float[] expected2 = new float[] {
             0.0f,  1.0f,  0.0f, 15.0f,  9.0f,
             4.0f,  0.0f,  2.0f,  6.0f,  6.0f,
            10.0f,  0.0f,  5.0f,  0.0f,  7.5f, 
             6.0f, -1.0f,  3.0f, -9.0f, -1.5f };
        float[] expected3 = new float[] {
             1.0f,  0.0f,  2.0f,  1.0f,  3.0f,
             0.0f,  0.0f,  1.0f,  0.0f,  3.0f,
            -4.0f,  0.0f,  3.0f, -2.0f,  1.0f,
             2.0f, -5.0f, -4.0f, -1.0f,  3.0f };

        float[] c1 = c.clone();
        blas.sgemm("N", "N", m, n, k, 1.0f, a, m, b, k, 2.0f, c1, m);
        assertArrayEquals(expected1, c1, sepsilon);

        float[] c2 = c.clone();
        blas.sgemm("N", "T", m, n, k, 1.0f, a, m, bT, n, 2.0f, c2, m);
        assertArrayEquals(expected1, c2, sepsilon);

        float[] c3 = c.clone();
        blas.sgemm("T", "N", m, n, k, 1.0f, aT, k, b, k, 2.0f, c3, m);
        assertArrayEquals(expected1, c3, sepsilon);

        float[] c4 = c.clone();
        blas.sgemm("T", "T", m, n, k, 1.0f, aT, k, bT, n, 2.0f, c4, m);
        assertArrayEquals(expected1, c4, sepsilon);

        float[] c5 = c.clone();
        blas.sgemm("N", "N", m, n, k, 1.0f, a, m, b, k, 0.0f, c5, m);
        assertArrayEquals(expected2, c5, sepsilon);

        float[] c6 = c.clone();
        blas.sgemm("N", "T", m, n, k, 1.0f, a, m, bT, n, 0.0f, c6, m);
        assertArrayEquals(expected2, c6, sepsilon);

        float[] c7 = c.clone();
        blas.sgemm("T", "N", m, n, k, 1.0f, aT, k, b, k, 0.0f, c7, m);
        assertArrayEquals(expected2, c7, sepsilon);

        float[] c8 = c.clone();
        blas.sgemm("T", "T", m, n, k, 1.0f, aT, k, bT, n, 0.0f, c8, m);
        assertArrayEquals(expected2, c8, sepsilon);

        float[] c9 = c.clone();
        blas.sgemm("N", "N", m, n, k, 0.0f, a, m, b, k, 1.0f, c9, m);
        assertArrayEquals(expected3, c9, sepsilon);

        float[] c10 = c.clone();
        blas.sgemm("N", "T", m, n, k, 0.0f, a, m, bT, n, 1.0f, c10, m);
        assertArrayEquals(expected3, c10, sepsilon);

        float[] c11 = c.clone();
        blas.sgemm("T", "N", m, n, k, 0.0f, aT, k, b, k, 1.0f, c11, m);
        assertArrayEquals(expected3, c11, sepsilon);

        float[] c12 = c.clone();
        blas.sgemm("T", "T", m, n, k, 0.0f, aT, k, bT, n, 1.0f, c12, m);
        assertArrayEquals(expected3, c12, sepsilon);
    }
}
