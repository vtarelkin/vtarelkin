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
  implicit val executor: ExecutionContext = system.dispatcher

  val host = "0.0.0.0"
  val port = 9000
  val route = ApiRouter.route

  val bindingFuture = Http().bindAndHandle(route, host, port)
  bindingFuture
    .onComplete {
      case util.Success(serverBinding)
      => println(s"Server is listening on: ${host + "/" + port}")
      case scala.util.Failure(error: Error)
      => println(s"error: ${error.getMessage}")
    }
}