/* Copyright (c) 2026 PARTs 3492. All rights reserved. */
/* This work is licensed under the terms of the license */
/* found in the root directory of this project. */

package org.parts3492.partslib;

import edu.wpi.first.wpilibj.Preferences;
import java.util.ArrayList;

/**
 * Manager class for each {@link PARTsPreference}. This class is used to manage the preferences for
 * a specific subsystem or component. It works like {@link org.parts3492.partslib.network.PARTsNT
 * PARTsNT} in the sense of how the preferences are grouped. A generic instance does not fall under
 * the "Generic" subtable, but instead each preference is added to the root of the preferences.
 */
public class PARTsPreferences {

  /**
   * Class representing a single preference. This class is used to store the key, and type. Unlike
   * {@link org.parts3492.partslib.network.PARTsNT PARTsNT}, the value is not cached, meaning it is
   * fetched every time it is accessed.
   */
  public class PARTsPreference {
    public String key;
    public Class<?> type;

    public PARTsPreference(String key, Boolean value) {
      this.key = key;
      Preferences.initBoolean(key, value);
      type = value.getClass();
    }

    public PARTsPreference(String key, Integer value) {
      this.key = key;
      Preferences.initInt(key, value);
      type = value.getClass();
    }

    public PARTsPreference(String key, Double value) {
      this.key = key;
      Preferences.initDouble(key, value);
      type = value.getClass();
    }

    public PARTsPreference(String key, Float value) {
      this.key = key;
      Preferences.initFloat(key, value);
      type = value.getClass();
    }

    public PARTsPreference(String key, String value) {
      this.key = key;
      Preferences.initString(key, value);
      type = value.getClass();
    }

    public String getKey() {
      return key;
    }

    public Class<?> getType() {
      return type;
    }

    public Boolean getBoolean() {
      return Preferences.getBoolean(key, false);
    }

    public Integer getInteger() {
      return Preferences.getInt(key, 0);
    }

    public Double getDouble() {
      return Preferences.getDouble(key, 0);
    }

    public Float getFloat() {
      return Preferences.getFloat(key, 0);
    }

    public String getString() {
      return Preferences.getString(key, key);
    }

    /* --- Setters --- */

    public void setBoolean(Boolean value) {
      Preferences.setBoolean(key, value);
    }

    public void setInteger(Integer value) {
      Preferences.setInt(key, value);
    }

    public void setDouble(Double value) {
      Preferences.setDouble(key, value);
    }

    public void setFloat(Float value) {
      Preferences.setFloat(key, value);
    }

    public void setString(String value) {
      Preferences.setString(key, value);
    }
  }

  public String name = "Generic";
  public ArrayList<PARTsPreference> preferences;

  public PARTsPreferences() {
    preferences = new ArrayList<>();
  }

  public PARTsPreferences(Object o) {
    preferences = new ArrayList<>();
    this.name = o.getClass().getSimpleName();
  }

  public PARTsPreferences(String className) {
    preferences = new ArrayList<>();
    this.name = className;
  }

  /**
   * Gets the table name for the preference. This is used to group preferences together in the
   * dashboard.
   *
   * @param key The key of the preference.
   * @return The table name with the preference key.
   */
  private String getTableName(String key) {
    if (name.equals("Generic")) {
      return key;
    }

    return name + "/" + key;
  }

  // #region Set / Adders

  /**
   * Adds a preference to the list of preferences. If a preference with the same name already
   * exists, it will be overwritten.
   *
   * @param name The name of the preference to add.
   * @param value The value of the preference to add.
   * @return The added preference.
   */
  public PARTsPreference addPreference(String name, Boolean value) {
    PARTsPreference pref = new PARTsPreference(getTableName(name), value);
    preferences.add(pref);
    return pref;
  }

  /**
   * Adds a preference to the list of preferences. If a preference with the same name already
   * exists, it will be overwritten.
   *
   * @param name The name of the preference to add.
   * @param value The value of the preference to add.
   * @return The added preference.
   */
  public PARTsPreference addPreference(String name, Integer value) {
    PARTsPreference pref = new PARTsPreference(getTableName(name), value);
    preferences.add(pref);
    return pref;
  }

  /**
   * Adds a preference to the list of preferences. If a preference with the same name already
   * exists, it will be overwritten.
   *
   * @param name The name of the preference to add.
   * @param value The value of the preference to add.
   * @return The added preference.
   */
  public PARTsPreference addPreference(String name, Double value) {
    PARTsPreference pref = new PARTsPreference(getTableName(name), value);
    preferences.add(pref);
    return pref;
  }

