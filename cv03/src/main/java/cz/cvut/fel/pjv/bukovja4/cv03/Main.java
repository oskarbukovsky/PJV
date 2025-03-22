package cz.cvut.fel.pjv.bukovja4.cv03;

import cz.cvut.fel.pjv.bukovja4.cv03.Bicycles.Bicycles.*;
import cz.cvut.fel.pjv.bukovja4.cv03.Services.Services.*;

public class Main {
    public static void main(String[] args) {
        Bicycle bike01, bike02, bike03;

        bike01 = new Bicycle(20, 10, 1);
        bike02 = new MountainBike(20, 10, 5, "Dual");
        bike03 = new RoadBike(40, 20, 8, 23);

        bike01.printDescription();
        bike02.printDescription();
        bike03.printDescription();

        BasicService basicService = new BasicService();
        MountainBikeService mountainBikeService = new MountainBikeService();
        RoadBikeService roadBikeService = new RoadBikeService();

        basicService.visit(bike01);
        basicService.visit(bike02);
        basicService.visit(bike03);

        mountainBikeService.visit(bike01);
        mountainBikeService.visit(bike02);
        mountainBikeService.visit(bike03);

        roadBikeService.visit(bike01);
        roadBikeService.visit(bike02);
        roadBikeService.visit(bike03);
    }
}