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
        var inputString = arrayOf<String>("-o", "tests/res.txt", "-c", "40", "tests/input1.txt", "tests/input2.txt")
        TailLauncher.main(inputString)
        assertFileContent("tests/output1.txt", "tests/res.txt")

        inputString = arrayOf<String>("-o", "tests/res.txt", "-n", "6", "tests/input1.txt", "tests/input2.txt")
        TailLauncher.main(inputString)
        assertFileContent("tests/output2.txt", "tests/res.txt")


        inputString = arrayOf<String>("-o", "tests/res.txt", "tests/input1.txt", "tests/input2.txt")
        TailLauncher.main(inputString)
        assertFileContent("tests/output3.txt", "tests/res.txt")

        inputString = arrayOf<String>("-o", "tests/res.txt", "-n", "59", "tests/input2.txt")
        TailLauncher.main(inputString)
        assertFileContent("tests/output4.txt", "tests/res.txt")

        inputString = arrayOf<String>("-o", "tests/res.txt", "-c", "2200", "tests/input1.txt")
        TailLauncher.main(inputString)
        assertFileContent("tests/output5.txt", "tests/res.txt")

        try {
            inputString = arrayOf<String>("-o", "tests/res.txt", "-n", "-6", "tests/input1.txt", "tests/input2.txt")
            TailLauncher.main(inputString)
        } catch (e: IllegalArgumentException) {
        }

        try {
            inputString = arrayOf<String>("-o", "tests/res.txt", "-c", "-6", "tests/input1.txt", "tests/input2.txt")
            TailLauncher.main(inputString)
        } catch (e: IllegalArgumentException) {
        }

        try {
            inputString = arrayOf<String>("-o", "tests/res.txt", "-c", "40", "-n", "6", "tests/input1.txt", "tests/input2.txt")
            TailLauncher.main(inputString)
        } catch (e: IllegalArgumentException) {
        }

        try {
            inputString = arrayOf<String>("-o", "tests/res.txt", "-c", "40", "tests/inpt1.txt", "tests/input2.txt")
            TailLauncher.main(inputString)
        } catch (e: IllegalArgumentException) {
        }
    }


}