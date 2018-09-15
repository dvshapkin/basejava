package storage;

import exception.StorageException;
import model.Resume;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected abstract void doWrite(OutputStream out, Resume resume) throws IOException;

    protected abstract Resume doRead(InputStream in) throws IOException;

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
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException(file.getName(), "Couldn't read file", e);
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            doWrite(new BufferedOutputStream(new FileOutputStream(file)), resume);
        } catch (IOException e) {
            throw new StorageException(file.getName(), "Couldn't write file", e);
        }
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException(file.getName(), "Couldn't create file", e);
        }
        doUpdate(file, resume);
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> result = new ArrayList<>();
        for (File file: directory.listFiles()) {
            result.add(doGet(file));
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
            result[i] = doGet(files[i]);
        }
        return result;
    }

    @Override
    public int size() {
        return directory.list().length;
    }
}
