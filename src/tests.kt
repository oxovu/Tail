import org.junit.Test

import org.junit.Assert.*
import java.io.File


class test {

    private fun assertFileContent(nameOut: String, nameIn: String) {
        val fileExpected = File(nameOut)
        val fileOut = File(nameIn)
        val contentExpected = fileExpected.readLines()
        val contentOut = fileOut.readLines()
        assertEquals(contentOut, contentExpected)
    }

    @Test
    fun main() {
        var inputString = arrayOf<String>("-o", "C:/Users/vlada/Desktop/STail.txt", "-c", "5", "C:/Users/vlada/Desktop/Slava.txt", "C:/Users/vlada/Desktop/EndedFile.txt")
        TailLauncher.main(inputString)
        assertFileContent("C:/Users/vlada/Desktop/STail.txt", "C:/Users/vlada/Desktop/SlavaTail.txt")

        inputString = arrayOf<String>("-o", "C:/Users/vlada/Desktop/STail.txt", "-n", "4", "C:/Users/vlada/Desktop/Slava.txt", "C:/Users/vlada/Desktop/EndedFile.txt")
        TailLauncher.main(inputString)
        assertFileContent("C:/Users/vlada/Desktop/STail.txt", "C:/Users/vlada/Desktop/SlavaTail1.txt")

        inputString = arrayOf<String>("-o", "C:/Users/vlada/Desktop/STail.txt", "C:/Users/vlada/Desktop/Slava.txt", "C:/Users/vlada/Desktop/EndedFile.txt")
        TailLauncher.main(inputString)
        assertFileContent("C:/Users/vlada/Desktop/STail.txt", "C:/Users/vlada/Desktop/SlavaTail2.txt")
    }


}