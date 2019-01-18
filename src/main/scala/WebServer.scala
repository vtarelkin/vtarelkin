import akka.actor.ActorSystem

import scala.concurrent.Future
//import akka.http.javadsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
//import akka.http.scaladsl.model.{HttpRequest, Uri}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods.GET
import akka.http.scaladsl.model._
import scala.concurrent.ExecutionContext

object WebServer extends App {

  implicit val system: ActorSystem = ActorSystem("HelloWorldSample")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executor: ExecutionContext = system.dispatcher


  val host = "0.0.0.0"
  val port = 9000


  val route = path("hello") {
    get {
      {
        complete("")
        getFromResource("templates/index.html")
      }} ~
        post {
          {
            complete("Welcome to the system, username")
          }
        }
    }


  //val bindingFuture = Http().bindAndHandle(staticResources, "localhost", 9000)


  //  val route = path("hello") {
  //    get {
  //  //    complete("Hello world, what's your name?")
  //      redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)
  //      getFromResourceDirectory("templates")
  //    } ~
  //    post {
  //      complete("Welcome to the system, username")
  //    }
  //  }


  //
  //  val requestHandler: HttpRequest => Future[HttpResponse] = {
  //    case HttpRequest(
  //    GET,
  //    Uri.Path("/hello"),
  //    _, // matches any headers
  //    _, // matches any HTTP entity (HTTP body)
  //    _  // matches any HTTP protocol
  //    ) => {
  //      val m = Marshal("Richard Imaoka")
  //      m.to[HttpResponse]
  //    }
  //  }

  //  val bindingFuture = Http().bindAndHandleAsync(requestHandler, "localhost", 9000)


  val bindingFuture = Http().bindAndHandle(route, host, port)
  bindingFuture
    .onComplete {
      case util.Success(serverBinding)
      => println(s"Server is listening to: ${host + "/" + port}")
      case scala.util.Failure(error: Error)
      => println(s"error: ${error.getMessage}")
    }


  //
  //  println(s"Server online at http://localhost:9000/\nPress RETURN to stop...")
  //  StdIn.readLine() // let it run until user presses return
  //  bindingFuture
  //    .flatMap(_.unbind()) // trigger unbinding from the port
  //    .onComplete(_ => system.terminate()) // and shutdown when done

}