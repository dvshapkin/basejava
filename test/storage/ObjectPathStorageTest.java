package storage;

import model.Resume;
import org.junit.Test;
import storage.serializer.ObjectStreamSerializer;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() throws IOException {
        super(new PathStorage(AbstractStorageTest.PATH, new ObjectStreamSerializer()));
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
