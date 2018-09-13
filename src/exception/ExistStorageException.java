package exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super(uuid, String.format("model.Resume %s already exists.", uuid));
    }
}
