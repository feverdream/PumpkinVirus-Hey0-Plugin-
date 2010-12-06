import java.util.logging.Logger;
import java.util.*;

public class  Ticker extends Plugin  {
    private Listener l = new Listener(this);
    protected static final Logger log = Logger.getLogger("Minecraft");
    private String name = "Ticker";
    private String version = "1.0";
    Timer timer = null;

    public void enable() {
        Say();
    }

    public void disable() {
        if( timer != null ) {
            timer.cancel();
            timer = null;
        }
    }

    public void initialize() {
        log.info(name + " " + version + " initialized.");
        Say();
    }

    public void broadcast(String message) {
        for (Player p : etc.getServer().getPlayerList()) {
            p.sendMessage(message);
        }
    }

    private void Say() {
        if( timer == null ) {
            timer = new Timer();
        }
        else {
          return;
        }
            final String msg = ("Sorry for the lag, new server on Friday!");

            timer.schedule( new TimerTask() {
                public void run() {
                    etc.getServer().addToServerQueue( new Runnable() {
                    public void run() {
                        broadcast(msg);
                    }
                    }
                    );
                }
            }, 0, 300*1000);
    }

    public class Listener extends PluginListener {
        Ticker p;

    public Listener(Ticker plugin) {
            p = plugin;
        }
    }
}