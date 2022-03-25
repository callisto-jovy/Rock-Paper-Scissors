package src.server.packets;
import org.json.JSONObject;
import src.server.User;
import src.util.Packet;

public class ResultPacket extends Packet 
{

    public ResultPacket() {
        super("RSLT");
    }
    
    private User winner;
    private int score;
    
    public ResultPacket(final User winner, final int score) {
        super("RSLT");
        this.winner = winner;
        this.score = score;
    }
    
    
    public void send() {
        
    }

    public void receive(final JSONObject obj, final User user) {
        
        
    }

}
