import akka.actor.ActorSystem
import akka.actor.Status.Success
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object WebServer extends App {

  implicit val system: ActorSystem = ActorSystem("HelloWorldSample")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executor: ExecutionContext = system.dispatcher


  val host = "0.0.0.0"
  val port = 9000


  def route = path("hello") {
    get {
      complete("Hello world, what's your name?")
    }
    post {
      complete("Welcome to the system, %username%")
    }
  }

  val bindingFuture = Http().bindAndHandle(route, host, port)
  bindingFuture
    .onComplete {
      case util.Success(serverBinding)
      => println(s"Server is listening to: ${host +"/" +port}")
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