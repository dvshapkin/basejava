package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

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
    protected boolean exist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        if (size == MAX_RESUME_COUNT) {
            throw new StorageException(resume.getUuid());
        }
        insert(searchKey, resume);
        ++size;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        remove(searchKey);
        storage[--size] = null;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void insert(int index, Resume resume);

    protected abstract void remove(int index);
}
