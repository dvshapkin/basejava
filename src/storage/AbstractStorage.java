package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<K> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract K getSearchKey(String uuid);

    protected abstract boolean exist(K searchKey);

    protected abstract Resume doGet(K searchKey);

    protected abstract void doUpdate(K searchKey, Resume resume);

    protected abstract void doSave(K searchKey, Resume resume);

    protected abstract void doDelete(K searchKey);

    protected abstract List<Resume> doGetAll();

    @Override
    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        return doGet(getExistSearchKey(uuid));
    }

    @Override
    public void update(Resume resume) {
        LOG.info("update " + resume);
        doUpdate(getExistSearchKey(resume.uuid), resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("save " + resume);
        K searchKey = getSearchKey(resume.uuid);
        if (exist(searchKey)) {
            LOG.info(String.format("storage.Resume %s already exists.", resume.getUuid()));
            throw new ExistStorageException(resume.uuid);
        }
        doSave(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        doDelete(getExistSearchKey(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list);
        return list;
    }

    private K getExistSearchKey(String uuid) {
        LOG.info("getExistSearchKey " + uuid);
        K searchKey = getSearchKey(uuid);
        if (!exist(searchKey)) {
            LOG.info(String.format("storage.Resume %s is not exists.", uuid));
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
