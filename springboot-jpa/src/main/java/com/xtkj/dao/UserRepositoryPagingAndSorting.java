package com.xtkj.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.xtkj.domain.User;

//PagingAndSortingRepository接口
public interface UserRepositoryPagingAndSorting extends PagingAndSortingRepository<User, Integer> {

}
