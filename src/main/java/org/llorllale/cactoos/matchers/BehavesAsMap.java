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

import java.util.Map;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher for collection.
 * @param <K> Type of key
 * @param <V> Type of value
 * @since 0.24
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class BehavesAsMap<K, V> extends TypeSafeMatcher<Map<K, V>> {

    /**
     * Sample key.
     */
    private final K key;

    /**
     * Sample value.
     */
    private final V value;

    /**
     * Ctor.
     * @param akey Sample key
     * @param val Sample value
     */
    public BehavesAsMap(final K akey, final V val) {
        super();
        this.key = akey;
        this.value = val;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean matchesSafely(final Map<K, V> map) {
        new Assertion<>(
            "Must contain the key/value entry",
            map,
            new HasEntry<>(this.key, this.value)
        ).affirm();
        new Assertion<>(
            "Must contain the key in #keySet()",
            map.keySet(),
            new HasValues<>(this.key)
        ).affirm();
        new Assertion<>(
            "Must contain the value in #values()",
            map.values(),
            new HasValues<>(this.value)
        ).affirm();
        return true;
    }

    @Override
    public void describeTo(final Description desc) {
        desc.appendText("not a valid map");
    }
}
