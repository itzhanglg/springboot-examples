package com.xtkj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xtkj.domain.User;

//参数一T：当前需要映射的实体
//参数二ID：当前映射的实体中的OID(主键)的类型
public interface UserRepository extends JpaRepository<User, Integer> {
}
