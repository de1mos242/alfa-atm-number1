package net.de1mos.alfabattle.atmnumber1.web

data class ErrorResponse(val status: String)

data class GetAtmByIdResponse(val city:String,
                              val deviceId: Int,
                              val latitude: String?,
                              val location: String?,
                              val longitude: String?,
                              val payments: Boolean)

