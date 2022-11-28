import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * @author anshvijay
 * @version 1.0
 * @param <T> parameterized type representing the Cargo of the TrainCar
 */
public class TrainIterator<T> implements Iterator<T> {
    private TrainCar<T> nextCar;
    /**
     * 1 Parameter Constructor that creates a TrainIterator.
     * @param train a train object to be iterated over
     */
    public TrainIterator(Train<T> train) {
        if (train == null) {
            throw new IllegalArgumentException("train cannot be null");
        }
        this.nextCar = train.getEngine();
    }
    /**
     * Method that indicates if there is another TrainCar in the train.
     * @return boolean value indicating if there is another TrainCar
     */
    public boolean hasNext() {
        if (this.nextCar != null) {
            return true;
        }
        return false;
    }
    /**
     * Method that iterates to the next TrainCar in the train if there is one.
     * @return the current TrainCar object
     */
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("next train car's cargo cannot be null");
        }
        TrainCar<T> temp = this.nextCar;
        this.nextCar = this.nextCar.getNextCar();
        return temp.getCargo();
    }
}