package com.cryprocodes.mediator.Utils;

import java.util.HashMap;
import java.util.Map;

public class PatternTypeMap {
    private Map<String, Class> map = new HashMap<>();

    public PatternTypeMap() {
        Initialize();
    }

    private void Initialize() {
        map.put("season", Integer.class);
        map.put("episode", Integer.class);
        map.put("year", Integer.class);
        map.put("extended", Boolean.class);
        map.put("hardcoded", Boolean.class);
        map.put("proper", Boolean.class);
        map.put("repack", Boolean.class);
        map.put("widescreen", Boolean.class);
        map.put("unrated", Boolean.class);
        map.put("3d", Boolean.class);
    }

    public Class getTypeForValue(String value) {
        if (map.containsKey(value)) {
            return map.get(value);
        }

        return null;
    }
}
