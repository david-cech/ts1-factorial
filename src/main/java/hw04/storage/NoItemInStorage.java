package hw04.storage;

public class NoItemInStorage extends Exception{
    public NoItemInStorage() {
        super("No item in storage");
    }
}
