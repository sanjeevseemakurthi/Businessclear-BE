package com.example.demo.Controllers;


import com.example.demo.Entity.finance;
import com.example.demo.Entity.people;
import com.example.demo.Entity.Deposits;
import com.example.demo.Services.expensesservice;
import com.example.demo.jwtauth.JWTUtility;
import com.example.demo.jwtauth.userdata;
import com.example.demo.requestresponsejsons.addnewpersonfin;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class financeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    public com.example.demo.jwtauth.userdetailsRepository userdetailsRepository;

    @Autowired
    public com.example.demo.Repository.financeRepository financeRepository;

    @Autowired
    public com.example.demo.Repository.peopleRepository peopleRepository;

    @Autowired
    public  expensesservice expensesservice;

    @PostMapping("addfintoexistingpeople")
    public String addfintoexistingpeople(@RequestHeader(value = "Authorization") String authorization, @RequestBody finance data) {

        String Token = authorization.replace("Bearer ","");
        String username = jwtUtility.getUsernameFromToken(Token);
        userdata userdata = userdetailsRepository.findByUsername(username);
        data.setUid(userdata.getId());
        finance aftersave = financeRepository.save(data);
        expensesservice.editorsavefromfinance(aftersave);
        JSONObject result = new JSONObject();
        result.put("reesult","sucess");
        return result.toString();
    }
    @PostMapping("addnewpersonfin")
    public String addnewpersonfin(@RequestHeader(value = "Authorization") String authorization, @RequestBody addnewpersonfin data) {

        String Token = authorization.replace("Bearer ","");
        String username = jwtUtility.getUsernameFromToken(Token);
        userdata userdata = userdetailsRepository.findByUsername(username);
        people peopledata =data.getPeopledata();
        finance financedata =data.getFinancedata();
        peopledata.setUid(userdata.getId());
        financedata.setUid(userdata.getId());
        people aftersave = peopleRepository.save(peopledata);

        while (aftersave == null) {

        }
        financedata.setPid(aftersave.getId());
        finance test = financeRepository.save(financedata);
        expensesservice.editorsavefromfinance(test);
        return "Sucess";
    }

    @PostMapping("getpersonfinance")
    public String getpersonfinance(@RequestHeader(value = "Authorization") String authorization, @RequestBody String data) {
        JSONObject demo = new JSONObject(data);
        long personid = demo.getLong("pid");
        JSONObject result =new JSONObject();
        people person[] = peopleRepository.serchbyid(personid);
        result.put("person",person);
        result.put("finance",financeRepository.findByPid(demo.getLong("pid")));
        return result.toString();
    }
    @GetMapping("getperson")
    public String getperson(@RequestHeader(value = "Authorization") String authorization) {
        String Token = authorization.replace("Bearer ","");
        String username = jwtUtility.getUsernameFromToken(Token);
        userdata userdata = userdetailsRepository.findByUsername(username);
        JSONObject result =new JSONObject();
        result.put("persons",peopleRepository.findByUid(userdata.getId()));
        return result.toString();
    }
}
