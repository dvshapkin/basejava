package storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected void doClear() {
        storage.clear();
    }

    @Override
    protected Resume getByIndex(int index) {
        return storage.get(index);
    }

    @Override
    protected void updateByIndex(int index, Resume resume) {
        storage.set(index, resume);
    }

    @Override
    protected int indexOf(String uuid) {
        return Collections.binarySearch(storage, new Resume(uuid));
    }

    @Override
    protected void insert(int index, Resume resume) {
        int insertIndex = -index - 1;
        storage.add(insertIndex, resume);
    }

    @Override
    protected void remove(int index) {
        storage.remove(index);
    }
}
