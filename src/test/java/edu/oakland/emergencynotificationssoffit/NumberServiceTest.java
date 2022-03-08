package edu.oakland.emergencynotificationssoffit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.oakland.emergencynotificationssoffit.dao.BannerDao;
import edu.oakland.emergencynotificationssoffit.model.Phone;
import edu.oakland.emergencynotificationssoffit.model.PhoneAction;
import edu.oakland.emergencynotificationssoffit.model.PhoneType;
import edu.oakland.emergencynotificationssoffit.service.NumberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
public class NumberServiceTest {
  private static NumberService numberService;
  private static BannerDao bannerDao;

  private static final String BANNER_NUMBER = "0123456789";
  private static final String MATCHING_USER_NUMBER = "0123456789";
  private static final String NON_MATCHING_USER_NUMBER = "9876543210";
  private static final String NULL_USER_NUMBER = null;
  private static final String EMPTY_USER_NUMBER = "";
  private static final String PIDM_ONE = "123456";
  private static final Phone PHONE = new Phone();
  private static final Phone TEXT_PHONE_ONE = new Phone();
  private static final Phone CELL_PHONE_ONE = new Phone();
  private static final Phone HOME_PHONE_ONE = new Phone();
  private static final Phone TEXT_PHONE_TWO = new Phone();
  private static final Phone HOME_PHONE_TWO = new Phone();
  private static final Phone EMPTY_CELL_PHONE = new Phone();
  private static final Phone EMPTY_HOME_PHONE = new Phone();
  private static final List<Phone> COMPLETE_PHONE_LIST = new ArrayList<>();
  private static final List<Phone> NO_TEXT_PHONE_LIST = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_TEXT_AND_CELL_PHONE = new ArrayList<Phone>();
  private static final List<Phone> DIFF_TEXT_AND_EMPTY_HOME_LIST = new ArrayList<Phone>();
  private static final List<Phone> DIFF_HOME_PHONE_LIST = new ArrayList<Phone>();
  private static final List<Phone> EMPTY_CELL_PHONE_LIST = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_TEXT_PHONE_ONE = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_CELL_PHONE_ONE = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_HOME_PHONE_ONE = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_TEXT_PHONE_TWO = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_HOME_PHONE_TWO = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_EMPTY_CELL_PHONE = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_EMPTY_HOME_PHONE = new ArrayList<Phone>();
  private static final List<Phone> LIST_OF_TEXT_AND_HOME_PHONE = new ArrayList<Phone>();

