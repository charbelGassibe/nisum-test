package cl.charbel.nisumtest.translator;

import cl.charbel.nisumtest.dto.PhoneTo;
import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.persistence.domain.Phone;
import cl.charbel.nisumtest.persistence.domain.User;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTranslator {

  public UserTo toTo(User user) {

    UserTo userTo = new UserTo();

    userTo.setEmail(user.getEmail());
    userTo.setName(user.getName());
    userTo.setPhones(toPhoneTo(user.getPhones()));
    userTo.setCreated(user.getCreated() != null ? user.getCreated().toString() : null);
    userTo.setModified(user.getModified() != null ? user.getModified().toString() : null);
    userTo.setLastLogin(user.getLastLogin() != null ? user.getLastLogin().toString() : null);
    userTo.setActive(user.isActive());
    userTo.setToken(user.getToken());

    return userTo;
  }

  public User toEntity(UserTo userTo) {
    User user = new User();

    user.setEmail(userTo.getEmail());
    user.setPhones(toPhoneEntity(userTo.getPhones()));
    user.setPassword(userTo.getPassword());
    user.setName(userTo.getName());

    return user;
  }

  private List<PhoneTo> toPhoneTo(List<Phone> phones) {
    List<PhoneTo> phoneTos = new ArrayList<>();
    phones.forEach(
        phone -> {
          PhoneTo phoneTo = new PhoneTo();
          phoneTo.setCitycode(phone.getCitycode());
          phoneTo.setNumber(phone.getNumber());
          phoneTo.setCountrycode(phone.getCountrycode());

          phoneTos.add(phoneTo);
        });
    return phoneTos;
  }

  private List<Phone> toPhoneEntity(List<PhoneTo> phoneTos) {
    List<Phone> phones = new ArrayList<>();

    phoneTos.forEach(
        phoneTo -> {
          Phone phone = new Phone();
          phone.setCitycode(phoneTo.getCitycode());
          phone.setNumber(phoneTo.getNumber());
          phone.setCountrycode(phoneTo.getCountrycode());
          phones.add(phone);
        });

    return phones;
  }
}
