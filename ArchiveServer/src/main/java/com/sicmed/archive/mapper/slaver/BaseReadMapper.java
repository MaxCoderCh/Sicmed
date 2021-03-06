package com.sicmed.archive.mapper.slaver;

import java.util.List;


public interface BaseReadMapper<E> {
    E selectById(String id);

    List<E> selectByParams(E e);
}
