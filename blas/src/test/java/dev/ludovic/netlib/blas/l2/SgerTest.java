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

public class SgerTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int m = 5, n = 4;
        float alpha = 0.1f;
        float[] x = new float[] {
            1.0f, 2.0f, 2.1f,  4.0f, 5.0f };
        float[] y = new float[] {
            3.0f, 4.0f, 5.1f, -1.0f };
        float[] a = new float[] {
            1.0f, 2.0f, 3.0f, 4.0f, 5.0f,
            2.0f, 2.0f, 3.0f, 4.0f, 5.0f,
            3.0f, 3.0f, 3.0f, 4.0f, 5.0f,
            4.0f, 4.0f, 4.0f, 4.0f, 4.0f };
        float[] expected = new float[] {
             1.3f,  2.6f,  3.63f,  5.2f,  6.5f,
             2.4f,  2.8f,  3.84f,  5.6f,  7.0f,
            3.51f, 4.02f, 4.071f, 6.04f, 7.55f,
             3.9f,  3.8f,  3.79f,  3.6f,  3.5f };

        float[] a1 = a.clone();
        blas.sger(m, n, alpha, x, 1, y, 1, a1, m);
        assertArrayEquals(expected, a1, sepsilon);
    }
}
