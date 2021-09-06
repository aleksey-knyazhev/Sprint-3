package ru.sber.qa

import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.random.Random

internal class ScannerTest {

    @BeforeEach
    fun setUp() {
        mockkObject(Random)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getScanData() {
        // given
        val data = Random.nextBytes(100)

        // when
        every { Random.nextLong(5000L, 15000L) } returns 10_000L
        every { Random.nextBytes(100) } returns data

        // then
        assertDoesNotThrow { Scanner.getScanData() }
        assertEquals(data, Scanner.getScanData())
    }

    @Test
    fun throwScanTimeoutException() {
        // when
        every { Random.nextLong(5000L, 15000L) } returns 10_001L

        // then
        assertThrows(ScanTimeoutException::class.java) { Scanner.getScanData() }
    }
}