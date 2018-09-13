package exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super(uuid, String.format("model.Resume %s is not exists.", uuid));
    }
}
