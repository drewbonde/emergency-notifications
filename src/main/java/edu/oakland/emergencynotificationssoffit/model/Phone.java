package edu.oakland.emergencynotificationssoffit.model;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

@Data
public class Phone {
  private PhoneType type;
  private String number;

  public static RowMapper<Phone> mapper =
      (rs, rowNum) -> {
        Phone phone = new Phone();

        phone.setType(PhoneType.getTypeName(rs.getString("spremrg_relt_code")));
        phone.setNumber(rs.getString("spremrg_phone_area") + rs.getString("spremrg_phone_number"));

        return phone;
      };
}
