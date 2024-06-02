package ru.femboypig.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CapeData {
    @SerializedName("capes")
    public Capes capes;

    public Capes getCapes() {
        return capes;
    }

    public static class Capes {
        @SerializedName("testers")
        private List<String> testers;

        @SerializedName("dev")
        private List<String> dev;

        @SerializedName("pig")
        private List<String> pig;
    }
}