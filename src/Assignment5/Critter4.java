package Assignment5;
/* CRITTERS Critter4.java
 * EE422C Project 5 submission by
 * Nikhil Kanzarkar
 * nk8357
 * 15466
 * Jack Hammett
 * jdh5529
 * 15466
 * Spring 2018
 */

import Assignment5.Critter.CritterShape;
import Assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

import static Assignment5.Params.*;


public class Critter4 extends TestCritter {
	
	private boolean hasMoved;
	/**
	 * Constructor for the MyCritter 7 Critter. Sets hasMoved to false.
	 */
	public Critter4() {
		hasMoved = false;
	}
	
	/**
	 * Getter for the hasMoved boolean variable.
	 * @return hasMoved
	 */
	public boolean gethasMoved() {
		return hasMoved;
	}
	
	
	@Override
	/**
	 * Handles what the critter does when during each time step run.
	 * If enough energy is present, it will try to reproduce twice and then run. If not, it will try to reproduce once or try to run, and if not, it won't move. 
	 */
	public void doTimeStep() {
		
		if(getEnergy() > (2*min_reproduce_energy + run_energy_cost)) {
			Critter4 childOne = new Critter4();
			Critter4 childTwo = new Critter4();
			reproduce(childOne, 0);
			reproduce(childTwo, 1);
			run(4);
			hasMoved = true;
		}
		else if(getEnergy() > min_reproduce_energy) {
			Critter4 child = new Critter4();
			reproduce(child,0);
			hasMoved = false;
		}
		else if(getEnergy() > run_energy_cost) {
			run(0);
			hasMoved = true;
		}
		else hasMoved = false;
		
	}

	public CritterShape viewShape()
	{
		return CritterShape.SQUARE;
	}

	@Override
	public Color viewFillColor() {
		return Color.YELLOW;
	}

	public javafx.scene.paint.Color viewOutlineColor()
	{
		return  javafx.scene.paint.Color.ORANGE;
	}
	
	@Override
	/** 
	 * Handles what the critter does when it has to fight. 
	 * This critter is extremely passive and will not fight, rather it will try to run. 
	 * @return always false
	 */
	public boolean fight(String opponent) {
		int rand = getRandomInt(8);
		String decison = look(rand,true);
		if (decison.equals("")) {
			run(rand);
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	/**
	 * The one character representation of this critter which is used to show where it is on the grid map when printed. 
	 */
	public String toString () {
		return "4";
	}
}
