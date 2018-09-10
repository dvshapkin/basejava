package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;

public abstract class AbstractStorage implements Storage {

    protected final int MAX_RESUME_COUNT = 10_000;
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        doClear();
        size = 0;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getByIndex(index);
    }

    public abstract Resume[] getAll();

    public void update(Resume resume) {
        int index = indexOf(resume.uuid);
        if (index < 0) {
            throw new NotExistStorageException(resume.uuid);
        }
        updateByIndex(index, resume);
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
        --size;
    }

    protected abstract void doClear();

    protected abstract Resume getByIndex(int index);

    protected abstract void updateByIndex(int index, Resume resume);

    protected abstract int indexOf(String uuid);

    protected abstract void insert(int index, Resume resume);

    protected abstract void remove(int index);
}
