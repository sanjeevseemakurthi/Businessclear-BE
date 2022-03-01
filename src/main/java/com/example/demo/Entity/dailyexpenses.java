package com.example.demo.Entity;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Controller
@Entity
public class dailyexpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tid;
    private String discription;
    private String type;
    private Long deposit;
    private Long withdraw;
    private Long uid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "dailyexpenses{" +
                "id=" + id +
                ", tid=" + tid +
                ", discription='" + discription + '\'' +
                ", type='" + type + '\'' +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", uid=" + uid +
                '}';
    }
}
