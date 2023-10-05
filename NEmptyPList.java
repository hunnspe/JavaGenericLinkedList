public class NEmptyPList<E> extends PList<E> {

    private final E first;
    private final PList<E> rest;

    public NEmptyPList(E first, PList<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public int size() {
        return 1 + rest.size();
    }

    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }
    public boolean contains (E element) {
        if (first == element) {
            return true;
        } else {
            return rest.contains(element);
        }
    }
    public int indexOf (E element) throws NotFoundException {
        if (first == element) {
            return 0;
        } else {
            return 1 + rest.indexOf(element);
        }
    }

    public int lastIndexOf(E element) throws NotFoundException {
        try{
            return 1 + rest.lastIndexOf(element);
        } catch (NotFoundException e) {
            if (first == element) {
                return 0;
            } else {
                throw new NotFoundException();
            }
        }
    }





    public E get (int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return first;
        } else {
            return rest.get(index - 1);
        }
    }

    public PList<E> remove (int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return rest;
        } else {
            return new NEmptyPList<>(first, rest.remove(index - 1));
        }
    }

    public PList<E> subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        } else if (fromIndex == 0 && toIndex == size()) {
            return this; // Return the original list if the sublist covers the entire range
        } else if (fromIndex == 0 && toIndex == 0) {
            return new EmptyPList<>(); // Special case for empty sublist
        } else if (fromIndex == 0) {
            return new NEmptyPList<>(first, rest.subList(0, toIndex - 1));
        } else {
            return rest.subList(fromIndex - 1, toIndex - 1);
        }
    }


    public boolean equals (Object other) {
        if (other instanceof NEmptyPList<?> otherList) {
            return first.equals(otherList.first) && rest.equals(otherList.rest);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return first.hashCode() + rest.hashCode();
    }
}