  @BeforeAll
  public static void init() {
    TEXT_PHONE_ONE.setType(PhoneType.TEXT);
    TEXT_PHONE_ONE.setNumber("0987654321");
    CELL_PHONE_ONE.setType(PhoneType.CELL);
    CELL_PHONE_ONE.setNumber("0987655555");
    HOME_PHONE_ONE.setType(PhoneType.HOME);
    HOME_PHONE_ONE.setNumber("5555554321");
    TEXT_PHONE_TWO.setType(PhoneType.TEXT);
    TEXT_PHONE_TWO.setNumber("1122334455");
    HOME_PHONE_TWO.setType(PhoneType.HOME);
    HOME_PHONE_TWO.setNumber("1234555555");
    EMPTY_CELL_PHONE.setType(PhoneType.CELL);
    EMPTY_CELL_PHONE.setNumber("");
    EMPTY_HOME_PHONE.setType(PhoneType.HOME);
    EMPTY_HOME_PHONE.setNumber("");
    COMPLETE_PHONE_LIST.add(TEXT_PHONE_ONE);
    COMPLETE_PHONE_LIST.add(CELL_PHONE_ONE);
    COMPLETE_PHONE_LIST.add(HOME_PHONE_ONE);
    NO_TEXT_PHONE_LIST.add(CELL_PHONE_ONE);
    NO_TEXT_PHONE_LIST.add(HOME_PHONE_ONE);
    DIFF_TEXT_AND_EMPTY_HOME_LIST.add(TEXT_PHONE_TWO);
    DIFF_TEXT_AND_EMPTY_HOME_LIST.add(CELL_PHONE_ONE);
    DIFF_TEXT_AND_EMPTY_HOME_LIST.add(EMPTY_HOME_PHONE);
    DIFF_HOME_PHONE_LIST.add(TEXT_PHONE_ONE);
    DIFF_HOME_PHONE_LIST.add(CELL_PHONE_ONE);
    DIFF_HOME_PHONE_LIST.add(HOME_PHONE_TWO);
    EMPTY_CELL_PHONE_LIST.add(TEXT_PHONE_ONE);
    EMPTY_CELL_PHONE_LIST.add(EMPTY_CELL_PHONE);
    EMPTY_CELL_PHONE_LIST.add(HOME_PHONE_ONE);
    LIST_OF_TEXT_PHONE_ONE.add(TEXT_PHONE_ONE);
    LIST_OF_CELL_PHONE_ONE.add(CELL_PHONE_ONE);
    LIST_OF_HOME_PHONE_ONE.add(HOME_PHONE_ONE);
    LIST_OF_TEXT_PHONE_TWO.add(TEXT_PHONE_TWO);
    LIST_OF_HOME_PHONE_TWO.add(HOME_PHONE_TWO);
    LIST_OF_EMPTY_CELL_PHONE.add(EMPTY_CELL_PHONE);
    LIST_OF_EMPTY_HOME_PHONE.add(EMPTY_HOME_PHONE);
    LIST_OF_TEXT_AND_CELL_PHONE.add(TEXT_PHONE_ONE);
    LIST_OF_TEXT_AND_CELL_PHONE.add(CELL_PHONE_ONE);
    LIST_OF_TEXT_AND_HOME_PHONE.add(TEXT_PHONE_ONE);
    LIST_OF_TEXT_AND_HOME_PHONE.add(HOME_PHONE_ONE);
  }

  @BeforeEach
  public void setupMocks() {
    bannerDao = mock(BannerDao.class);
    numberService = new NumberService(bannerDao);
  }

  @Test
  public void getPhoneActions_UserNumsSameAsBanner_NoChanges() {
    when(bannerDao.getPhones(PIDM_ONE)).thenReturn(COMPLETE_PHONE_LIST);
    Map<PhoneAction, List<Phone>> expectedResult = new HashMap<>();
    expectedResult.put(PhoneAction.NOTHING, COMPLETE_PHONE_LIST);
    Map<PhoneAction, List<Phone>> result =
        numberService.getPhoneActions(COMPLETE_PHONE_LIST, PIDM_ONE);
    assertEquals(expectedResult, result);
  }

  @Test
  public void getPhoneActions_BannerMissingTextPhoneWithNonEmptyUserNum_AddText() {
    when(bannerDao.getPhones(PIDM_ONE)).thenReturn(NO_TEXT_PHONE_LIST);
    Map<PhoneAction, List<Phone>> expectedResult = new HashMap<>();
    expectedResult.put(PhoneAction.ADD, LIST_OF_TEXT_PHONE_ONE);
    expectedResult.put(PhoneAction.NOTHING, NO_TEXT_PHONE_LIST);
    Map<PhoneAction, List<Phone>> result =
        numberService.getPhoneActions(COMPLETE_PHONE_LIST, PIDM_ONE);
    assertEquals(expectedResult, result);
  }

  @Test
  public void getPhoneActions_UserCellPhoneIsEmptyString_DeleteCell() {
    when(bannerDao.getPhones(PIDM_ONE)).thenReturn(COMPLETE_PHONE_LIST);
    Map<PhoneAction, List<Phone>> expectedResult = new HashMap<>();
    expectedResult.put(PhoneAction.NOTHING, LIST_OF_TEXT_AND_HOME_PHONE);
    expectedResult.put(PhoneAction.DELETE, LIST_OF_EMPTY_CELL_PHONE);
    Map<PhoneAction, List<Phone>> result =
        numberService.getPhoneActions(EMPTY_CELL_PHONE_LIST, PIDM_ONE);
    assertEquals(expectedResult, result);
  }

