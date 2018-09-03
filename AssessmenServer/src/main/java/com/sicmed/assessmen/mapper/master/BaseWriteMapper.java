package com.sicmed.assessmen.mapper.master;


public interface BaseWriteMapper<E> {

    int insertSelective(E e);

    int updateSelective(E e);

    int deleteById(String id);

}