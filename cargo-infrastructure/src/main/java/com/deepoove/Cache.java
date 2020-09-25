package com.deepoove;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
  public static Map<String, Object> redis = new ConcurrentHashMap<>();
}
