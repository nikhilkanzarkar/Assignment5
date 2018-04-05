package Assignment5;
/* CRITTERS Critter1.java
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

public class Critter1 extends Critter{
    private boolean hasMoved = false;
    /**
     * Constructor for the MyCritter3 critter. Sets the hasMoved variable to false.
     */
    public Critter1()
    {
        hasMoved = false;
    }
    @Override
    /**
     * A very basic timestep based on 1 or 0.
     * If 0, then it will run in an random direction
     * If 1, then it will not move at all
     */
    public void doTimeStep()
    {
        if(getRandomInt(2) == 0) {
            run(getRandomInt(8));
            hasMoved = true;
        }

        else {
            hasMoved = false;
        }
    }

    /**
     * Getter method for this hasMoved boolean
     * @return hasMoved- a way to keep track of if the critter has moved during this timestep
     */
    public boolean getHasMoved()
    {
        return hasMoved;
    }
    @Override
    /**
     * A very basic fight function which basically fights anything as long it has an energy > 0
     * @return true or false based on if a critter wants to fight
     */
    public boolean fight(String opponent)
    {
        if (getEnergy() > 0) return true;
        else {
            if(!hasMoved) {
                run(getRandomInt(8));
                hasMoved = true;
            }
            return false;
        }
    }
    
	public CritterShape viewShape()
	{
		return CritterShape.CIRCLE;
	}
    
	public javafx.scene.paint.Color viewFillColor() 
	{ 
		return javafx.scene.paint.Color.DARKGOLDENROD; 
	}
    /**
     * The one character representation of this critter which is used to show where it is on the grid map when printed.
     */
    public String toString() {
        return "1";
    }
}