  /**
   * Adds a preference to the list of preferences. If a preference with the same name already
   * exists, it will be overwritten.
   *
   * @param name The name of the preference to add.
   * @param value The value of the preference to add.
   * @return The added preference.
   */
  public PARTsPreference addPreference(String name, Float value) {
    PARTsPreference pref = new PARTsPreference(getTableName(name), value);
    preferences.add(pref);
    return pref;
  }

  /**
   * Adds a preference to the list of preferences. If a preference with the same name already
   * exists, it will be overwritten.
   *
   * @param name The name of the preference to add.
   * @param value The value of the preference to add.
   * @return The added preference.
   */
  public PARTsPreference addPreference(String name, String value) {
    PARTsPreference pref = new PARTsPreference(getTableName(name), value);
    preferences.add(pref);
    return pref;
  }

  // #endregion

  // #region Getters
  /**
   * Gets a preference from the list of preferences. If the preference does not exist, it returns
   * null.
   *
   * @param name The name of the preference.
   * @return The preference or null if not found.
   */
  public PARTsPreference getPreference(String name) {
    for (PARTsPreference pref : preferences) {
      if (pref.getKey().equals(name)) {
        return pref;
      }
    }

    // Checks if the preference acutally exists and we just don't have it in our list.
    // This can occur is the robot restarts and we get a preference that was added in a previous
    // session.

    // That's the fun thing about preferences, it saves the preferences to disk and they persist
    // across sessions.

    if (Preferences.containsKey(name)) {
      if (Preferences.getInt(name, 0) != 0) {
        return addPreference(name, Preferences.getInt(name, 0));
      } else if (Preferences.getDouble(name, 0) != 0) {
        return addPreference(name, Preferences.getDouble(name, 0));
      } else if (Preferences.getFloat(name, 0) != 0) {
        return addPreference(name, Preferences.getFloat(name, 0));
      } else if (!Preferences.getString(name, name).equals(name)) {
        return addPreference(name, Preferences.getString(name, name));
      } else {
        // its possible for the bool to be false, so we have to check if the key exists
        // instead of the value
        if (Preferences.getBoolean(name, false)) {
          return addPreference(name, true);
        } else {
          return addPreference(name, false);
        }
      }
    }

    // There is no preference with the given name. (꒡⌓꒡)
    return null;
  }

  /**
   * Gets a string preference from the list of preferences. If the preference does not exist, it
   * returns the default value.
   *
   * @param name The name of the preference to get.
   * @param defaultValue The value to return if the preference does not exist or is not a boolean.
   * @return The value of the preference or the default value.
   */
  public Boolean getBoolean(String name, Boolean defaultValue) {
    PARTsPreference pref = getPreference(name);
    if (pref != null && pref.getType() == Boolean.class) {
      return pref.getBoolean();
    }
    return defaultValue;
  }

  /**
   * Gets a string preference from the list of preferences. If the preference does not exist, it
   * returns the default value.
   *
   * @param name The name of the preference to get.
   * @param defaultValue The value to return if the preference does not exist or is not an Integer.
   * @return The value of the preference or the default value.
   */
  public Integer getInt(String name, Integer defaultValue) {
    PARTsPreference pref = getPreference(name);
    if (pref != null && pref.getType() == Integer.class) {
      return pref.getInteger();
    }
    return defaultValue;
  }

  /**
   * Gets a string preference from the list of preferences. If the preference does not exist, it
   * returns the default value.
   *
   * @param name The name of the preference to get.
   * @param defaultValue The value to return if the preference does not exist or is not a double.
   * @return The value of the preference or the default value.
   */
  public Double getDouble(String name, Double defaultValue) {
    PARTsPreference pref = getPreference(name);
    if (pref != null && pref.getType() == Double.class) {
      return pref.getDouble();
    }
    return defaultValue;
  }

  /**
   * Gets a float preference from the list of preferences. If the preference does not exist, it
   * returns the default value.
   *
   * @param name The name of the preference to get.
   * @param defaultValue The value to return if the preference does not exist or is not a float.
   * @return The value of the preference or the default value.
   */
  public Float getFloat(String name, Float defaultValue) {
    PARTsPreference pref = getPreference(name);
    if (pref != null && pref.getType() == Float.class) {
      return pref.getFloat();
    }
    return defaultValue;
  }

  /**
   * Gets a string preference from the list of preferences. If the preference does not exist, it
   * returns the default value.
   *
   * @param name The name of the preference to get.
   * @param defaultValue The value to return if the preference does not exist or is not a string.
   * @return The value of the preference or the default value.
   */
  public String getString(String name, String defaultValue) {
    PARTsPreference pref = getPreference(name);
    if (pref != null && pref.getType() == String.class) {
      return pref.getString();
    }
    return defaultValue;
  }
  // #endregion
}
