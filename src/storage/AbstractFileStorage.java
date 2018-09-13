package storage;

import exception.StorageException;
import model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) throws IOException {
        Objects.requireNonNull(directory, "directory must not be null");
        if (! directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getCanonicalPath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getCanonicalPath() + " is not readable/writable");
        }
        if (! directory.exists()) {
            throw new IllegalArgumentException(directory.getCanonicalPath() + " is not exists");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected Resume doGet(File file) {
        return doRead(file);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException(file.getName(), "IO error", e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
            doWrite(file, resume);
        } catch (IOException e) {
            throw new StorageException(file.getName(), "IO error", e);
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> result = new ArrayList<>();
        for (File file: directory.listFiles()) {
            result.add(doRead(file));
        }
        return result;
    }

    @Override
    public void clear() {
        for (File file: directory.listFiles()) {
            file.delete();
        }
    }

    @Override
    public Resume[] getAll() {
        File[] files = directory.listFiles();
        Resume[] result = new Resume[files.length];
        for (int i=0; i<files.length; ++i) {
            result[i] = doRead(files[i]);
        }
        return result;
    }

    @Override
    public int size() {
        return directory.list().length;
    }

    protected abstract void doWrite(File file, Resume resume) throws IOException;

    protected abstract Resume doRead(File file);
}
