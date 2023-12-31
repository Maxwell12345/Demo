import web.dom.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div")
    document.body.appendChild(container)

    val welcome = Welcome.create {
        name = "max"
    }
    createRoot(container).render(welcome)
}