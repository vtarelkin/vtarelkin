import akka.http.scaladsl.server.Directives._


object UtilityRouter {


  val route = path("index.js") {
    getFromResourceDirectory("templates")
  } ~
    path("styles.css") {
      getFromResourceDirectory("templates")
    }
}
