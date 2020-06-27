package net.de1mos.alfabattle.atmnumber1.proxy

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

data class PostAddress(val city: String, val location: String, val mode: String)
data class Coordinates(val latitude: String?, val longitude: String?)
data class Services(val payments: String?)
data class AtmDetails(val address: PostAddress, val deviceId: Int, val coordinates: Coordinates, val services: Services, val availablePaymentSystems: List<String>)

data class BankAtmDetails(val atms: List<AtmDetails>)
data class Error(val code: Int, val message: String)
data class AtmResponse(val data: BankAtmDetails, val error: Error?, val success: Boolean?, val total: Int?)

//data class AtmStatus(val )
//data class BankAtmStatus(val atms: List<AtmStatus>)
//data class AtmStatusResponse(val data: BankAtmStatus, val error: Error?, val success: Boolean?, val total: Int?)

@Service
class AtmProxy(private val restTemplate: RestTemplate,
               @Value("\${alfa.client.id}") val client_id: String,
               @Value("\${alfa.atms.url}") val atmUrl: String,
               @Value("\${alfa.atms.ulr.status}") val atmStatusUrl: String) {

    fun getAtms(): AtmResponse {
        val headers = HttpHeaders()
        headers.set("x-ibm-client-id", client_id)
        val atms = restTemplate.exchange(atmUrl, HttpMethod.GET,
                HttpEntity<Any>(headers),
                AtmResponse::class.java)
        return atms.body!!  // Once this should be checked for errors
    }

//    fun getAtmsStatus(): AtmStatusResponse {
//
//    }
}