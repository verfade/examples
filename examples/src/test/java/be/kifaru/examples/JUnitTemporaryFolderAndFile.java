package be.kifaru.examples;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * @author Devid Verfaillie
 * @since 2016-08-07
 */
public class JUnitTemporaryFolderAndFile {

    /**
     * The TemporaryFolder Rule allows creation of files and folders that should be deleted when the test method
     * finishes (whether it passes or fails).
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
        File fileInTempFolder = tempFolder.newFile("sample.txt");
//        File subFolderInTempFolder = tempFolder.newFolder("subfolder");

        String expectedLine = "testing JUnit's TemporaryFolder Rule";
        writeStringToFile(fileInTempFolder, expectedLine, UTF_8, true);
        System.out.println("fileInTempFolder = " + fileInTempFolder.getAbsolutePath());

        String fileAsString = readFileToString(fileInTempFolder, UTF_8);

        assertThat(fileAsString, is(equalTo(expectedLine)));
    }
}
