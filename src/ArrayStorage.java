/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void insert(int index, Resume resume) {
        int insertIndex = -index - 1;
        storage[insertIndex] = resume;
    }

    protected void remove(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected int indexOf(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -(size + 1);
    }
}
