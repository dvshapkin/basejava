package storage;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected Resume[] storage = new Resume[MAX_RESUME_COUNT];

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected void doClear() {
        Arrays.fill(storage, 0, size, null);
    }

    @Override
    protected Resume getByIndex(int index) {
        return storage[index];
    }

    @Override
    protected void updateByIndex(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        super.delete(uuid);
        storage[size] = null;
    }

}
