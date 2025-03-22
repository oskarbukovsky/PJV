package cz.cvut.fel.pjv.bukovja4.cv03.Services;

import cz.cvut.fel.pjv.bukovja4.cv03.Bicycles.Bicycles.*;

public class Holders {
    public class BicycleHolder {
        public BicycleHolder(Bicycle bicycle) {
            System.out.println("Bicycle holder created");
        }
    }

    public class MountainBikeHolder extends BicycleHolder {
        public MountainBikeHolder(MountainBike bicycle) {
            super(bicycle);
        }
    }

    public class RoadBikeHolder extends BicycleHolder {
        public RoadBikeHolder(RoadBike bicycle) {
            super(bicycle);
        }
    }
}
