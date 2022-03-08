package edu.oakland.emergencynotificationssoffit.dao;

import edu.oakland.emergencynotificationssoffit.model.Phone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BannerDao {
  @Autowired JdbcTemplate jdbcTemplate;

  public List<Phone> getPhones(String pidm) {
    return jdbcTemplate.query(Constants.GET_PHONES, Phone.mapper, pidm);
  }

  public void updatePhone(Phone phoneToUpdate, String pidm) {
    jdbcTemplate.update(
        Constants.UPDATE_PHONES, phoneToUpdate.getNumber(), phoneToUpdate.getType().code, pidm);
  }

  public void addPhone(Phone phoneToAdd, String pidm) {
    jdbcTemplate.update(
        Constants.ADD_PHONES, phoneToAdd.getNumber(), phoneToAdd.getType().code, pidm);
  }

  public void deletePhone(Phone phoneToDelete, String pidm) {
    jdbcTemplate.update(Constants.DELETE_PHONES, pidm, phoneToDelete.getType().code);
  }
}
