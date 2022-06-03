package catHouse.repositories;

import catHouse.entities.toys.Toy;

public interface Repository { //място

    void buyToy(Toy toy);

    boolean removeToy(Toy toy);

    Toy findFirst(String type);
}
