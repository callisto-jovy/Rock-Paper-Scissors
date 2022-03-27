package src.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class PacketUtil {

    public JSONObject basicData;

    public PacketUtil(final JSONObject object) {
        this.basicData = object;
    }

    public boolean isError() {
        return this.basicData.has("error");
    }

    public boolean hasPayload() {
        return this.basicData.has("payload");
    }

    public String getError() {
        return this.basicData.getString("error");
    }

    public String getPayloadString() {
        return this.basicData.getString("payload");
    }

    public int getPayloadInt() {
        return this.basicData.optInt("payload", -1);
    }

    public JSONObject getPayloadJSON() {
        return this.basicData.getJSONObject("payload");
    }

    public JSONArray getPayloadArray() {
        return this.basicData.getJSONArray("payload");
    }

    public long getPayloadLong() {
        return this.basicData.getLong("payload");
    }

    public boolean getPayloadBoolean() {
        return this.basicData.getBoolean("payload");
    }

    public double getPayloadDouble() {
        return this.basicData.getDouble("payload");
    }

}
