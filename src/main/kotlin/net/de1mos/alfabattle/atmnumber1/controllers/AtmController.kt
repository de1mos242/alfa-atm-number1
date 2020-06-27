package net.de1mos.alfabattle.atmnumber1.controllers

import net.de1mos.alfabattle.atmnumber1.services.AtmService
import net.de1mos.alfabattle.atmnumber1.web.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atms")
class AtmController(private val atmService: AtmService) {

    @GetMapping("/{deviceId}")
    fun getDeviceId(@PathVariable("deviceId") deviceId: Int): ResponseEntity<Any> {
        val atm = atmService.getAtmById(deviceId)
        return if (atm.isPresent) {
            ResponseEntity.ok(atm)
        } else {
            ResponseEntity(ErrorResponse("atm not found"), HttpStatus.NOT_FOUND)
        }
    }
}