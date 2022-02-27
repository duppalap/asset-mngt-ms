package com.freewiretech.enterprise.asset.service;

import org.springframework.data.domain.Pageable;

import com.freewiretech.enterprise.asset.dto.BoostGroupDTO;
import com.freewiretech.enterprise.asset.dto.BoostGroupList;

public interface BoostGroupService {

  BoostGroupDTO getBoostGroupById(Integer id);

  BoostGroupList getBoostGroups(Pageable pageableCriteria);
}
