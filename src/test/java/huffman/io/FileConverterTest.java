package huffman.io;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import huffman.converting.DataConverter;

public class FileConverterTest {

    String path = "src/test/resources/test";
    File file = new File(path);
    FileConverter fileConverter = new FileConverter();
    DataConverter dataConverterStub = new DataConverterStub();

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

    @Before
    public void setUp() throws IOException {
        file.createNewFile();
        fileConverter.convert(file, dataConverterStub);
    }

    @Test
    public void oldFileIsDeleted() {
        assertFalse(file.exists());
    }

    @Test
    public void newFileIsCreated() {
        File dir = new File("src/test/resources/");
        assertTrue(dir.list().length > 0);
    }

    @Test
    public void newFileHasCorrectExtension() {
        assertTrue(new File(path + ".test").exists());
    }

    @Test
    public void newFileHasCorrectData() throws IOException {
    byte[] data = Files.readAllBytes(Paths.get(path + ".test"));
    assertArrayEquals(data, new byte[] { (byte) 64 });
    }

    @After
    public void tearDown() {
        new File(path + ".test").delete();
    }

}
