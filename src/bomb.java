/*
import java.util.ArrayList;
import java.util.logging.Logger;


public class bomb extends Plugin {
    private Listener listener = new Listener();
    private Logger log;
    String name = "bomb";
    String version = "1.0";
    private ArrayList<String> bombEnabled = new ArrayList<String>();

    public void initialize() {
        log = Logger.getLogger("Minecraft");
        log.info(name + " " + version + " initialized");
        etc.getLoader().addListener(
                PluginLoader.Hook.COMMAND,
                listener,
                this,
                PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.BLOCK_CREATED,
                listener,
                this,
                PluginListener.Priority.MEDIUM);
    }

    public void enable() {
    }

    public void disable() {
    }

    class Listener extends PluginListener {
        public boolean onBlockCreate(Player player,  Block blockPlaced, Block blockClicked, int itemInHand) {
            if (bombEnabled.contains(player.getName())) {
                if (itemInHand == 1) {
                    // set the block type to fire block and update the server with the new block info
                    blockClicked.setType(46);
                    etc.getServer().setBlock(blockClicked);
                    return true;
                }
            }
            return false;
        }

        public boolean onCommand(Player player, String[] split) {
            if (split[0].equals("/bomb") && player.canUseCommand("/bomb")) {
                // if they're in the list, remove them, otherwise, add the user
                // to the list.
                if (bombEnabled.contains(player.getName())) {
                    player.sendMessage("");
                    bombEnabled.remove(bombEnabled.indexOf(player.getName()));
                } else {
                    player.sendMessage("armed");
                    bombEnabled.add(player.getName());
                }
                // return true so no further processing is done on the command
                return true;
            }
            return false;
        }
    }
}
*/