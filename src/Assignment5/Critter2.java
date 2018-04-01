package Assignment5;
/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Nikhil Kanzarkar
 * nk8357
 * 15466
 * Jack Hammett
 * jdh5529
 * 15466
 * Spring 2018
 */

import static Assignment5.Params.*;

public class Critter2 extends Critter {

	private boolean hasMoved;
	 
	/**
	 * Constructor for the MyCritter1 critter. Sets the hasMoved variable to false.
	 */
	public Critter2() {
		hasMoved = false;
	}
	
	@Override
	/**
	 * Handles what the critter does when during each time step run.
	 * If the critter has a lot of energy it will try to run. Otherwise it will try to use its energy to reproduce. 
	 * If it doesn't have enough energy to run or to reproduce then it won't move.
	 */
	public void doTimeStep() {
		
		if(getEnergy()> 70) {
			run(getRandomInt(8));
			hasMoved = true;
		}
		else if(getEnergy() > min_reproduce_energy){
			Critter2 child = new Critter2();
			reproduce(child, getRandomInt(8));
			hasMoved = false;
		}
		else hasMoved = false;
	}
	
	/**
	 * Getter for the hasMoved boolean variable.
	 * @return hasMoved
	 */
	public boolean getHasMoved() {
		return hasMoved;
	}

	@Override
	/** 
	 * Handles what the critter does when it has to fight. 
	 * This critter is very cannabalistic as it will fight another of it's own kind if it has any energy.
	 * If it isn't it's own kind it will still fight if it has a lot of energy. Otherwise it will try to walk. 
	 * @return always false
	 */
	public boolean fight(String opponent) {
		try {
			if((Class.forName(opponent) == this.getClass()) && getEnergy() > 0) {
				return true;
			}
			else if(getEnergy() > 80) {
				return true;
			}
			else {
				walk(getRandomInt(8));
				return false;
			}
		}
        catch(ClassNotFoundException e)
        {
        	walk(getRandomInt(8));
        	return false;
        }
		
	}
	
	/**
	 * The one character representation of this critter which is used to show where it is on the grid map when printed. 
	 */
	public String toString() {
		return "2";
	}


	
}