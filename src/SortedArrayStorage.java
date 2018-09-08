import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int indexOf(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    public void save(Resume resume) {
        if (isOverflow() || exists(resume)) return;
        int pos = getPositionTo(resume);
        for (int i = size - 1; i >= pos; --i) {
            storage[i + 1] = storage[i];
        }
        storage[pos] = resume;
        ++size;
    }

    @Override
    public void delete(String uuid) {
        int index = indexOf(uuid);
        if (index < 0) {
            System.out.println("Error: try to delete not exists resume.");
            return;
        }
        for (int i = index; i < size - 1; ++i) {
            storage[i] = storage[i + 1];
        }
        storage[--size] = null;
    }

    private int getPositionTo(Resume resume) {
        int position = size;
        for (int i = 0; i < size; ++i) {
            if (resume.compareTo(storage[i]) < 0) {
                position = i;
                break;
            }
        }
        return position;
    }

}
