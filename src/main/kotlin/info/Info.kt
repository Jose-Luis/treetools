package info

import com.beust.klaxon.Klaxon
import info.entities.App
import info.entities.Commands
import info.entities.Root
import info.entities.Server
import tools.SSHClient
import tools.DiffCache
import java.io.File

class Info(file: File) {

    private val root: Root

    val workspace: File
    val projects: ProjectTree
    val cacheDir: File
    val commands: Commands
    val SSHClient: SSHClient
    val servers: Map<String, Server>
    val apps: Map<String, App>

    init {
        root = Klaxon().parse<Root>(file.inputStream())!!
        projects = ProjectTree(root)
        commands = root.commands
        workspace = File(root.`workspace-dir`).absoluteFile
        servers = root.servers.associateBy { it.name }
        apps = root.apps.associateBy { it.name }
        SSHClient = SSHClient(root.`backup-dir`)
        cacheDir = File(root.`cache-dir`).absoluteFile
    }
}
