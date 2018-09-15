package storage.serializer;

import model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {

    void doWrite(OutputStream out, Resume resume) throws IOException;

    Resume doRead(InputStream in) throws IOException;
}
