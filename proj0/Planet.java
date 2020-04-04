public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p){

		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName; 
	
	}

	public double calcDistance(Planet p){

		double x_dist = this.xxPos - p.xxPos;
		double y_dist = this.yyPos - p.yyPos;
		return (Math.pow(x_dist*x_dist+y_dist*y_dist,0.5));

	}

	public double calcForceExertedBy(Planet p){
		return ((this.mass * p.mass * G)/
			(this.calcDistance(p)*this.calcDistance(p)));
	}

	public double calcForceExertedByX(Planet p){
		return ((this.calcForceExertedBy(p)*(p.xxPos - this.xxPos))
			/this.calcDistance(p));
	}

	public double calcForceExertedByY(Planet p){
		return ((this.calcForceExertedBy(p)*(p.yyPos - this.yyPos))
			/this.calcDistance(p));
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double sum = 0;
		for (Planet p : planets){
			if (this.equals(p)){
				continue;
			}
			sum += this.calcForceExertedByX(p);
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double sum = 0;
		for (Planet p : planets){
			if (this.equals(p)){
				continue;
			}
			sum += this.calcForceExertedByY(p);
		}
		return sum;
	}

	public void update(double time, double xForce, double yForce){
		double xxAccel = xForce / this.mass;
		double yyAccel = yForce / this.mass;

		this.xxVel = this.xxVel + time * xxAccel;
		this.yyVel = this.yyVel + time * yyAccel;

		this.xxPos = this.xxPos + xxVel * time;
		this.yyPos = this.yyPos + yyVel * time; 
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}

}





