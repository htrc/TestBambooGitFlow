import org.mashupbots.socko.routes._
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.webserver.WebServer
import org.mashupbots.socko.webserver.WebServerConfig

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ExtensionId
import akka.actor.ExtensionIdProvider
import akka.actor.ExtendedActorSystem

object MyWebServerConfig extends ExtensionId[WebServerConfig] with ExtensionIdProvider {
  override def lookup = MyWebServerConfig
  override def createExtension(system: ExtendedActorSystem) =
    new WebServerConfig(system.settings.config, "config")
}

object Main extends App {
  val actorSystem = ActorSystem("AkkaConfigActorSystem")

  val routes = Routes({
    case GET(request) => {
      actorSystem.actorOf(Props[HelloHandler]) ! request
    }
  })

  val myWebServerConfig = MyWebServerConfig(actorSystem)
  val webServer = new WebServer(myWebServerConfig, routes, actorSystem)
  webServer.start()

  Runtime.getRuntime.addShutdownHook(new Thread {
    override def run { webServer.stop() }
  })
}
