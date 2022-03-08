package edu.oakland.emergencynotificationssoffit.service;

import edu.oakland.emergencynotificationssoffit.dao.BannerDao;
import edu.oakland.emergencynotificationssoffit.model.Phone;
import edu.oakland.emergencynotificationssoffit.model.PhoneAction;
import edu.oakland.emergencynotificationssoffit.model.PhoneType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService {
  private final BannerDao bannerDao;

  @Autowired
  public NumberService(BannerDao bannerDao) {
    this.bannerDao = bannerDao;
  }

  public Map<PhoneAction, List<Phone>> getPhoneActions(List<Phone> numsFromUser, String pidm) {
    HashMap<PhoneAction, List<Phone>> phones = new HashMap<>();

    Map<PhoneType, String> bannerPhones =
        bannerDao.getPhones(pidm).stream()
            .collect(Collectors.toMap(Phone::getType, Phone::getNumber));

    numsFromUser.forEach(
        userPhone -> {
          if (bannerPhones.containsKey(userPhone.getType())) {
            String bannerNumber = bannerPhones.get(userPhone.getType());
            PhoneAction action = determineAction(bannerNumber, userPhone.getNumber());
            phones.computeIfAbsent(action, k -> new ArrayList<Phone>()).add(userPhone);
          } else if (userPhone.getNumber() != null || !userPhone.getNumber().isEmpty()) {
            phones.computeIfAbsent(PhoneAction.ADD, k -> new ArrayList<Phone>()).add(userPhone);
          }
        });

    return phones;
  }

  public PhoneAction determineAction(String bannerNumber, String userNumber) {
    if (userNumber == null || userNumber.isEmpty()) {
      return PhoneAction.DELETE;
    } else if (!bannerNumber.equals(userNumber)
        && (!bannerNumber.isEmpty() || bannerNumber != null)) {
      return PhoneAction.UPDATE;
    }

    return PhoneAction.NOTHING;
  }

  public void callDaoFunction(PhoneAction action, Phone phone, String pidm) {
    switch (action) {
      case ADD:
        bannerDao.addPhone(phone, pidm);
        break;
      case UPDATE:
        bannerDao.updatePhone(phone, pidm);
        break;
      case DELETE:
        bannerDao.deletePhone(phone, pidm);
        break;
      default:
        break;
    }
  }
}
