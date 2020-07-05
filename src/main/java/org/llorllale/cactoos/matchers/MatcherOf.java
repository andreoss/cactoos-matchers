/*
 * The MIT License (MIT)
 *
 * Copyright (c) for portions of project cactoos-matchers are held by
 * Yegor Bugayenko, 2017-2018, as part of project cactoos.
 * All other copyright for project cactoos-matchers are held by
 * George Aristy, 2018.
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

import org.cactoos.Func;
import org.cactoos.Proc;
import org.cactoos.Text;
import org.cactoos.func.FuncOf;
import org.cactoos.text.FormattedText;
import org.cactoos.text.UncheckedText;

/**
 * Func as Matcher.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <T> Type of object to match
 * @since 0.12
 */
public final class MatcherOf<T> extends MatcherEnvelope<T> {

    /**
     * Ctor.
     * @param proc The func
     */
    public MatcherOf(final Proc<T> proc) {
        this(new FuncOf<>(proc, true));
    }

    /**
     * Ctor.
     * @param fnc The func
     */
    public MatcherOf(final Func<T, Boolean> fnc) {
        this(fnc, new UncheckedText(fnc.toString()));
    }

    /**
     * Ctor.
     * @param fnc The func
     * @param description The description
     */
    public MatcherOf(final Func<T, Boolean> fnc, final Text description) {
        super(
            fnc,
            desc -> desc.appendText(
                new FormattedText("\"%s\"", description).asString()
            ),
            (actual, desc) -> desc.appendValue(actual)
        );
    }
}
