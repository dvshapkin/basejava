/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (isOverflow() || exists(resume)) return;
        storage[size++] = resume;
    }

    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: try to delete not exists resume.");
            return;
        }
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
