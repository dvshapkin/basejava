package storage;

import exception.StorageException;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i=4; i<=AbstractArrayStorage.MAX_RESUME_COUNT; ++i) {
            storage.save(new Resume("Иванов Иван"));
        }
        storage.save(new Resume("Иванов Иван"));
    }
}