import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{StatusCodes}
import akka.http.scaladsl.server.Directives._
import spray.json.{DefaultJsonProtocol, JsValue}

object ApiRouter extends SprayJsonSupport with DefaultJsonProtocol {


    val route = getFromResourceDirectory("templates") ~ path("hello") {
      get {
        {
          redirect("templates/index.html", StatusCodes.PermanentRedirect)
        }
      } ~
        post {
          {
            entity(as[JsValue]) {
              jsonNameValue =>
                complete(s"Welcome to the system, ${jsonNameValue.asJsObject.fields("name").convertTo[String]}")
            }
          }
        }
    }
  }