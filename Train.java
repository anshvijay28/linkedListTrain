import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author anshvijay
 * @version 1.0
 * @param <T> parameterized type representing the Cargo of the TrainCar
 */
public class Train<T> implements List<T> {

    private TrainCar<T> engine;
    private int size;

    /**
     * 0 param constructor that makes an empty train.
     */
    public Train() {
        engine = null;
        size = 0;
    }
    /**
     * 1 param constructor that makes train as a linked list.
     * @param cargoArray array of cargo
     */
    public Train(T[] cargoArray) {
        if (cargoArray == null) {
            throw new IllegalArgumentException("cargoArray cannot be null");
        }
        for (T cargo : cargoArray) {
            if (cargo == null) {
                throw new IllegalArgumentException("cargo cannot be null");
            }
            add(cargo); // keep adding to end
        }
    }

    /**
     * Method that gets the first TrainCar in the train.
     * @return engine
     */
    public TrainCar<T> getEngine() {
        return engine;
    }

    /**
     * Method that turns linked list into an array of cargos.
     * @return an array of type T[]
     */
    public T[] toArray() {
        T[] cargos = (T[]) new Object[size];
        Iterator<T> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
            cargos[i] = iterator.next();
            i++;
        }
        return cargos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== TRAIN %d =====\nisEmpty: %s\nsize: %d\nengine: %s\nCHOO CHOO: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (engine == null ? "null" : engine.getCargo())));

        T[] cargo = toArray();
        if (cargo == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < cargo.length - 1; ++i) {
                sb.append(String.format("%s, ", cargo[i])); // append all but last value
            }
            if (cargo.length > 0) {
                sb.append(String.format("%s", cargo[cargo.length - 1])); // append last value
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("cargo cannot be null");
        }
        TrainCar<T> cur = this.engine;
        TrainCar<T> newTrainCar = new TrainCar(element, null);

        if (this.size == 0) {
            this.engine = newTrainCar;
        } else {
            for (int i = 0; i < this.size - 1; i++) {
                cur = cur.getNextCar();
            }
            cur.setNextCar(newTrainCar);
        }

        this.size++;
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("the index is out of bounds");
        }
        if (element == null) {
            throw new IllegalArgumentException("cargo cannot be null");
        }

        TrainCar<T> cur = this.engine;
        TrainCar<T> newTrainCar = new TrainCar(element, null);

        if (index == size) {
            add(element);
            this.size--;
        } else if (index == 0) {
            TrainCar<T> temp = this.engine;
            this.engine = newTrainCar;
            this.engine.setNextCar(temp);
        } else {
            for (int i = 0; i < index - 1; i++) {
                cur = cur.getNextCar();
            }
            newTrainCar.setNextCar(cur.getNextCar());
            cur.setNextCar(newTrainCar);
        }

        this.size++;
    }

    @Override
    public T remove() throws NoSuchElementException {
        TrainCar<T> toBeRemoved = this.engine;
        if (this.engine == null) {
            throw new NoSuchElementException("the train is empty");
        }
        this.engine = this.engine.getNextCar();
        toBeRemoved.setNextCar(null);
        this.size--;
        return toBeRemoved.getCargo();
    }

    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("the index is out of bounds");
        }
        if (this.engine == null) {
            throw new NoSuchElementException("the train is empty");
        }
        TrainCar<T> cur = this.engine;

        for (int i = 0; i < index - 1; i++) {
            cur = cur.getNextCar();
        }
        TrainCar<T> toBeRemoved = cur.getNextCar();
        cur.setNextCar(toBeRemoved.getNextCar());
        toBeRemoved.setNextCar(null);
        this.size--;
        return toBeRemoved.getCargo();
    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("cargo cannot be null");
        }
        TrainCar<T> cur = this.engine;
        TrainCar<T> toBeRemoved = null;

        if (this.engine.getCargo().equals(element)) {
            toBeRemoved = this.engine;
        } else {
            for (int i = 0; i < this.size - 1; i++) {
                if (cur.getNextCar().getCargo().equals(element)) {
                    toBeRemoved = cur.getNextCar();
                    break;
                } else {
                    cur = cur.getNextCar();
                }
            }
        }
        if (toBeRemoved == null) {
            throw new NoSuchElementException("train car is not in this train");
        }
        cur.setNextCar(toBeRemoved.getNextCar());
        toBeRemoved.setNextCar(null);
        this.size--;
        return toBeRemoved.getCargo();
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("the index is out of bounds");
        }
        if (element == null) {
            throw new NoSuchElementException("the element cannot be null");
        }
        TrainCar<T> cur = this.engine;
        T toBeReplaced = null;
        if (index == 0) {
            toBeReplaced = this.engine.getCargo();
            this.engine.setCargo(element);
            return toBeReplaced;
        }
        for (int i = 0; i < index - 1; i++) {
            cur = cur.getNextCar();
        }
        toBeReplaced = cur.getNextCar().getCargo();
        cur.getNextCar().setCargo(element);
        return toBeReplaced;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("the index is out of bounds");
        }
        Iterator<T> iterator = iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }

        return iterator.next();
    }

    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("element cannot be null");
        }

        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.engine = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (this.engine == null) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new TrainIterator<>(this);
    }

}
