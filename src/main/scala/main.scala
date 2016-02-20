import org.mashupbots.socko.routes._
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.webserver.WebServer
import org.mashupbots.socko.webserver.WebServerConfig

import akka.actor.ActorSystem
import akka.actor.Props

object Main extends App {
  val actorSystem = ActorSystem("HelloExampleActorSystem")

  val routes = Routes({
    case GET(request) => {
      actorSystem.actorOf(Props[HelloHandler]) ! request
    }
  })

  val webServer = new WebServer(WebServerConfig(), routes, actorSystem)
  webServer.start()

  Runtime.getRuntime.addShutdownHook(new Thread {
    override def run { webServer.stop() }
  })
}
