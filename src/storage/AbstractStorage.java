package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<K> implements Storage {

    protected abstract K getSearchKey(String uuid);

    protected abstract boolean exist(K searchKey);

    protected abstract Resume doGet(K searchKey);

    protected abstract void doUpdate(K searchKey, Resume resume);

    protected abstract void doSave(K searchKey, Resume resume);

    protected abstract void doDelete(K searchKey);

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
        K searchKey = getSearchKey(resume.uuid);
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

    private K getExistSearchKey(String uuid) {
        K searchKey = getSearchKey(uuid);
        if (!exist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
