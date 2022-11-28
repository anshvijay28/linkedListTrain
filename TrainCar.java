/**
 * @author anshvijay
 * @version 1.0
 * @param <T> parameterized type representing the Cargo of the TrainCar
 */
public class TrainCar<T> {
    private T cargo;
    private TrainCar<T> nextCar;

    /**
     * 2 Parameter Constructor that creates a TrainCar object.
     * @param cargo the value each TrainCar held, where TrainCars represent nodes
     * @param nextCar reference to the next TrainCar object in the linked list
     */

    public TrainCar(T cargo, TrainCar<T> nextCar) {
        if (cargo == null) {
            throw new IllegalArgumentException("cargo cannot be null");
        }
        this.cargo = cargo;
        this.nextCar = nextCar;
    }
    /**
     * 1 Parameter Constructor that creates a TrainCar object.
     * @param cargo the value each TrainCar held, where TrainCars represent nodes
     */
    public TrainCar(T cargo) {
        this(cargo, null);
    }
    /**
     * Method that sets the cargo of the TrainCar.
     * @param cargo the value each TrainCar held, where TrainCars represent nodes
     */
    public void setCargo(T cargo) {
        this.cargo = cargo;
    }
    /**
     * Method that gets the cargo of the TrainCar.
     * @return cargo of the TrainCar
     */
    public T getCargo() {
        return this.cargo;
    }
    /**
     * Method that sets the the reference to the next car in the linked list.
     * @param nextCar the next car in the linked list
     */
    public void setNextCar(TrainCar<T> nextCar) {
        this.nextCar = nextCar;
    }
    /**
     * Mehtod that gets the next car in the linked list.
     * @return the nextCar in the linked list
     */
    public TrainCar<T> getNextCar() {
        return this.nextCar;
    }
}