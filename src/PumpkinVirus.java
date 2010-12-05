import java.util.logging.Logger;
import java.util.Random;

/**
*
* @author Indivisible0
*/
//Declare the main class
public class PumpkinVirus extends Plugin {
    //This is used to listen for events
    private Listener listener = new Listener(this);
    //Create a new logger?
    protected static final Logger log = Logger.getLogger("Minecraft");
    //Name the Plugin
    private String name = "PumpkinVirus";
    //Plugin Version (a = minor change, b = moderate, +0.1 = major
    private String version = "0.5b";

    public boolean currentCondition = true;

    public boolean previousCondition = true;

    //Figure out what this does?
    public void enable() {
    }

    //And this
    public void disable() {
    }

    //Initializes the plugin(Possibly a constructor?)
    public void initialize() {
        log.info(name + " " + version + " by Indivisible0 initialized");
        etc.getLoader().addListener( PluginLoader.Hook.BLOCK_CREATED, listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener( PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
        //etc.getLoader().addListener( PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, l, this, PluginListener.Priority.MEDIUM);
        //etc.getLoader().addListener( PluginLoader.Hook.COMPLEX_BLOCK_SEND, l, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener( PluginLoader.Hook.LOGIN, listener, this, PluginListener.Priority.MEDIUM);
        //etc.getLoader().addListener( PluginLoader.Hook.NUM_HOOKS, l, this, PluginListener.Priority.MEDIUM);
        //etc.getLoader().addListener( PluginLoader.Hook.PLAYER_MOVE, listener, this, PluginListener.Priority.MEDIUM);
        //etc.getLoader().addListener( PluginLoader.Hook.SERVERCOMMAND, listener, this, PluginListener.Priority.MEDIUM);
    }

    public void broadcast(String message) {
        for (Player p : etc.getServer().getPlayerList()) {
            p.sendMessage(message);
        }
    }

    public void pumpkinVirus() {
        String message = "";

        if(previousCondition == true){
            currentCondition = true;
            message = "VIRUS ENABLED";
        }
        else{
            currentCondition = false;
            message = "VIRUS DISABLED";
        }

        broadcast(message);
        previousCondition = currentCondition;
    }

    public void pumpkinSpread(Block blockPlaced){
        if(currentCondition == true){
            Random randomGenerator = new Random();
            int randX = randomGenerator.nextInt(2);
            int randY = randomGenerator.nextInt(2);
            int randZ = randomGenerator.nextInt(2);

            int newX = 0;
            int newY = 0;
            int newZ = 0;

            if(randX == 0){
                newX = blockPlaced.getX() - 1;
            }
            else{
                newX = blockPlaced.getX() + 1;
            }
            if(randY == 0){
                newY = blockPlaced.getX() - 1;
            }
            else{
                newY = blockPlaced.getX() + 1;
            }
            if(randZ == 0){
                newZ = blockPlaced.getX() - 1;
            }
            else{
                newZ = blockPlaced.getX() + 1;
            }
            Block newBlock = new Block(86, newX, newY, newZ);
            etc.getServer().setBlock(newBlock);
            pumpkinSpread(newBlock);
        }
    }

    public class Listener extends PluginListener {
        PumpkinVirus p;

        // This controls the accessability of functions / variables from the main class.
        public Listener(PumpkinVirus plugin) {
            p = plugin;
        }

        public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand){
            if(itemInHand == 86){
                pumpkinSpread(blockPlaced);
            }
            return false;
        }

        public void onLogin(Player player) {
            // Player Message
            player.sendMessage(Colors.Yellow + "Currently running plugin: " + p.name + " v" + p.version + "!");

            // Global Message
            p.broadcast(Colors.Green + player.getName() + " has joined the server!");
        }

        public boolean onCommand(Player player, String[] split) {
            if (split[0].equalsIgnoreCase("/pumpkinvirus") && player.canUseCommand("/pumpkinvirus")) {
               pumpkinVirus();
            }
            return false;
        }
        /*
        public void onPlayerMove(Player player, Location from, Location to) {
        }
        */

        /*
        public boolean onTeleport(Player player, Location from, Location to) {
        return false;
        }
        */

        /*
        public String onLoginChecks(String user) {
        return null;
        }
        */

        /*
        public boolean onConsoleCommand(String[] split) {
        return false;
        }
        */

        /*
        public void onBan(Player mod, Player player, String reason) {
        }
        */

        /*
        public void onIpBan(Player mod, Player player, String reason) {
        }
        */

        /*
        public void onKick(Player mod, Player player, String reason) {
        }
        */

        /*
        public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) {
        return false;
        }
        */

        /*
        public boolean onBlockDestroy(Player player, Block block) {
        return false;
        }
        */

        /*
        public void onArmSwing(Player player) {
        }
        */

        /*
        public boolean onInventoryChange(Player player) {
        return false;
        }
        */

        /*
        public boolean onComplexBlockChange(Player player, ComplexBlock block) {
        return false;
        }
        */

        /*
        public boolean onSendComplexBlock(Player player, ComplexBlock block) {
        return false;
        }
        */
    }
}