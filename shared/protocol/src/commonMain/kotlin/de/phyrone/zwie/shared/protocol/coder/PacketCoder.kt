package de.phyrone.zwie.shared.protocol.coder

interface PacketCoder<T> : PacketDecoder<T>, PacketEncoder<T>