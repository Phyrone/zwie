package de.phyrone.zwie.client.backend.data

data class ClientLocalData(
    val settings: ClientSettings,
    val servers: List<ServerConnectionData>
){
    companion object{
        val default = ClientLocalData(
            settings = ClientSettings.default,
            servers = emptyList()
        )
    }
}