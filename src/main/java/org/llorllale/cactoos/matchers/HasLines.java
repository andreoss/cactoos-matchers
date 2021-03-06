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

import java.util.Collection;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.Text;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.list.ListOf;
import org.cactoos.text.Split;

/**
 * Allows to check that text has lines considering platform-dependent line
 *  separator.
 *
 * @since 1.0.0
 */
public final class HasLines extends MatcherEnvelope<String> {

    /**
     * Ctor.
     * @param lns The expected lines to be present.
     */
    public HasLines(final Text... lns) {
        this(new Mapped<>(Text::asString, lns));
    }

    /**
     * Ctor.
     * @param lns The expected lines to be present.
     */
    public HasLines(final String... lns) {
        this(new IterableOf<>(lns));
    }

    /**
     * Ctor.
     * @param lns The expected lines to be present.
     */
    public HasLines(final Iterable<String> lns) {
        this(Collection::containsAll, System::lineSeparator, new ListOf<>(lns));
    }

    /**
     * Ctor.
     * @param fnc The function to match the actual/expected lines.
     * @param sep OS dependent line separator.
     * @param lns The expected lines to be present.
     */
    public HasLines(
        final BiFunc<? super Collection<String>, ? super Collection<String>, Boolean> fnc,
        final Scalar<String> sep,
        final Collection<String> lns
    ) {
        this(
            fnc,
            lns,
            text -> new ListOf<>(
                new Mapped<>(Text::asString, new Split(text, sep::value))
            )
        );
    }

    /**
     * Ctor.
     * @param match The function to match the actual/expected lines.
     * @param expected The expected lines to be present.
     * @param split The function to split the text which came for testing.
     */
    private HasLines(
        final BiFunc<? super Collection<String>, ? super Collection<String>, Boolean> match,
        final Collection<String> expected,
        final Func<? super String, ? extends Collection<String>> split
    ) {
        super(
            new MatcherOf<>(
                actual -> match.apply(split.apply(actual), expected),
                desc -> desc
                    .appendText("lines are ")
                    .appendValue(expected),
                (actual, desc) -> desc
                    .appendText("lines were ")
                    .appendValue(split.apply(actual))
            )
        );
    }

}
