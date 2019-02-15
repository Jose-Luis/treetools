package commands

import info.Info
import kotlin.system.exitProcess

class Logger(
    val projectname: String,
    val server: String,
    val regex: String?,
    val info: Info
) {
    fun log() {
        val server = info.servers[server]!!
        val app = info.apps[projectname]!!
        System.out.println("===LOG")
        info.SSHClient.showLog(server, app, regex)
        exitProcess(0)
    }
}