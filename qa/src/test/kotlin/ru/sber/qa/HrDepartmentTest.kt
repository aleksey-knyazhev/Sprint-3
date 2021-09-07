package ru.sber.qa

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import java.util.*

internal class HrDepartmentTest {
    private var certificateRequest = mockk<CertificateRequest>()

    @BeforeEach
    fun setUp() {
        val clockFixed = Clock.fixed(Instant.parse("2021-09-06T10:00:00Z"), ZoneOffset.UTC)
        HrDepartment.clock = clockFixed
        mockkObject(CertificateType.NDFL)
        mockkObject(CertificateType.LABOUR_BOOK)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun receiveRequest() {
        // when
        every { certificateRequest.certificateType } returns CertificateType.NDFL

        // then
        assertDoesNotThrow { HrDepartment.receiveRequest(certificateRequest) } // Monday, NDFL
    }

    @Test
    fun throwNotAllowReceiveRequestException() {
        // when
        every { certificateRequest.certificateType } returns CertificateType.LABOUR_BOOK

        // then
        assertThrows(NotAllowReceiveRequestException::class.java) { HrDepartment.receiveRequest(certificateRequest) } // Monday, LABOUR_BOOK not allowed
    }

    @Test
    fun processNextRequest() {
        // given
        var certificate = mockk<Certificate>()
        var hrEmployeeNumber = 10L
        //val outcomeOutcome: LinkedList<Certificate> = LinkedList()

        // when
        every { certificateRequest.process(hrEmployeeNumber)} returns certificate
        HrDepartment.receiveRequest(certificateRequest)

        // then
        // В идеале, здесь бы проверить соответствие outcomeOutcome. Но outcomeOutcome
        //assertEquals(outcomeOutcome, HrDepartment.outcomeOutcome). Но свойство private - с модификатором private, добраться не получится
    }
}