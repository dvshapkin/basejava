/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void saveToArray(Resume resume) {
        storage[size++] = resume;
    }

    protected void deleteFromArray(int index) {
        storage[index] = storage[--size];
        storage[size] = null;
    }

    @Override
    protected int indexOf(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
