package com.github.jasoma.graft.extensions

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Transaction

/**
 * Extension methods for making the {@link GraphDatabaseService} class more groovy.
 */
class EmbeddedExtensions {

    /**
     * Starts a new transaction and then runs the provided closure. The transaction is guaranteed to be closed when
     * the closure exits. The database instance itself is passed to the closure as an argument while the transaction
     * acts as the delegate allowing for easy access to the {@link org.neo4j.graphdb.Transaction#success()} or
     * {@link org.neo4j.graphdb.Transaction#failure()} methods. Example:
     *
     * <pre><code>
     *     db.withTransaction { db ->
     *          def results = db.execute("CREATE (n:TEST)")
     *          // ...
     *          if (condition) {
     *              success()
     *          }
     *          else {
     *              failure()
     *          }
     *     }
     * </code></pre>
     *
     * @param closure the closure to run with the transaction.
     */
    public def void withTransaction(GraphDatabaseService self,
                                    @DelegatesTo(Transaction)
                                    @ClosureParams(value=SimpleType.class, options="org.neo4j.graphdb.GraphDatabaseService") Closure closure) {
        def tx = self.beginTx()
        closure.delegate = tx
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        try {
            closure(self)
        }
        finally {
            tx.close()
        }
    }

}
