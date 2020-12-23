package com.internous.study.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.internous.study.model.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>{

}
