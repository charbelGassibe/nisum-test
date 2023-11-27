package cl.charbel.nisumtest.translator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.charbel.nisumtest.dto.PhoneTo;
import cl.charbel.nisumtest.dto.UserTo;
import cl.charbel.nisumtest.persistence.domain.Phone;
import cl.charbel.nisumtest.persistence.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class UserTranslatorTest {

  @Test
  void testToTo() {
    User user = new User();
    user.setName("John Doe");
    user.setEmail("john@example.com");
    user.setActive(true);

    Phone phone = new Phone();
    phone.setNumber("123456789");
    phone.setCitycode("1");
    phone.setCountrycode("57");
    List<Phone> phones = new ArrayList<>();
    phones.add(phone);
    user.setPhones(phones);

    // Convertir User a UserTo
    UserTo userTo = UserTranslator.toTo(user);

    // Verificar que la conversión sea correcta
    assertEquals(user.getName(), userTo.getName());
    assertEquals(user.getEmail(), userTo.getEmail());
    assertEquals(user.isActive(), userTo.isActive());

    // Verificar la conversión de los teléfonos
    assertEquals(1, userTo.getPhones().size());
    PhoneTo phoneTo = userTo.getPhones().get(0);
    assertEquals(phone.getNumber(), phoneTo.getNumber());
    assertEquals(phone.getCitycode(), phoneTo.getCitycode());
    assertEquals(phone.getCountrycode(), phoneTo.getCountrycode());
  }

  @Test
  void testToEntity() {
    UserTo userTo = new UserTo();
    userTo.setName("Jane Doe");
    userTo.setEmail("jane@example.com");
    userTo.setActive(false);

    PhoneTo phoneTo = new PhoneTo();
    phoneTo.setNumber("987654321");
    phoneTo.setCitycode("2");
    phoneTo.setCountrycode("58");
    List<PhoneTo> phoneTos = new ArrayList<>();
    phoneTos.add(phoneTo);
    userTo.setPhones(phoneTos);

    // Convertir UserTo a User
    User user = UserTranslator.toEntity(userTo);

    // Verificar que la conversión sea correcta
    assertEquals(userTo.getName(), user.getName());
    assertEquals(userTo.getEmail(), user.getEmail());
    assertEquals(userTo.isActive(), user.isActive());

    // Verificar la conversión de los teléfonos
    assertEquals(1, user.getPhones().size());
    Phone phone = user.getPhones().get(0);
    assertEquals(phoneTo.getNumber(), phone.getNumber());
    assertEquals(phoneTo.getCitycode(), phone.getCitycode());
    assertEquals(phoneTo.getCountrycode(), phone.getCountrycode());
  }
}
