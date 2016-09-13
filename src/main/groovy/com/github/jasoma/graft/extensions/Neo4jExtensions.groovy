package com.github.jasoma.graft.extensions

import org.codehaus.groovy.runtime.m12n.SimpleExtensionModule

/**
 * Registration module for extensions to the driver and embedded database classes.
 */
class Neo4jExtensions extends SimpleExtensionModule {

    /**
     * Default constructor for registration.
     */
    Neo4jExtensions() {
        super("neo4j-extensions", "3.0.4")
    }

    @Override
    List<Class> getInstanceMethodsExtensionClasses() {
        return [DriverExtensions, EmbeddedExtensions]
    }

    @Override
    List<Class> getStaticMethodsExtensionClasses() {
        return []
    }
}
