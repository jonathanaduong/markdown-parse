import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    @Test
    public void getLinksTestOne() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://something.com");
        expected.add("some-page.html");
        Path fileName = Path.of("test-file.md");
        String contents = Files.readString(fileName);
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }
    @Test
    public void getLinksTestTwo() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("test.com");
        expected.add("testsite().html");
        Path fileName = Path.of("test-file-2.md");
        String contents = Files.readString(fileName);
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }
    @Test
    public void getLinksTestThree() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        Path fileName = Path.of("test-file-3.md");
        String contents = Files.readString(fileName);
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }
    @Test
    public void getLinksTestFour() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        Path fileName = Path.of("test-file-4.md");
        String contents = Files.readString(fileName);
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }
}