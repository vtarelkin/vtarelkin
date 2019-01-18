import akka.http.scaladsl.server.Directives._

object ApiRouter {
  val route = path("hello") {
    get {
      {
        getFromResource("index.html")
      }
    }~
      post {
        {
          complete("Welcome to the system, username")
        }
      }
  }
}
