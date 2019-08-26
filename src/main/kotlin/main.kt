@file:JvmName("Main")

import com.unboundid.ldap.listener.InMemoryDirectoryServer
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import com.unboundid.ldap.listener.InMemoryListenerConfig

fun main() {
    val config = InMemoryDirectoryServerConfig("dc=example,dc=com")
    config.addAdditionalBindCredentials("cn=Manager,ou=people,dc=example,dc=com", "password")
    config.setListenerConfigs(InMemoryListenerConfig.createLDAPConfig("default", 10389))
    config.setSchema(null)

    val ds = InMemoryDirectoryServer(config);
    ds.importFromLDIF(true, InMemoryDirectoryServer::class.java.getResource("/example.ldif").getFile())
    ds.startListening()
    println("Ldap server running on port ${ds.listenPort}")

    Runtime.getRuntime().addShutdownHook(Thread { ds.shutDown(true) })
}
