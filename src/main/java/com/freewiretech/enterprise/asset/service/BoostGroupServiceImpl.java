package com.freewiretech.enterprise.asset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.freewiretech.enterprise.asset.dto.BoostGroupDTO;
import com.freewiretech.enterprise.asset.dto.BoostGroupList;
import com.freewiretech.enterprise.asset.entity.BoostGroup;
import com.freewiretech.enterprise.asset.repository.BoostGroupRepository;

@Service
@Transactional
public class BoostGroupServiceImpl implements BoostGroupService {

  @PersistenceContext private EntityManager entityManager;

  @Autowired BoostGroupRepository boostGroupRepository;

  @Override
  public BoostGroupDTO getBoostGroupById(Integer id) {
    Optional<BoostGroup> boostGroup = boostGroupRepository.findById(id);
    if (boostGroup.isPresent()) {
      return transformToBoostGroupDTO(boostGroup.get());
    }
    return null;
  }

  @Override
  public BoostGroupList getBoostGroups(Pageable pageable) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<BoostGroup> propertyQuery = cb.createQuery(BoostGroup.class);
    Root<BoostGroup> from = propertyQuery.from(BoostGroup.class);
    CriteriaQuery<BoostGroup> selectPropertyQuery = propertyQuery.select(from).distinct(true);

    // Add Sorting
    Sort sort = pageable.getSort();
    List<Order> orders = QueryUtils.toOrders(sort, from, cb);
    if (CollectionUtils.isEmpty(orders)) { // if empty, default to sorting by category asc and icn
      orders = new ArrayList<>(2);
      orders.add(cb.asc(from.get("boostGroupId")));
    } else {
      orders = new ArrayList<>(QueryUtils.toOrders(sort, from, cb));
    }
    selectPropertyQuery.orderBy(orders);

    TypedQuery<BoostGroup> typedQuery = entityManager.createQuery(selectPropertyQuery);

    typedQuery.getResultList();

    // Set page attributes
    int totalRows = typedQuery.getResultList().size();
    typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
    typedQuery.setMaxResults(pageable.getPageSize());

    Page<BoostGroup> boostGroups = new PageImpl<>(typedQuery.getResultList(), pageable, totalRows);

    List<BoostGroupDTO> boostGroupsList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(boostGroups.getContent())) {
      for (BoostGroup bg : boostGroups.getContent()) {
        BoostGroupDTO bgDTO = transformToBoostGroupDTO(bg);
        boostGroupsList.add(bgDTO);
      }
    }
    return new BoostGroupList(
        boostGroups.getTotalElements(),
        boostGroupsList,
        boostGroups.getSize(),
        boostGroups.getTotalPages());
  }

  private BoostGroupDTO transformToBoostGroupDTO(BoostGroup boostGroup) {
    BoostGroupDTO boostGroupDTO = null;
    if (boostGroup != null) {
      boostGroupDTO = new BoostGroupDTO();
      BeanUtils.copyProperties(boostGroup, boostGroupDTO);
    }
    return boostGroupDTO;
  }
}
