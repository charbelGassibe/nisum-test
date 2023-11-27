package cl.charbel.nisumtest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneTo {

  private String number;
  private String citycode;
  private String countrycode;
}
