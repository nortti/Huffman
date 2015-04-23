package huffman.io;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Checks the functionality of the ArgsParser class.
 */
public class ArgsParserTest {

    ArgsParser argsParser = new ArgsParser();

    /**
     * Sets up a non-empty file that is used in some tests.
     */
    @Before
    public void setUp() throws IOException {
        PrintWriter writer = new PrintWriter("validfile");
        writer.println("test");
        writer.close();
    }

    /**
     * Checks that the parsing fails when the number of args differs from 2. Also makes sure that
     * the error message is correct.
     */
    @Test
    public void worksCorrectlyWithWrongNumberOfArgs() {
        assertFalse(argsParser.parse(new String[] {}));
        assertTrue(argsParser.getErrorMessage().equals("Wrong number of args, "
                                                       + "expected: 2, was: 0"));
        assertFalse(argsParser.parse(new String[] { "1", "2", "3"}));
        assertTrue(argsParser.getErrorMessage().equals("Wrong number of args, "
                                                       + "expected: 2, was: 3"));
    }

    /**
     * Checks that the parsing fails when the first argument isn't "c" or "d". Also makes sure that
     * the error message is correct.
     */
    @Test
    public void worksCorrectlyWhenFirstArgIsntRight() {
        assertFalse(argsParser.parse(new String[] { "a", "validfile" }));
        assertTrue(argsParser.getErrorMessage().equals("Incorrect argument #1, expected: "
                                                       + "'c' or 'd', was: 'a'"));
    }

    /**
     * Checks that the parsing fails when the first argument is correct, but a file is not found
     * in the path given in the second argument. Also makes sure that the error message is correct.
     */
    @Test
    public void worksCorrectlyWhenFileDoesntExist() {
        assertFalse(argsParser.parse(new String[] { "c", "nothing" }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: "
                                                       + "'nothing' does not exist"));
    }

    /**
     * Checks that the parsing fails when the first argument is correct, but the file found in the
     * path given in the second argument is a folder. Also makes sure that the error message is
     * correct.
     */
    @Test
    public void worksCorrectlyWhenFileIsADirectory() {
        assertFalse(argsParser.parse(new String[] { "c", "." }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: '.' is a directory"));
    }

    /**
     * Checks that the parsing fails when the first argument is correct, but the file found in the
     * path given in the second argument is empty. Also makes sure that the error message is
     * correct.
     */
    @Test
    public void worksCorrectlyWhenFileIsEmpty() throws IOException {
        File file = new File("empty");
        file.createNewFile();
        assertFalse(argsParser.parse(new String[] { "c", "empty" }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: 'empty' "
                                                       + "empty files can not be converted"));
        file.delete();
    }

    /**
     * Checks that the parsing succeeds when the arguments are correct, and that the arguments
     * were parsed correctly.
     */
    @Test
    public void worksWhenBothArgsAreValid() {
        assertTrue(argsParser.parse(new String[] { "c", "validfile" }));
        assertTrue(argsParser.getIsCompressing() == true);
        assertTrue(argsParser.getFile().getName() == "validfile");
    }

    /**
     * Afterwards, removes the file used in some tests.
     */
    @After
    public void tearDown() throws IOException {
        File file = new File("validfile");
        file.delete();
    }
}
