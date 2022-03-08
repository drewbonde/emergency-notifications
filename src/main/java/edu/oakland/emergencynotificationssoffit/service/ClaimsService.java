package edu.oakland.emergencynotificationssoffit.service;

import java.util.Map;

import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Service;

@Service
public class ClaimsService {
  public boolean checkForImpersonation(Map<String, Claim> personalInfo) {
    return personalInfo.get("impersonating") != null
        && Boolean.parseBoolean(personalInfo.get("impersonating").asString());
  }

  public String getNetIdOfUser(Map<String, Claim> personalInfo) {
    return personalInfo.get("uid") == null ? null : personalInfo.get("uid").asString();
  }
}
