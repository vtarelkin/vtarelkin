import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import spray.json.{DefaultJsonProtocol, JsValue}

object ApiRouter extends SprayJsonSupport with DefaultJsonProtocol {

    val route = getFromResourceDirectory("templates") ~ path("hello") {
      get {
        {
          getFromResource("templates/index.html")
        }
      } ~
        post {
          {
            entity(as[JsValue]) {
              jsonNameValue =>
                complete(s"<div>Welcome to the system, ${jsonNameValue.asJsObject.fields("name").convertTo[String]}</div>")
            }
          }
        }
    }
  }