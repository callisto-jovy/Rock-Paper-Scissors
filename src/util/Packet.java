package src.util;

import org.json.JSONArray;
import org.json.JSONObject;
import src.server.User;

/**
 * Every packet, which is sent, has the following basic form:
 * When the operation was carried out successfully:
 * {
 * "status_code": 200,
 * "payload": "...",
 * }
 * It is possible that a packet may have additional key-value pairs but this is the most basic form.
 * When the operation was not carried out successfully:
 * {
 * "status_code": 500,
 * "error":"..."
 * }
 * Most likely the error-message or a custom error message is to be found.
 *
 * @author Roman
 */
public abstract class Packet {

    private final String identifier;
    private final JSONObject basicData;

    public Packet(String identifier) {
        this.identifier = identifier;
        //Basic initialization with a 200 status_code
        this.basicData = new JSONObject()
                .put("id", identifier)
                .put("status_code", 200);
    }

    /**
     * The method is called whenever the server receives the packet from the client.
     *
     * @param input  JSONObject, provided by the client (Contains the statuscode and payload, in its most basic from)
     * @param parent The client which sent the packet
     */
    public abstract void receive(PacketUtil input, User parent);

    public abstract void send();

    protected void setStatusCode(final int statusCode) {
        this.basicData.put("status_code", statusCode);
    }

    public boolean isPacketEmpty() {
        return !isError() && !hasPayload();
    }

    protected boolean isError() {
        return this.basicData.has("error");
    }

    protected boolean hasPayload() {
        return this.basicData.has("payload");
    }

    protected String getError() {
        return this.basicData.getString("error");
    }

    protected void setError(final String error) {
        this.setStatusCode(500);
        this.basicData.put("error", error);
    }

    protected String getPayloadString() {
        return this.basicData.getString("payload");
    }

    protected int getPayloadInt() {
        return this.basicData.getInt("payload");
    }

    protected JSONObject getPayloadJSON() {
        return this.basicData.getJSONObject("payload");
    }

    protected JSONArray getPayloadArray() {
        return this.basicData.getJSONArray("payload");
    }

    protected long getPayloadLong() {
        return this.basicData.getLong("payload");
    }

    protected boolean getPayloadBoolean() {
        return this.basicData.getBoolean("payload");
    }

    protected double getPayloadDouble() {
        return this.basicData.getDouble("payload");
    }

    protected void setPayload(Object payload) {
        this.setStatusCode(200);
        this.basicData.put("payload", payload);
    }

    public JSONObject getData() {
        return basicData;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
