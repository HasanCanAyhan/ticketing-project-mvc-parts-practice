package com.cydeo.service;

import java.util.List;

public interface CRUDService<T,ID> { // ID id, T object

    //save // readAll // update // findById // delete

    T save (T object);
    void update(T object);
    List<T> readAll();
    T findById(ID id);
    void deleteById(ID id);




}
