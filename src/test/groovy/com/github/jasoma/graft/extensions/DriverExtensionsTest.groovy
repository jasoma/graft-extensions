package com.github.jasoma.graft.extensions

import com.github.jasoma.graft.test.NeoServerConnection
import org.junit.ClassRule
import org.junit.Test

class DriverExtensionsTest {

    @ClassRule public static NeoServerConnection server = new NeoServerConnection()

    @Test
    def void testWithSession() {

    }

}
