package Assignment5;
/* CRITTERS Critter3.java
 * EE422C Project 4 submission by
 * Nikhil Kanzarkar
 * nk8357
 * 15466
 * Jack Hammett
 * jdh5529
 * 15466
 * Spring 2018
 */

public class Critter3 extends Critter {
	private boolean hasMoved= false;

	/**
	 * Constructor for the MyCritter1 critter. Sets the hasMoved variable to false.
	 */
	public Critter3()
	{
		hasMoved = false;
	}
	
	@Override
	/**
	 * Critter3's timestep involves running in an random direction from 0 to 7
	 */
	public void doTimeStep() {
		run(getRandomInt(8));
	}

	@Override
	/**
	 * the critter never fights and attempts to run in a random direction in hopes of living
	 */
	public boolean fight(String opponent) {
		if(!hasMoved) {
			run(getRandomInt(8));
		}
		return false;
	}

	@Override
	/**
	 * The one character representation of this critter which is used to show where it is on the grid map when printed.
	 */
	public String toString () {
		return "3";
	}
}
