package ru.sber.qa

import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class CertificateTest {
    // given
    private val certificateRequest = mockk<CertificateRequest>()
    private val processedBy = 10L
    private val data = Random.nextBytes(100)
    private val certificate = Certificate(certificateRequest, processedBy, data)



    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getCertificateRequest() {
        assertEquals(certificateRequest, certificate.certificateRequest)
    }

    @Test
    fun getProcessedBy() {
        assertEquals(processedBy, certificate.processedBy)
    }

    @Test
    fun getData() {
        assertEquals(data, certificate.data)
    }
}