package com.freewiretech.enterprise.asset.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoostGroupDTO {

  private Integer boostGroupId;

  private Integer autoRechargingSOCLimit;

  private String boostGroupName;

  private Integer contactId;

  private String country;

  private Timestamp createdAt;

  private Integer currencyId;

  private Boolean hoursOfOperationCreated;

  private Integer imageId;

  private Integer languageId;

  private Integer level;

  private Integer logoId;

  private Integer lowStateOfChargeValue;

  private String mainColorTheme;

  private Integer networkId;

  private String networkURL;

  private Integer ownerId;

  private String secondaryColorTheme;

  private String termsConditionsUrl;

  private Integer timeZoneId;

  private Timestamp updatedAt;

  private Integer userId;
}
