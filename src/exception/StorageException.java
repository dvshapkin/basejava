package exception;

import java.io.IOException;
import java.sql.SQLException;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String uuid) {
        this(uuid, "storage.Storage overflow.");
    }

    public StorageException(String uuid, String message) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String uuid, String message, Exception e) {
        super(message, e);
        this.uuid = uuid;
    }

    public StorageException(String message, Exception e) {
        this(null, message, e);
    }

    public StorageException(Exception e) {
        this(e.getMessage(), e);
    }
}
