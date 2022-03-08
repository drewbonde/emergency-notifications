package edu.oakland.emergencynotificationssoffit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
  private final Logger log = LoggerFactory.getLogger("emergency-notifications-soffit");

  public void logImpersonationUpdateWarning(String netidImpersonated) {
    log.error(
        "Someone impersonating "
            + netidImpersonated
            + " tried to update the impersonated's emergency contact information.");
  }
}
