package net.gefco.dia.util;

import java.util.HashMap;
import java.util.Map;

public class Form {
    
    private Map<Integer,Boolean> mapa = new HashMap<Integer, Boolean>();

    public Map<Integer, Boolean> getMapa() {
          return mapa;
    }

    public void setMapa(Map<Integer, Boolean> mapa) {
          this.mapa = mapa;
    }

}

