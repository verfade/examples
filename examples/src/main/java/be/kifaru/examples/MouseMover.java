package be.kifaru.examples;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MouseMover {

    private static void move(Robot robot, int x, int y) {
        robot.mouseMove(x, y);
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
    }

    @SuppressWarnings("NestedAssignment")
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        int steps = 3;

        while (true) {
            Point p = MouseInfo.getPointerInfo().getLocation();

            move(robot, p.x += steps, p.y);

            // update the pointer location, else you block the user's movement
            // (the pointer would move to the initial location instead of following the user's movement)
            p = MouseInfo.getPointerInfo().getLocation();
            move(robot, p.x, p.y += steps);

            p = MouseInfo.getPointerInfo().getLocation();
            move(robot, p.x -= steps, p.y);

            p = MouseInfo.getPointerInfo().getLocation();
            move(robot, p.x, p.y -= steps);

            LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(2));
        }
    }
}
