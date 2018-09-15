package storage.serializer;

import exception.StorageException;
import model.Resume;
import storage.serializer.StreamSerializer;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {

    public void doWrite(OutputStream out, Resume resume) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(resume);
        }
    }

    public Resume doRead(InputStream in) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException(null, "Couldn't read resume", e);
        }
    }
}
