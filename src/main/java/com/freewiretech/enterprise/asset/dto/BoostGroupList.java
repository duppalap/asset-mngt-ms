package com.freewiretech.enterprise.asset.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoostGroupList {

  private Long totalElements;

  List<BoostGroupDTO> boostGroups;

  private int currentPageNumber;
  private int currentPageSize;
}
