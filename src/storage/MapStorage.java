package storage;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new TreeMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean exist(Object resume) {
        return resume != null;
    }

    @Override
    protected Resume doGet(Object resume) {
        return storage.get(((Resume) resume).uuid);
    }

    @Override
    protected void doUpdate(Object r, Resume resume) {
        storage.replace(((Resume) r).uuid, resume);
    }

    @Override
    protected void doSave(Object r, Resume resume) {
        storage.put(resume.uuid, resume);
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume)resume).getUuid());
    }
}