  @Test
  public void getPhoneActions_UserHomePhoneDiffThanBannerHomePhone_UpdateHome() {
    when(bannerDao.getPhones(PIDM_ONE)).thenReturn(COMPLETE_PHONE_LIST);
    Map<PhoneAction, List<Phone>> expectedResult = new HashMap<>();
    expectedResult.put(PhoneAction.UPDATE, LIST_OF_HOME_PHONE_TWO);
    expectedResult.put(PhoneAction.NOTHING, LIST_OF_TEXT_AND_CELL_PHONE);
    Map<PhoneAction, List<Phone>> result =
        numberService.getPhoneActions(DIFF_HOME_PHONE_LIST, PIDM_ONE);
    assertEquals(expectedResult, result);
  }

  @Test
  public void
      getPhoneActions_DiffTextPhoneBannerNoCellPhoneEmptyUserHomePhone_AddUpdateAndDelete() {
    when(bannerDao.getPhones(PIDM_ONE)).thenReturn(LIST_OF_TEXT_AND_HOME_PHONE);
    Map<PhoneAction, List<Phone>> expectedResult = new HashMap<>();
    expectedResult.put(PhoneAction.UPDATE, LIST_OF_TEXT_PHONE_TWO);
    expectedResult.put(PhoneAction.ADD, LIST_OF_CELL_PHONE_ONE);
    expectedResult.put(PhoneAction.DELETE, LIST_OF_EMPTY_HOME_PHONE);
    Map<PhoneAction, List<Phone>> result =
        numberService.getPhoneActions(DIFF_TEXT_AND_EMPTY_HOME_LIST, PIDM_ONE);
    assertEquals(expectedResult, result);
  }

  @Test
  public void determineType_NullUserNumber_DeleteAction() {
    PhoneAction action = numberService.determineAction(BANNER_NUMBER, NULL_USER_NUMBER);
    assertEquals(PhoneAction.DELETE, action);
  }

  @Test
  public void determineType_EmptyUserNumber_DeleteAction() {
    PhoneAction action = numberService.determineAction(BANNER_NUMBER, EMPTY_USER_NUMBER);
    assertEquals(PhoneAction.DELETE, action);
  }

  @Test
  public void determineType_UserNumberNotEqualValidBannerNumber_UpdateAction() {
    PhoneAction action = numberService.determineAction(BANNER_NUMBER, NON_MATCHING_USER_NUMBER);
    assertEquals(PhoneAction.UPDATE, action);
  }

  @Test
  public void determineType_BannerNumberEqualsUserNumber_NothingAction() {
    PhoneAction action = numberService.determineAction(BANNER_NUMBER, MATCHING_USER_NUMBER);
    assertEquals(PhoneAction.NOTHING, action);
  }

  @Test
  public void callDaoFunction_AddAction_BannerAdd() {
    numberService.callDaoFunction(PhoneAction.ADD, PHONE, PIDM_ONE);
    verify(bannerDao, times(1)).addPhone(PHONE, PIDM_ONE);
  }

  @Test
  public void callDaoFunction_UpdateAction_BannerUpdate() {
    numberService.callDaoFunction(PhoneAction.UPDATE, PHONE, PIDM_ONE);
    verify(bannerDao, times(1)).updatePhone(PHONE, PIDM_ONE);
  }

  @Test
  public void callDaoFunction_DeleteAction_BannerDelete() {
    numberService.callDaoFunction(PhoneAction.DELETE, PHONE, PIDM_ONE);
    verify(bannerDao, times(1)).deletePhone(PHONE, PIDM_ONE);
  }

  @Test
  public void callDaoFunction_NothingAction_DoNothing() {
    numberService.callDaoFunction(PhoneAction.NOTHING, PHONE, PIDM_ONE);
    verify(bannerDao, never()).addPhone(PHONE, PIDM_ONE);
    verify(bannerDao, never()).updatePhone(PHONE, PIDM_ONE);
    verify(bannerDao, never()).deletePhone(PHONE, PIDM_ONE);
  }
}
