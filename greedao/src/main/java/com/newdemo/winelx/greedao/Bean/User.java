package com.newdemo.winelx.greedao.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/1/15 0015.
 */
@Entity
public class User {
   @Id
    public Long id;
    public String name;
    public String number;
   @Generated(hash = 345148039)
   public User(Long id, String name, String number) {
       this.id = id;
       this.name = name;
       this.number = number;
   }
   @Generated(hash = 586692638)
   public User() {
   }
   public Long getId() {
       return this.id;
   }
   public void setId(Long id) {
       this.id = id;
   }
   public String getName() {
       return this.name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public String getNumber() {
       return this.number;
   }
   public void setNumber(String number) {
       this.number = number;
   }

    
}
