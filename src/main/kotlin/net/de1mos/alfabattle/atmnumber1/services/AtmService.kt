package net.de1mos.alfabattle.atmnumber1.services

import net.de1mos.alfabattle.atmnumber1.proxy.AtmProxy
import net.de1mos.alfabattle.atmnumber1.proxy.AtmResponse
import net.de1mos.alfabattle.atmnumber1.web.GetAtmByIdResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class AtmService(private val atmProxy: AtmProxy) {

    fun getAtmById(deviceId: Int): Optional<GetAtmByIdResponse> {
        val atms = getAtms()
        val atm = atms.data.atms.stream().filter() { it.deviceId == deviceId }.findFirst()
        return atm.map {
            val hasPayments = if (it.availablePaymentSystems.isNotEmpty()) "Y" else "N"
            GetAtmByIdResponse(it.address.city, it.deviceId, it.coordinates.latitude, it.address.location, it.coordinates.longitude, hasPayments)
        }
    }

    private fun getAtms(): AtmResponse {
        return atmProxy.getAtms()
    }
}