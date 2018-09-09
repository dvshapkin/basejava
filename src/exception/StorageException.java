package exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String uuid) {
        this(uuid, "storage.Storage overflow.");
    }

    public StorageException(String uuid, String message) {
        super(message);
        this.uuid = uuid;
    }
}
