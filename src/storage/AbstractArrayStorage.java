package storage;

import exception.StorageException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int MAX_RESUME_COUNT = 10_000;
    protected int size = 0;
    protected Resume[] storage = new Resume[MAX_RESUME_COUNT];

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected boolean exist(Object searchKey) {
        return (int)searchKey >= 0;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        if (size == MAX_RESUME_COUNT) {
            throw new StorageException(resume.uuid);
        }
        insert((int)searchKey, resume);
        ++size;
    }

    @Override
    protected void doDelete(Object searchKey) {
        remove((int) searchKey);
        storage[--size] = null;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insert(int index, Resume resume);

    protected abstract void remove(int index);
}
