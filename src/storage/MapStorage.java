package storage;

import model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<Resume> {

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
    protected boolean exist(Resume resume) {
        return resume != null;
    }

    @Override
    protected Resume doGet(Resume resume) {
        return storage.get(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        storage.replace(r.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }
}
