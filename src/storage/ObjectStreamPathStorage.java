package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dir) throws IOException {
        super(dir);
    }

    @Override
    protected void doWrite(OutputStream out, Resume resume) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume doRead(InputStream in) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException(null, "Couldn't read resume", e);
        }
    }
}
