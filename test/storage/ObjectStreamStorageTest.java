package storage;

import model.Resume;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() throws IOException {
        super(new ObjectStreamStorage(new File(AbstractStorageTest.PATH)));
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
