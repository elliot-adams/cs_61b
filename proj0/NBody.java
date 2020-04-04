public class NBody{

	public static double readRadius(String file){

		In in = new In(file);

		int bodies = in.readInt();
		double radius = in.readDouble();

		return radius;
		
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int bodies = in.readInt();
		double radius = in.readDouble();

		int i = 0;
		Planet[] planets = new Planet[bodies];
		while (i < bodies){
			double x_pos = in.readDouble();
			double y_pos = in.readDouble();
			double x_vel = in.readDouble();
			double y_vel = in.readDouble();
			double mass = in.readDouble();
			String img_file = in.readString();

			Planet new_planet = new Planet(x_pos,y_pos,x_vel,y_vel,mass,img_file);
			planets[i] = new_planet;
			i+=1;
		} 
		return planets;
	}

	public static void main(String args[]){

		//Start time which will be incremented
		double current_time = 0;
		//Set total run time
		double t = Double.parseDouble(args[0]);
		//Set time increment
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		
		StdDraw.enableDoubleBuffering();
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-1*radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");

		for (Planet p : planets){
			p.draw();
		}
		
		while (current_time < t){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			
			int i = 0;	
			for (Planet p : planets){
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets);
				i++;
			}

			int k = 0;
			for (Planet p : planets){
				p.update(dt,xForces[k],yForces[k]);
				k++;
			}

			StdDraw.picture(0,0,"images/starfield.jpg");
			for (Planet p : planets){
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(5);

			current_time+=dt;
		}
		
		//Print out the final state for the autograder
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    	}	

	}
}