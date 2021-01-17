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

public class SspmvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int n = 4;
        float[] aU = new float[] {
             3.0f,
            -2.0f, -8.0f,
             2.0f,  4.0f, -3.0f,
            -4.0f,  7.0f, -3.0f, 0.0f };
        float[] aL = new float[] {
             3.0f, -2.0f,  2.0f, -4.0f,
                   -8.0f,  4.0f,  7.0f,
                          -3.0f, -3.0f,
                                  0.0f };
        float[] x = new float[] {
             5.0f, 2.0f, -1.0f, -9.0f };
        float[] y = new float[] {
            -3.0f, 6.0f, -8.0f, -3.0f };
        float[] expected1 = new float[] {
            42.0f, -87.0f, 40.0f, -6.0f };
        float[] expected2 = new float[] {
            19.5f, -40.5f, 16.0f, -4.5f };
        float[] expected3 = new float[] {
            -25.5f, 52.5f, -32.0f, -1.5f };
        float[] expected4 = new float[] {
            -3.0f, 6.0f, -8.0f, -3.0f };
        float[] expected5 = new float[] {
            43.5f, -90.0f, 44.0f, -4.5f };
        float[] expected6 = new float[] {
            46.5f, -96.0f, 52.0f, -1.5f };
        float[] expected7 = new float[] {
            45.0f, -93.0f, 48.0f, -3.0f };

        float[] y1 = y.clone();
        blas.sspmv("U", n,  1.0f, aU, x, 1,  1.0f, y1, 1);
        assertArrayEquals(expected1, y1, sepsilon);

        float[] y2 = y.clone();
        blas.sspmv("U", n,  0.5f, aU, x, 1,  1.0f, y2, 1);
        assertArrayEquals(expected2, y2, sepsilon);

        float[] y3 = y.clone();
        blas.sspmv("U", n, -0.5f, aU, x, 1,  1.0f, y3, 1);
        assertArrayEquals(expected3, y3, sepsilon);

        float[] y4 = y.clone();
        blas.sspmv("U", n,  0.0f, aU, x, 1,  1.0f, y4, 1);
        assertArrayEquals(expected4, y4, sepsilon);

        float[] y5 = y.clone();
        blas.sspmv("U", n,  1.0f, aU, x, 1,  0.5f, y5, 1);
        assertArrayEquals(expected5, y5, sepsilon);

        float[] y6 = y.clone();
        blas.sspmv("U", n,  1.0f, aU, x, 1, -0.5f, y6, 1);
        assertArrayEquals(expected6, y6, sepsilon);

        float[] y7 = y.clone();
        blas.sspmv("U", n,  1.0f, aU, x, 1,  0.0f, y7, 1);
        assertArrayEquals(expected7, y7, sepsilon);

        float[] y8 = y.clone();
        blas.sspmv("L", n,  1.0f, aL, x, 1,  1.0f, y8, 1);
        assertArrayEquals(expected1, y8, sepsilon);

        float[] y9 = y.clone();
        blas.sspmv("L", n,  0.5f, aL, x, 1,  1.0f, y9, 1);
        assertArrayEquals(expected2, y9, sepsilon);

        float[] y10 = y.clone();
        blas.sspmv("L", n, -0.5f, aL, x, 1,  1.0f, y10, 1);
        assertArrayEquals(expected3, y10, sepsilon);

        float[] y11 = y.clone();
        blas.sspmv("L", n,  0.0f, aL, x, 1,  1.0f, y11, 1);
        assertArrayEquals(expected4, y11, sepsilon);

        float[] y12 = y.clone();
        blas.sspmv("L", n,  1.0f, aL, x, 1,  0.5f, y12, 1);
        assertArrayEquals(expected5, y12, sepsilon);

        float[] y13 = y.clone();
        blas.sspmv("L", n,  1.0f, aL, x, 1, -0.5f, y13, 1);
        assertArrayEquals(expected6, y13, sepsilon);

        float[] y14 = y.clone();
        blas.sspmv("L", n,  1.0f, aL, x, 1,  0.0f, y14, 1);
        assertArrayEquals(expected7, y14, sepsilon);
    }
}
