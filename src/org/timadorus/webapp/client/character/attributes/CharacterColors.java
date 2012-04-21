package org.timadorus.webapp.client.character.attributes;

/**
 * This enumeration holds all supported skin colors for the characters.
 * 
 * @author aaz210
 * 
 */
public enum CharacterColors {

  BLACK("#000000", "Black"),
  WHITE("#FFFFFF", "White"),
  BROWN("#593107", "Brown"),
  GREEN("#003000", "Green"),
  RED("#CF0000", "Red"),
  YELLOW("#FFC600", "Yellow"),
  BLUE("#0017C7", "Blue");

  private String value;

  private String name;

  private CharacterColors(String value, String name) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  /**
   * Returns the {@link CharacterColors} element that fits to the given value. If no element can be found
   * <code>null</code> will be returned.
   * 
   * @param value
   *          String value of the color e.g. "#000000"
   * @return {@link CharacterColors} or <code>null</code>.
   */
  public static CharacterColors getByValue(String value) {
    for (CharacterColors color : CharacterColors.values()) {
      if (color.getValue().equals(value)) {
        return color;
      }
    }
    return null;
  }
}
