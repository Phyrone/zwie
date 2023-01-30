package de.phyrone.zwie.shared.crypt.import.pgpjs

import global.GPGJS.BasePublicKeyPacket
import global.GPGJS.GenerateKeyOptions
import global.GPGJS.enums.packet


typealias AnyKeyPacket = BasePublicKeyPacket

typealias AllowedPackets = Map<packet, Any?>

typealias PacketList<T> = Array<T>
typealias KeyOptions = GenerateKeyOptions