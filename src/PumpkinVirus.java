/**
*
* @author Indivisible0
*/
import java.util.logging.Logger;
import java.util.Random;
import java.util.*;

public class PumpkinVirus extends Plugin {
    private Listener listener = new Listener(this);
    protected static final Logger log = Logger.getLogger("Minecraft");
    private String name = "PumpkinVirus";
    private String version = "0.7.1";
    public boolean currentCondition = true;
    public boolean previousCondition = true;
    public int speed = 500;

    public void enable() {
    }

    public void disable() {
    }

    public void initialize() {
        log.info(name + " " + version + " initialized");
        etc.getLoader().addListener( PluginLoader.Hook.BLOCK_CREATED, listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener( PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
    }

    public void broadcast(String message) {
        for (Player p : etc.getServer().getPlayerList()) {
            p.sendMessage(message);
        }
    }

    public void pumpkinToggle() {
        String message = "";

        if(previousCondition == false){
            currentCondition = true;
            message = "VIRUS ENABLED. Speed = " + speed;
        }
        else{
            currentCondition = false;
            message = "VIRUS DISABLED";
        }

        broadcast(message);
        previousCondition = currentCondition;
    }

    public class Listener extends PluginListener {
        PumpkinVirus p;

        // This controls the accessability of functions / variables from the main class.
        public Listener(PumpkinVirus plugin) {
            p = plugin;
        }

        public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand){
            if(itemInHand == 86){
                Pumpkin pumpkin = new Pumpkin();
                pumpkin.pumpkinSpread(pumpkin,blockPlaced,currentCondition);
            }
            return false;
        }

        public boolean onCommand(Player player, String[] split) {
            if (split[0].equalsIgnoreCase("/pumpkinvirus") && player.canUseCommand("/pumpkinvirus")) {
               pumpkinToggle();
               return true;
            }
            else if(split[0].equalsIgnoreCase("/pumpkinspeed") && player.canUseCommand("/pumpkinspeed")){
                if(split.length > 1){
                    speed = Integer.valueOf(split[1]);
                    if(speed > 10000){
                       speed = 10000;
                    }
                    else if(speed <= 0){
                       speed = 1;
                    }
                }
                else{
                    speed = 1000;
                }
                broadcast("PumpkinSpeed set to " + speed);
                return true;
            }
            return false;
        }
    }
}