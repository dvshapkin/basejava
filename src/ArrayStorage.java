import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int MAX_RESUME_COUNT = 10_000;
    private int size = 0;

    private Resume[] storage = new Resume[MAX_RESUME_COUNT];

    void clear() {
        Arrays.fill(storage, 0, size-1, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size > MAX_RESUME_COUNT) throw new ArrayIndexOutOfBoundsException("The number of resumes is too large");
        storage[size++] = resume;
    }

    Resume get(String uuid) {
        int idx = indexOf(uuid);
        if (idx < 0) return null;
        return storage[idx];
    }

    void delete(String uuid) {
        int idx = indexOf(uuid);
        if (idx < 0) return;
        for (int i = idx; i < size - 1; ++i) {
            storage[i] = storage[i + 1];
        }
        storage[--size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; ++i) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
