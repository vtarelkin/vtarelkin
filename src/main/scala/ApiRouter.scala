import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._

object ApiRouter {
  val route = getFromResourceDirectory("templates") ~ path("hello") {
    get {
      {
        redirect("templates/index.html", StatusCodes.PermanentRedirect)
      }
    }~
      post {
        {
          complete("Welcome to the system, username")
        }
      }
  }
}
