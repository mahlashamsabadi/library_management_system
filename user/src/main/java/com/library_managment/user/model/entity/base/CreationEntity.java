package com.library_managment.user.model.entity.base;

import lombok.Data;

import java.util.Date;

@Data
public class CreationEntity extends BaseEntity{

    private Date creationDate;
    private Date lastModificationDate;

}
