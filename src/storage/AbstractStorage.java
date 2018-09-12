package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean exist(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doGetAll();

    @Override
    public Resume get(String uuid) {
        return doGet(getExistSearchKey(uuid));
    }

    @Override
    public void update(Resume resume) {
        doUpdate(getExistSearchKey(resume.uuid), resume);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.uuid);
        if (exist(searchKey)) {
            throw new ExistStorageException(resume.uuid);
        }
        doSave(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        doDelete(getExistSearchKey(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (! exist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
