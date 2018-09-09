import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private final int MAX_RESUME_COUNT = 10_000;
    protected int size = 0;
    protected Resume[] storage = new Resume[MAX_RESUME_COUNT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = indexOf(uuid);
        return (index < 0) ? null : storage[index];
    }

    public void update(Resume resume) {
        int index = indexOf(resume.uuid);
        if (index < 0) {
            System.out.println("Error: try to update not exists resume.");
            return;
        }
        storage[index] = resume;
    }

    public void save(Resume resume) {
        if (size == MAX_RESUME_COUNT) {
            System.out.println("Error: storage is overflow.");
        } else if (indexOf(resume.uuid) >= 0) {
            System.out.println("Error: resume already exists.");
        } else {
            saveToArray(resume);
        }
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: try to delete not exists resume.");
            return;
        }
        deleteFromArray(index);
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int indexOf(String uuid);

    protected abstract void saveToArray(Resume resume);

    protected abstract void deleteFromArray(int index);
}
