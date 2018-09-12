package storage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); ++i) {
            if (uuid.equals(storage.get(i).uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(Integer searchKey) {
        return searchKey >= 0 && searchKey < storage.size();
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }
}
