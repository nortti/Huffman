package huffman.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;

public class ArgsParserTest {

    ArgsParser argsParser = new ArgsParser();

    @Before
    public void setUp() throws IOException {
        PrintWriter writer = new PrintWriter("validfile");
        writer.println("test");
        writer.close();
    }

    @Test
    public void worksCorrectlyWithWrongNumberOfArgs() {
        assertFalse(argsParser.parse(new String[] {}));
        assertTrue(argsParser.getErrorMessage().equals("Wrong number of args, expected: 2, was: 0"));
        assertFalse(argsParser.parse(new String[] { "1", "2", "3"}));
        assertTrue(argsParser.getErrorMessage().equals("Wrong number of args, expected: 2, was: 3"));
    }

    @Test
    public void worksCorrectlyWhenFirstArgIsntRight() {
        assertFalse(argsParser.parse(new String[] { "a", "validfile" }));
        assertTrue(argsParser.getErrorMessage().equals("Incorrect argument #1, expected: 'c' or 'd', was: 'a'"));
    }

    @Test
    public void worksCorrectlyWhenFileDoesntExist() {
        assertFalse(argsParser.parse(new String[] { "c", "nothing" }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: 'nothing' does not exist"));
    }

    @Test
    public void worksCorrectlyWhenFileIsADirectory() {
        assertFalse(argsParser.parse(new String[] { "c", "." }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: '.' is a directory"));
    }

    @Test
    public void worksCorrectlyWhenFileIsEmpty() throws IOException {
        File file = new File("empty");
        file.createNewFile();
        assertFalse(argsParser.parse(new String[] { "c", "empty" }));
        assertTrue(argsParser.getErrorMessage().equals("Invalid argument #2: 'empty' empty files can not be converted"));
        file.delete();
    }

    @Test
    public void worksWhenBothArgsAreValid() {
        assertTrue(argsParser.parse(new String[] { "c", "validfile" }));
        assertTrue(argsParser.getIsCompressing() == true);
        assertTrue(argsParser.getFile().getName() == "validfile");
    }

    @After
    public void tearDown() throws IOException {
        File file = new File("validfile");
        file.delete();
    }
}
