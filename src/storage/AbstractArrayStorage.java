package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private final int MAX_RESUME_COUNT = 10_000;
    protected int size = 0;
    protected Resume[] storage = new Resume[MAX_RESUME_COUNT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = indexOf(resume.uuid);
        if (index < 0) {
            throw new NotExistStorageException(resume.uuid);
        }
        storage[index] = resume;
    }

    public void save(Resume resume) {
        int index = indexOf(resume.uuid);
        if (size == MAX_RESUME_COUNT) {
            throw new StorageException(resume.uuid);
        } else if (index >= 0) {
            throw new ExistStorageException(resume.uuid);
        } else {
            insert(index, resume);
            ++size;
        }
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        remove(index);
        storage[--size] = null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int indexOf(String uuid);

    protected abstract void insert(int index, Resume resume);

    protected abstract void remove(int index);
}
