package edu.oakland.emergencynotificationssoffit;

import org.apereo.portal.soffit.renderer.SoffitApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"edu.oakland.soffit.auth", "edu.oakland.emergencynotificationssoffit"})
@SoffitApplication
@SpringBootApplication
public class EmergencyNotificationsSoffitApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmergencyNotificationsSoffitApplication.class, args);
  }
}
