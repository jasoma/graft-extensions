package com.github.jasoma.graft.extensions

import org.neo4j.driver.v1.Driver

/**
 * Extension methods for making the {@link Driver} class more groovy.
 */
class DriverExtensions {

    /**
     * Creates a new session and passes it to the provided closure. Session is guaranteed to be
     * closed when the closure exits. Example:
     *
     * <pre><code>
     * Driver driver = ...
     * driver.withSession { session ->
     *     ...
     * }
     * </code></pre>
     *
     * @param self the instance of the driver.
     * @param closure the closure to run with the session.
     */
    public static void withSession(Driver self, Closure closure) {
        def session = self.session()
        try {
            closure(session)
        }
        finally {
            session.close()
        }
    }

}
