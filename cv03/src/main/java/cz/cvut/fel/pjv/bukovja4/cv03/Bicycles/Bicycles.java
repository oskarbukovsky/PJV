package cz.cvut.fel.pjv.bukovja4.cv03.Bicycles;

import cz.cvut.fel.pjv.bukovja4.cv03.Services.Services.*;

public class Bicycles {
    static public class Bicycle {
        protected int cadence;
        protected int speed;
        protected int gear;

        public Bicycle(int cadence, int speed, int gear) {
            this.cadence = cadence;
            this.speed = speed;
            this.gear = gear;
        }

        public void visit(BasicService service) {
            service.accept(this);
        }

        public void printDescription() {
            System.out.printf("Bike is in gear %d with a cadence of %d and travelling at a speed of %d.\n\n", this.gear,
                    this.cadence, this.speed);

        }
    }

    static public class MountainBike extends Bicycle {
        private String suspension;

        public MountainBike(int cadence, int speed, int gear, String suspension) {
            super(cadence, speed, gear);
            this.suspension = suspension;
        }

        @Override
        public void visit(BasicService service) {
            service.accept(this);
        }

        @Override
        public void printDescription() {
            System.out.printf(
                    "Bike is in gear %d with a cadence of %d and travelling at a speed of %d. Bike have %s suspension.\n\n",
                    this.gear, this.cadence, this.speed, this.suspension);
        }
    }

    static public class RoadBike extends Bicycle {
        private int tireWidth;

        public RoadBike(int cadence, int speed, int gear, int tireWidth) {
            super(cadence, speed, gear);
            this.tireWidth = tireWidth;
        }

        @Override
        public void visit(BasicService service) {
            service.accept(this);
        }

        @Override
        public void printDescription() {
            System.out.printf(
                    "Bike is in gear %d with a cadence of %d and travelling at a speed of %d. Bike have %smm tires.\n\n",
                    this.gear, this.cadence, this.speed, this.tireWidth);
        }
    }
}