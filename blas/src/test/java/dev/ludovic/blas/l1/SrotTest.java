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

public class SrotTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int n = 9;
        float[] x = new float[] {
            1.0f, 0.0f, -2.0f, 3.0f, 1.0f, 0.0f, -2.0f, 3.0f, 3.0f };
        float[] y = new float[] {
            2.0f, 1.0f,  0.0f, 0.0f, 2.0f, 1.0f,  0.0f, 0.0f, 0.0f };
        float c = 2.0f;
        float s = 3.0f;

        float[] xexpected = new float[] {
            8.0f, 3.0f, -4.0f,  6.0f, 8.0f, 3.0f, -4.0f,  6.0f,  6.0f };
        float[] yexpected = new float[] {
            1.0f, 2.0f,  6.0f, -9.0f, 1.0f, 2.0f,  6.0f, -9.0f, -9.0f };

        float[] x1 = x.clone();
        float[] y1 = y.clone();
        blas.srot(n, x1, 1, y1, 1, c, s);
        assertArrayEquals(xexpected, x1, sepsilon);
        assertArrayEquals(yexpected, y1, sepsilon);
    }
}
