package huffman.io;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import huffman.converting.DataConverter;

/**
 * Tests the functionality of the FileConverter class.
 */
public class FileConverterTest {

    String path = "src/test/resources/test";
    File file = new File(path);
    FileConverter fileConverter = new FileConverter();
    DataConverter dataConverterStub = new DataConverterStub();

    /**
     * A stub to remove the dependency of the Encoder/Decoder classes.
     */
    public class DataConverterStub implements DataConverter {
        @Override
        public byte[] convert(byte[] data) {
           return new byte[] { (byte) 64 };
        }
        @Override
        public String getNewPath(String path) {
            return path + ".test";
        }
    }

    /**
     * A stub to test the case when the DataConverter returns an error.
     */
    public class DataConverterStubThrowsError implements DataConverter {
        @Override
        public byte[] convert(byte[] data) throws UnsupportedEncodingException {
            throw new UnsupportedEncodingException();
        }
        @Override
        public String getNewPath(String path) {
            return path + ".test";
        }
    }

    /**
     * Before, create and convert a test file. The contents of the converted file will be static
     * since stubs are used. This is used in most tests, in cases where the conversion succeeds.
     */
    @Before
    public void setUp() throws IOException {
        file.createNewFile();
        fileConverter.convert(file, dataConverterStub);
    }

    /**
     * Checks that the old file no longer exists.
     */
    @Test
    public void oldFileIsDeleted() {
        assertFalse(file.exists());
    }

    /**
     * Checks that a new file with the correct name is created.
     */
    @Test
    public void newFileWithCorrectNameExists() {
        assertTrue(new File(path + ".test").exists());
    }

    /**
     * Checks that no files are created, removed or changed if the data conversion fails.
     */
    @Test
    public void doesNothingIfConverterThrowsError() throws Exception {
        file = new File(path + "2");
        // Creates another file for this test
        file.createNewFile();
        DataConverter dataConverterStubThrowsError = new DataConverterStubThrowsError();
        // On which conversion is attempted with a stub that throws an error
        fileConverter.convert(file, dataConverterStubThrowsError);
        // Afterwards, check that no conversion was actually made
        assertTrue(file.exists());
        assertFalse(new File(path + "2.test").exists());
        file.delete();
    }

    /**
     * Checks that the data of the converted file is as expected.
     */
    @Test
    public void newFileHasCorrectData() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(path + ".test"));
        assertArrayEquals(data, new byte[] { (byte) 64 });
    }

    /**
     * After, delete the converted file.
     */
    @After
    public void tearDown() {
        new File(path + ".test").delete();
    }

}
