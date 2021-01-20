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

public class SgemvTest extends BLASTest {

    @ParameterizedTest
    @MethodSource("BLASImplementations")
    void testSanity(BLAS blas) {
        float[] expected, sYcopy;

        blas.sgemv("N", M, N, 1.0f, sgeA, M, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N, 1.0f, sgeA, M, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        blas.sgemv("T", N, M, 1.0f, sgeAT, N, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.sgemv("T", N, M, 1.0f, sgeAT, N, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        blas.sgemv("N", M, N, 2.0f, sgeA, M, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.sgemv("N", M, N, 2.0f, sgeA, M, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);

        blas.sgemv("T", N, M, 2.0f, sgeAT, N, sX, 1, 2.0f, expected = sY.clone(), 1);
        blas.sgemv("T", N, M, 2.0f, sgeAT, N, sX, 1, 2.0f, sYcopy = sY.clone(), 1);
        assertArrayEquals(expected, sYcopy, sepsilon);
    }
}
