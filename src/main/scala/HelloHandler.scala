import org.mashupbots.socko.events.HttpRequestEvent
import akka.actor.Actor
import java.util.Date
import buildinfo.BuildInfo._

/**
* Hello processor writes a greeting and stops.
*/
class HelloHandler extends Actor {
    def receive = {
      case event: HttpRequestEvent =>
        val sb = new StringBuilder()
        sb.append(s"$name $version").append("\n")
        sb.append(s"SBT : $sbtVersion").append("\n")
        sb.append(s"Scala: $scalaVersion").append("\n")
        sb.append(s"SHA $gitSha on branch $gitBranch with version $gitVersion").append("\n")
        sb.append(s"Built on: $builtAtString").append("\n")
        sb.append(s"\nA friendly message\n")

        event.response.write(sb.toString)
        context.stop(self)
    }
}
