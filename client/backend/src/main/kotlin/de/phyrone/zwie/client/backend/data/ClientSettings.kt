package de.phyrone.zwie.client.backend.data

data class ClientSettings(
    val test: String
){
    companion object{
        val default = ClientSettings(
            test = "test"
        )
    }
}