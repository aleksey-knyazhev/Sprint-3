package ru.sber.io

import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Реализовать методы архивации и разархивации файла.
 * Для реализиации использовать ZipInputStream и ZipOutputStream.
 */
class Archivator {

    /**
     * Метод, который архивирует файл logfile.log в архив logfile.zip.
     * Архив должен располагаться в том же каталоге, что и исходной файл.
     */
    fun zipLogfile() {
        val sourceFileName = "logfile.log"
        val sourceFile = File("io/$sourceFileName")
        val targetFile = File("io/logfile.zip")

        sourceFile.inputStream().use { fileInputStream ->
            val readByte = fileInputStream.readAllBytes()
            targetFile.outputStream().use {
                val zipEntry = ZipEntry(sourceFileName)
                val zos = ZipOutputStream(it)
                zos.putNextEntry(zipEntry)
                zos.write(readByte)
                zos.closeEntry()
                zos.close() // Правильно ли я закрыл здесь все необходимые потоки?
            }
        }
    }

    /**
     * Метод, который извлекает файл из архива.
     * Извлечь из архива logfile.zip файл и сохарнить его в том же каталоге с именем unzippedLogfile.log
     */
    fun unzipLogfile() {
        val sourceFile = File("io/logfile.zip")
        val targetFile = File("io/unzippedLogfile.log")

        sourceFile.inputStream().use { fileInputStream ->
            targetFile.outputStream().use {
                val zis = ZipInputStream(fileInputStream)
                zis.nextEntry
                it.write(zis.readAllBytes())
                it.flush()
                zis.closeEntry()
                it.close() // Правильно ли я закрыл здесь все необходимые потоки?
            }
        }
    }
}