public class NBody {

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int bodyNum = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int bodyNum = in.readInt();
        Planet[] bodies = new Planet[bodyNum];
        in.readDouble();   // read radius
        for (int i = 0; i < bodyNum; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            bodies[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return bodies;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.exit(1);
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];

        double radius = readRadius(fileName);
        Planet[] bodies = readPlanets(fileName);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1*radius, radius);
        for (int t = 0; t <= T; t += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            /* Clears the drawing window. */
            StdDraw.clear();

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
                bodies[i].draw();
            }
            /* Shows the drawing to the screen, and waits 10 milliseconds. */
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
