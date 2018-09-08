import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int MAX_RESUME_COUNT = 10_000;
    private int size = 0;

    private Resume[] storage = new Resume[MAX_RESUME_COUNT];

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void update(Resume resume) {
        int index = indexOf(resume.uuid);
        if (index < 0) {
            System.out.println("Error: try to update not exists resume.");
            return;
        }
        storage[index] = resume;
    }

    void save(Resume resume) throws ArrayIndexOutOfBoundsException {
        if (size > MAX_RESUME_COUNT) {
            throw new ArrayIndexOutOfBoundsException("Error: The number of resumes is too large.");
        }
        if (indexOf(resume.uuid) != -1) {
            System.out.println("Error: resume already exists.");
            return;
        }
        storage[size++] = resume;
    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: try to delete not exists resume.");
            return;
        }
        storage[index] = storage[--size];
        storage[size] = null;
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
