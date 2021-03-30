import java.util.Objects;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet b) {
        this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
    }

    public double calcDistance(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    public double calcForceExertedBy(Planet b) {
        double distance = calcDistance(b);
        double F = (G * this.mass * b.mass) / Math.pow(distance, 2);
        return F;
    }

    public double calcForceExertedByX(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double F = (G * this.mass * b.mass) / Math.pow(r, 2);
        return (F * dx) / r;
    }

    public double calcForceExertedByY(Planet b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double F = (G * this.mass * b.mass) / Math.pow(r, 2);
        return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Planet[] bodies) {
        double fNetX = 0.0;
        for (Planet b : bodies) {
            if (!this.equals(b)) {
                fNetX += calcForceExertedByX(b);
            }
        }
        return fNetX;
    }

    public double calcNetForceExertedByY(Planet[] bodies) {
        double fNetY = 0.0;
        for (Planet b : bodies) {
            if (!this.equals(b)) {
                fNetY += calcForceExertedByY(b);
            }
        }
        return fNetY;
    }

    public void update(double dt, double fx, double fy) {
        double aNetX = fx / this.mass;
        double aNetY = fy / this.mass;
        this.xxVel = this.xxVel + dt * aNetX;
        this.yyVel = this.yyVel + dt * aNetY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

    private boolean equals(Planet o) {
        if (this == o) return true;
        if (o == null) return false;
        return Double.compare(o.xxPos, xxPos) == 0 && Double.compare(o.yyPos, yyPos) == 0 && Double.compare(o.xxVel, xxVel) == 0 && Double.compare(o.yyVel, yyVel) == 0 && Double.compare(o.mass, mass) == 0 && Objects.equals(imgFileName, o.imgFileName);
    }

}
