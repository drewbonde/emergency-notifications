package edu.oakland.emergencynotificationssoffit.dao;

public class Constants {
  public static final String GET_PHONES =
      "SELECT"
          + "      spremrg_phone_area,    "
          + "      spremrg_phone_number,  "
          + "      spremrg_relt_code      "
          + "  FROM                       "
          + "      saturn.spremrg         "
          + "  WHERE                      "
          + "      spremrg_pidm = ?       "
          + "      AND spremrg_relt_code  "
          + "          IN ('1', '2', '3') ".replaceAll("\\s+", " ");

  public static final String ADD_PHONES = "call baninst1.zwcketei.g_spremrg_code(?, ?, ?)";

  public static final String UPDATE_PHONES = "call baninst1.zwcketea.g_spremrg_code(?, ?, ?)";

  public static final String DELETE_PHONES = "call baninst1.zwcketed.g_spremrg_code(?, ?)";
}
