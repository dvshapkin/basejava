package storage;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int indexOf(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    protected void insert(int index, Resume resume) {
        int insertIndex = -index - 1;
        int length = size - insertIndex;
        if (length > 0) {
            System.arraycopy(storage, insertIndex, storage, insertIndex + 1, length);
        }
        storage[insertIndex] = resume;
    }

    @Override
    protected void remove(int index) {
        int length = size - index - 1;
        if (length > 0) {
            System.arraycopy(storage, index + 1, storage, index, length);
        }
    }

}
