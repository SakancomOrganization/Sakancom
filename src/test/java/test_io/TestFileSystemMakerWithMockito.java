package test_io;

import io.FileSystemMaker;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class TestFileSystemMakerWithMockito {
    @Test
    void testMkDir() {
        // mock the file
        File newDirectoryFileMock = Mockito.mock(File.class);
        when(newDirectoryFileMock.mkdir()).thenReturn(true);

        // Create an instance
        FileSystemMaker fileSystemMaker = new FileSystemMaker();

        // Assert
        assertTrue(fileSystemMaker.mkDir(newDirectoryFileMock));
    }

    @Test
    void testMkFile() throws IOException {
        // mock the file
        File newFileMock = Mockito.mock(File.class);
        when(newFileMock.createNewFile()).thenReturn(true);

        // Create an instance
        FileSystemMaker fileSystemMaker = new FileSystemMaker();

        // Assert
        assertTrue(fileSystemMaker.mkFile(newFileMock));
    }
}
