package ru.sber.qa

import io.mockk.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateRequestTest {
    // given
    private val certificateType = mockk<CertificateType>()
    private val employeeNumber = 10L
    private val certificateRequest = CertificateRequest(employeeNumber, certificateType)

    @Test
    fun process() {
        // given
        val hrEmployeeNumber = 20L
        val data = Random.nextBytes(100) // scanned data
        mockkConstructor(Certificate::class)
        mockkObject(Scanner)

        // when
        every { Scanner.getScanData() } returns data
        val certificate = certificateRequest.process(hrEmployeeNumber)

        // then
        assertEquals(hrEmployeeNumber, certificate.processedBy)
        assertEquals(data, certificate.data)
        assertEquals(certificateRequest, certificate.certificateRequest)

        unmockkAll()
    }

    @Test
    fun getEmployeeNumber() {
        // then
        assertEquals(employeeNumber, certificateRequest.employeeNumber)
    }

    @Test
    fun getCertificateType() {
        // then
        assertEquals(certificateType, certificateRequest.certificateType)
    }
}