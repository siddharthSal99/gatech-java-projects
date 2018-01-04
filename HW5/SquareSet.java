import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
*represents a set of squares with no repeats
*
*@author ssalunkhe3
*@version 1.2
*/

public class SquareSet implements Set<Square> {
    private Square[] sqarr;
    private int arrsize;
    /**
    *No-arg Constructor that creates a SquareSet with no elements
    */
    public SquareSet() {
        sqarr = new Square[0];
        arrsize = 0;
    }
        /**
    *Creates a square set from any collection given
    *
    *@param  items that contains the sqaures
    */
    public SquareSet(Collection<Square> items) {
        sqarr = new Square[0];
        arrsize = 0;
        for (Square s : items) {
            this.add(s);
        }
    }
    /**
    *adds a sqaure to the set. checks for repeats
    *
    *@param sq the square to be added
    *@return boolean true if the sqaure is added, false otherwise
    *
    */
    public boolean add(Square sq) throws InvalidSquareException ,
        NullPointerException {

        if (sq == null) {
            throw new NullPointerException();
        }
        if (!sq.isValid(sq.toString())) {
            throw new InvalidSquareException(sq.toString());
        }
        if (this.contains(sq)) {
            return false;
        }
        this.expand();
        sqarr[sqarr.length - 1] = sq;
        return true;
    }
    /**
    *adds a collection of sqaures to the set. checks for repeats
    *
    *@param items the squares to be added
    *@return boolean, true if the sqaures are added, false otherwise
    *
    */
    public boolean addAll(Collection<? extends Square> items) {
        if (items.size() == 0) {
            return false;
        }
        int tempSize = sqarr.length;
        for (Square s : items) {
            this.add(s);
        }
        return !(sqarr.length == tempSize);

    }
        /**
    *removes a collection of sqaures from the set
    *
    *@param items the squares to be removed
    *@return boolean, true if the sqaures are removed, false otherwise
    *
    */
    public boolean removeAll(Collection<?> items) {
        if (items.size() == 0) {
            return false;
        }
        int tempSize = sqarr.length;
        for (Object s : items) {
            this.remove((Square) s);
        }
        return !(sqarr.length == tempSize);
    }
    /**
    *clears the set of sqaures
    */
    public void clear() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
    /**
    *checks if the set contains the given square
    *
    *@param o the sqaure to be checked for availability
    *@return boolean true if the sqaure is contained
    *
    */
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Square)) {
            return false;
        }
        for (Square s : sqarr) {
            if (s.equals((Square) o)) {
                return true;
            }
        }
        return false;
    }
    /**
    *checks if the set contains the given collection of squares
    *
    *@param items the sqaures to be checked for availability
    *@return boolean true if the sqaures are contained
    *
    */
    @Override
    public boolean containsAll(Collection<?> items) {
        for (Object s : items) {
            if (!this.contains(s)) {
                return false;
            }
        }
        return true;
    }


    /**
    *checks if the set is equal to another set element wise
    *
    *@param o the set to be checked for equality
    *@return boolean true if the sets are equal
    *
    */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collection)) {
            return false;
        }
        if (sqarr.length != ((Collection) o).size()) {
            return false;
        }
        return this.containsAll((Collection) o);
    }
      /**
    *returns the hashcode of a bunch of squares
    *@return int hashcode calssification
    *
    */
    @Override
    public int hashCode() {
        int result = 0;
        for (Square s : sqarr) {
            result += s.hashCode();
        }
        return result;
    }
     /**
    *returns true if no elements in the set
    *@return boolean true if the set is empty
    *
    */
    @Override
    public boolean isEmpty() {
        return arrsize == 0;
    }
    /**
    *returns an iterator to traverse the set
    *@return Iterator to traverse the set
    *
    */
    @Override
    public Iterator<Square> iterator() {
        return new SquareSetIterator();
    }
    /**
    *removes an given element in a set
    *
    *@param o to be removed
    *@return boolean true if the object is removed
    *
    */
    @Override
    public boolean remove(Object o) throws InvalidSquareException ,
        NullPointerException {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!(o instanceof Square)) {
            return false;
        }
        if (!((Square) o).isValid(((Square) o).toString())) {
            throw new InvalidSquareException(((Square) o).toString());
        }
        if (!contains((Square) o)) {
            return false;
        }
        Square[] smaller = contract();
        int cursor = 0;
        for (Square s : sqarr) {
            if (!s.equals((Square) o)) {
                smaller[cursor] = s;
                cursor++;
            }
        }
        sqarr = smaller;
        arrsize = smaller.length;
        return true;
    }
    /**
    *retains all the elements in a given collection
    *
    *@param items collection to be retained
    *@return boolean true if the collection is retained
    *
    */
    @Override
    public boolean retainAll(Collection<?> items) throws
        UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
     /**
    *gives the size of the set(num elements)
    *@return int size of the set
    *
    */
    @Override
    public int size() {
        return arrsize;
    }
    /**
    *returns the object array form of a set
    *@return object array version of the set
    *
    */
    @Override
    public Object[] toArray() {
        Object[] obj = new Square[arrsize];
        int cursor = 0;
        for (Square s : sqarr) {
            obj[cursor] = (Object) s;
            cursor++;
        }
        return obj;
    }
    /**
    *returns the object array form of a set of a generic type
    *
    *@param a specifies the type of the new array
    *@return object array version of the set
    *
    */
    @Override
    public <T> T[] toArray(T[] a) throws ArrayStoreException,
        NullPointerException {
        if (a == null) {
            throw new NullPointerException();
        }

        if (!(a instanceof Square[])) {
            try {
                a = (T[]) a;
            } catch (Exception e) {
                throw new ArrayStoreException();
            }
        }

        if (a.length < size()) {
            T[] returnArr = (T[]) new Object[size()];
            int cursor = 0;
            for (Square s : sqarr) {
                returnArr[cursor] = (T) s;
                cursor++;
            }
            return returnArr;
        } else {
            T[] returnArr = (T[]) new Object[a.length];
            int cursor = 0;
            for (Square s : sqarr) {
                a[cursor] = (T) s;
                cursor++;
            }
            return a;
        }
    }

    private void expand() {
        Square[] temp = new Square[sqarr.length + 1];
        for (int i = 0; i < sqarr.length; i++) {
            temp[i] = sqarr[i];
        }
        sqarr = temp;
        arrsize++;
    }
    private Square[] contract() {
        return new Square[sqarr.length - 1];
    }


    private class SquareSetIterator implements Iterator<Square> {
        private int cursor = -1;
        @Override
        public Square next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return sqarr[++cursor];
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return cursor < arrsize - 1;
        }
    }
}