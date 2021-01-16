/*
 * The MIT License (MIT)
 *
 * Copyright (c) for portions of project cactoos-matchers are held by
 * Yegor Bugayenko, 2017-2018, as part of project cactoos.
 * All other copyright for project cactoos-matchers are held by
 * George Aristy, 2018-2020.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.llorllale.cactoos.matchers;

import org.cactoos.iterable.IterableOfBooleans;
import org.cactoos.list.ListOf;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link HasSize}.
 *
 * @since 1.0.0
 * @checkstyle ClassDataAbstractionCoupling (2 lines)
 */
final class HasSizeTest {

    @Test
    void matchesIterableSize() {
        new Assertion<>(
            "matches iterable with given size",
            new HasSize(2),
            new Matches<>(new IterableOfBooleans(true, false))
        ).affirm();
    }

    @Test
    void matchesEmptyCollection() {
        new Assertion<>(
            "matches empty iterable if given size arg is 0",
            new HasSize(0),
            new Matches<>(new ListOf<>())
        ).affirm();
    }

    @Test
    void mismatches() {
        new Assertion<>(
            "mismatches empty iterable if given size arg is 2",
            new HasSize(2),
            new Mismatches<>(
                new ListOf<>(),
                "has size <2>",
                "has size <0>"
            )
        ).affirm();
    }
}
