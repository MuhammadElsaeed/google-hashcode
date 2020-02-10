package practice2020;

/**
 *
 * @author muhammad
 */
public class Pizza implements Comparable<Pizza> {

    private final int id;
    private final int size;
    private boolean selected = false;

    public Pizza(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int compareTo(Pizza that) {
        int diff = this.size - that.size;

        return diff == 0 ? this.id - that.id : diff;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pizza other = (Pizza) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", size=" + size + ", selected=" + selected + '}';
    }

}
