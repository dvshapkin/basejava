package storage;

import model.Resume;
import org.junit.Test;
import storage.serializer.ObjectStreamSerializer;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() throws IOException {
        super(new FileStorage(new File(AbstractStorageTest.PATH), new ObjectStreamSerializer()));
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        assertEquals(3, resumes.length);
        assertEquals(RESUME_1, resumes[0]);
        assertEquals(RESUME_2, resumes[1]);
        assertEquals(RESUME_3, resumes[2]);
    }
}
