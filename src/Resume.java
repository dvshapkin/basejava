/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    String uuid;

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
