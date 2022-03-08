package edu.oakland.emergencynotificationssoffit.model;

public enum PhoneType {
  TEXT("1"),
  CELL("2"),
  HOME("3");

  public final String code;

  private PhoneType(String code) {
    this.code = code;
  }

  public static PhoneType getTypeName(String code) {
    for (PhoneType p : values()) {
      if (p.code.equals(code)) {
        return p;
      }
    }
    return null;
  }
}
