package com.freewiretech.enterprise.asset.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** The persistent class for the "boostGroups" database table. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(name = "boostGroups")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoostGroup implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "boostGroupId", unique = true, nullable = false)
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
