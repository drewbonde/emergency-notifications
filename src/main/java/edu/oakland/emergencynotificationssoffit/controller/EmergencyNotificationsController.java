package edu.oakland.emergencynotificationssoffit.controller;

import edu.oakland.emergencynotificationssoffit.dao.BannerDao;
import edu.oakland.emergencynotificationssoffit.model.Phone;
import edu.oakland.emergencynotificationssoffit.model.PhoneAction;
import edu.oakland.emergencynotificationssoffit.service.ClaimsService;
import edu.oakland.emergencynotificationssoffit.service.LoggerService;
import edu.oakland.emergencynotificationssoffit.service.NumberService;
import edu.oakland.soffit.auth.AuthService;
import edu.oakland.soffit.auth.SoffitAuthException;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EmergencyNotificationsController {
  private final BannerDao bannerDao;
  private final AuthService authorizer;
  private final ClaimsService claimsService;
  private final LoggerService loggerService;
  private final NumberService numberService;

  @Autowired
  public EmergencyNotificationsController(
      BannerDao bannerDao,
      AuthService authorizer,
      ClaimsService claimsService,
      LoggerService loggerService,
      NumberService numberService) {
    this.bannerDao = bannerDao;
    this.authorizer = authorizer;
    this.numberService = numberService;
    this.loggerService = loggerService;
    this.claimsService = claimsService;
  }

  @GetMapping("/numbers")
  public List<Phone> getRegisteredPhones(HttpServletRequest request) throws SoffitAuthException {
    String pidm = authorizer.getClaimFromJWE(request, "pidm").asString();
    return bannerDao.getPhones(pidm);
  }

  @PutMapping("/numbers")
  public void updateNumbers(HttpServletRequest request, @RequestBody List<Phone> userPhones)
      throws SoffitAuthException {
    Map<String, Claim> personalInfo = authorizer.getClaimsFromJWE(request);
    String userNetid = claimsService.getNetIdOfUser(personalInfo);

    if (claimsService.checkForImpersonation(personalInfo)) {
      loggerService.logImpersonationUpdateWarning(userNetid);
      throw new ResponseStatusException(
          HttpStatus.FORBIDDEN, "Forbidden request due to impersonation");
    } else {
      String pidm = authorizer.getClaimFromJWE(request, "pidm").asString();
      Map<PhoneAction, List<Phone>> phones = numberService.getPhoneActions(userPhones, pidm);
      phones.forEach((k, v) -> v.forEach(phone -> numberService.callDaoFunction(k, phone, pidm)));
    }
  }
}
