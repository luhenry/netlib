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
 *
 * Please contact git@ludovic.dev or visit ludovic.dev if you need additional
 * information or have any questions.
 */

package dev.ludovic.netlib.blas;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
public class InstanceBuilderTest {

    @Test
    void testJavaBlas() {
        JavaBLAS javaBlas = JavaBLAS.getInstance();
        assertNotNull(javaBlas, "JavaBLAS instance should not be null");
        assertTrue(javaBlas instanceof JavaBLAS, "Instance should implement JavaBLAS interface");
    }

    @Test
    void testJavaBlasImplementation() {
        JavaBLAS javaBlas = JavaBLAS.getInstance();

        String[] fullVersion = System.getProperty("java.version").split("[+.\\-]+", 3);
        int major = Integer.parseInt(fullVersion[0]) > 1 ? Integer.parseInt(fullVersion[0]) : Integer.parseInt(fullVersion[1]);

        if (major >= 16) {
            // On Java 16+, it should be VectorBLAS (if available) or Java11BLAS (fallback)
            assertTrue(javaBlas instanceof Java11BLAS,
                String.format("On Java 16+, instance should be Java11BLAS or VectorBLAS (which extends Java11BLAS), but got %s", javaBlas.getClass().getName()));
        } else if (major >= 11) {
            // On Java 11-15, it should be Java11BLAS
            assertTrue(javaBlas instanceof Java11BLAS,
                String.format("On Java 11+, instance should be Java11BLAS, but got %s", javaBlas.getClass().getName()));
            assertFalse(javaBlas.getClass().getName().contains("VectorBLAS"),
                "On Java 11-15, instance should not be VectorBLAS");
        } else {
            // On Java 8, it should be Java8BLAS
            assertEquals(Java8BLAS.class, javaBlas.getClass(),
                "On Java 8, instance should be Java8BLAS");
        }
    }

    @Test
    void testJavaBlasSingleton() {
        JavaBLAS instance1 = JavaBLAS.getInstance();
        JavaBLAS instance2 = JavaBLAS.getInstance();
        assertSame(instance1, instance2, "JavaBLAS should return the same singleton instance");
    }
}
