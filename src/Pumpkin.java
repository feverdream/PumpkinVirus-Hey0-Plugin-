/**
 *
 * @author Indivisible0
 */
import java.util.logging.Logger;
import java.util.Random;
import java.util.*;

public class Pumpkin extends PumpkinVirus{
    public void pumpkinSpread(Pumpkin pumpkin, Block blockPlaced,boolean isTrue){
        if(currentCondition == true){
            Random randomGenerator = new Random();
            int randX = randomGenerator.nextInt(2); // initialize first element
            int randY = randomGenerator.nextInt(2); // initialize second element
            int randZ = randomGenerator.nextInt(2); // etc.

            int newX;
            int newY;
            int newZ;

            boolean dirX = randomGenerator.nextBoolean();
            boolean dirY = randomGenerator.nextBoolean();
            boolean dirZ = randomGenerator.nextBoolean();

            if(dirX == true){
                newX = blockPlaced.getX() + randX;
            }
            else{
                newX = blockPlaced.getX() - randX;
            }
            if(dirY == true){
                newY = blockPlaced.getY() + randY;
            }
            else{
                newY = blockPlaced.getY() - randY;
            }
            if(dirZ == true){
                newZ = blockPlaced.getZ() + randZ;
            }
            else{
                newZ = blockPlaced.getZ() - randZ;
            }
            int type = etc.getServer().getBlockIdAt(newX, newY, newZ);
            if(type == 0){
                int underY = newY - 3;
                int typeUnder = etc.getServer().getBlockIdAt(newX, underY, newZ);
                if(typeUnder != 0){
                    Block newPumpkinBlock = new Block(86, newX, newY, newZ);
                    Pumpkin newPumpkin = new Pumpkin();
                    etc.getServer().setBlock(newPumpkinBlock);
                    try{
                        Thread.sleep(speed);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }                    
                    newPumpkin.pumpkinSpread(newPumpkin, newPumpkinBlock, isTrue);
                }
                else{
                    pumpkinSpread(pumpkin, blockPlaced, isTrue);
                }
            }
            else{
                pumpkinSpread(pumpkin, blockPlaced, isTrue);
            }
        }
    }
}
