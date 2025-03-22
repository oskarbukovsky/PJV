package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.bukovja4.cv03.Bicycles.Bicycles.*;

public interface BicycleVisitable {
    public void accept(Bicycle b);

    public void accept(MountainBike b);

    public void accept(RoadBike b);
}
