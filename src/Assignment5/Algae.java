package Assignment5;
import Assignment5.Critter.TestCritter;
import javafx.scene.paint.Color;

public class Algae extends TestCritter {

    public String toString() { return "@"; }

    public boolean fight(String not_used) { return false; }

    public void doTimeStep() {
        setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
    }

    public CritterShape viewShape()
    {
        return CritterShape.TRIANGLE;
    }

    @Override
    public Color viewFillColor() {
        return Color.GREEN;
    }
}
