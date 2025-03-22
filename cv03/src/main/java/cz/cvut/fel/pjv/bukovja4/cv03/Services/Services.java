package cz.cvut.fel.pjv.bukovja4.cv03.Services;

import cz.cvut.fel.pjv.bukovja4.cv03.Bicycles.Bicycles.*;

public class Services {

    static public class BasicService {
        public void visit(Bicycle bicycle) {
            bicycle.visit(this);
        }

        public void accept(Bicycle bicycle) {
            System.out.println("Fixing bicycle");
        }

        public void accept(MountainBike bicycle) {
            System.out.println("Can`t fix Mountain bike");
        }

        public void accept(RoadBike bicycle) {
            System.out.println("Can`t fix Road bike");
        }
    }

    static public class MountainBikeService extends BasicService {
        @Override
        public void accept(MountainBike bicycle) {
            System.out.println("fixing MountainBike");
        }

        @Override
        public void accept(RoadBike bicycle) {
            System.out.println("MountainBikeService can`t fix Road bike");
        }

        @Override
        public void accept(Bicycle bicycle) {
            System.out.println("MountainBikeService can`t fix Bicycle");
        }
    }

    static public class RoadBikeService extends BasicService {
        @Override
        public void accept(RoadBike bicycle) {
            System.out.println("fixing RoadBike");
        }

        @Override
        public void accept(MountainBike bicycle) {
            System.out.println("RoadBikeService can`t fix Mountain bike");
        }

        @Override
        public void accept(Bicycle bicycle) {
            System.out.println("RoadBikeService can`t fix Bicycle");
        }
    }

}