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

public class SsyrTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        int n = 4;
        float alpha = 0.15f;
        float[] x = new float[] {
            0.0f, 2.7f, 3.5f, 2.1f };
        float[] a = new float[] {
            0.0f, 1.2f, 2.2f, 3.1f,
            1.2f, 3.2f, 5.3f, 4.6f,
            2.2f, 5.3f, 1.8f, 3.0f,
            3.1f, 4.6f, 3.0f, 0.8f };
        float[] expectedU = new float[] {
            0.0f,    1.2f,    2.2f,       3.1f,
            1.2f, 4.2935f,    5.3f,       4.6f,
            2.2f, 6.7175f, 3.6375f,       3.0f,
            3.1f, 5.4505f, 4.1025f, 1.4614999f };
        float[] expectedL = new float[] {
            0.0f,    1.2f,    2.2f,    3.1f,
            1.2f, 4.2935f, 6.7175f, 5.4505f,
            2.2f,    5.3f, 3.6375f, 4.1025f,
            3.1f,    4.6f,    3.0f, 1.4615f };

        float[] a1 = a.clone();
        blas.ssyr("U", n, alpha, x, 1, a1, n);
        assertArrayEquals(expectedU, a1, sepsilon);

        float[] a2 = a.clone();
        blas.ssyr("L", n, alpha, x, 1, a2, n);
        assertArrayEquals(expectedL, a2, sepsilon);
    }
}
