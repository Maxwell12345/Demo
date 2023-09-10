package mil.ship.detection

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.Netty
import io.ktor.server.routing.*
import kotlinx.html.*
import mil.ship.detection.ml.model

fun HTML.index() {
    head {
        title("Ship Detection")
    }
    body {
        div {
            +"Ship Detection App"
        }
        div {
            id = "root"
        }
        script(src = "/static/Ship_Detection.js") {}
    }
}

fun main() {
    var testModel = model()
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}