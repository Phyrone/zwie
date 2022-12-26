package de.phyrone.zwie.server

import de.phyrone.zwie.server.module.CommonModule
import dev.onvoid.webrtc.CreateSessionDescriptionObserver
import dev.onvoid.webrtc.PeerConnectionFactory
import dev.onvoid.webrtc.RTCBundlePolicy
import dev.onvoid.webrtc.RTCConfiguration
import dev.onvoid.webrtc.RTCIceServer
import dev.onvoid.webrtc.RTCOfferOptions
import dev.onvoid.webrtc.RTCSdpType
import dev.onvoid.webrtc.RTCSessionDescription
import dev.onvoid.webrtc.media.audio.AudioPlayer
import dev.onvoid.webrtc.media.audio.AudioTrackSource
import kotlinx.coroutines.channels.consume
import org.koin.dsl.module
import java.nio.ByteBuffer

class WRTCGateModule : CommonModule {

    override suspend fun onEnable() {
        val factory = PeerConnectionFactory()
        val rtcConfiguration = RTCConfiguration()
        rtcConfiguration.iceServers = listOf(
            RTCIceServer().also { rtcIceServer ->
                rtcIceServer.urls = listOf("stun:stun.l.google.com:19302")
            }
        )

        val connection = factory.createPeerConnection(rtcConfiguration) { obs ->

        }

        val offer = connection.createOffer(RTCOfferOptions(), object : CreateSessionDescriptionObserver {
            override fun onSuccess(description: RTCSessionDescription) {
                description.sdp
                description.sdpType
                TODO("Not yet implemented")
            }

            override fun onFailure(error: String?) {
                TODO("Not yet implemented")
            }

        })

    }

    companion object {
        val module = module {
            single { PeerConnectionFactory() }
        }

    }

}