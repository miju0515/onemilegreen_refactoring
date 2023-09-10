package mingreen.onemilegreen.Controller;

import lombok.RequiredArgsConstructor;
import mingreen.onemilegreen.Domain.GSStatus;
import mingreen.onemilegreen.Domain.GreenSeoul;
import mingreen.onemilegreen.Domain.SeoulStatus;
import mingreen.onemilegreen.Domain.UserStatus;
import mingreen.onemilegreen.GreenSeoulDto;
import mingreen.onemilegreen.Message;
import mingreen.onemilegreen.Service.GSService;
import mingreen.onemilegreen.StatusCode;
import mingreen.onemilegreen.StringConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;


@RestController
@RequiredArgsConstructor
@RequestMapping(path="/greenseoul")
public class greenSeoulController {


    private final GSService gsService;

    @PostMapping("/status1")
    public GSStatus getDistrictStatus1(int user_no){
        GSStatus gsStatus = new GSStatus();
        UserStatus us=gsService.getUserData(user_no);
        String district_name=us.getDistrict();
        GreenSeoul gs = gsService.findByDistrict(district_name);
        SeoulStatus ss = gsService.getSeoulData();
        gsStatus.setDistrictName(district_name); //지역구명
        gsStatus.setDistrictTotalMileage(gs.getDistrict_mile()); //지역구 마일
        gsStatus.setDistrictTotalEffect(gs.getDistrict_effect()); //지역구 환경보호효과
        gsStatus.setDistrictTotalUser(gsService.countDistrictPpl(district_name)); //지역구 총 인구수
        gsStatus.setSeoulTotalUser(gsService.countAllPpl()); // 서울시 총 인구수
        gsStatus.setDistrictRangking(gsService.getDistrictRanking(district_name)); //지역구 랭킹
        gsStatus.setUserEffect(us.getUser_effect()); //사용자 환경보호효과
        gsStatus.setUserMileage(us.getUser_mile()); //사용자 마일
        gsStatus.setSeoulTotalEffect(ss.getSeoul_effect());
        gsStatus.setSeoulTotalMileage(ss.getSeoul_mile());
        GSStatus gsStatus1 = new GSStatus(gs.getDistrict_mile(),gs.getDistrict_effect(),gsService.countDistrictPpl(district_name),gsService.getDistrictRanking(district_name),district_name,us.getUser_mile(),us.getUser_effect(),ss.getSeoul_effect(),ss.getSeoul_mile(),gsService.countAllPpl());

        return gsStatus;
    }

    @PostMapping("/status")
    public ResponseEntity<Message> getDistrictStatus(int user_no){
        UserStatus us=gsService.getUserData(user_no);
        String district_name=us.getDistrict();
        GreenSeoul gs = gsService.findByDistrict(district_name);
        SeoulStatus ss = gsService.getSeoulData();
        GSStatus gsStatus1 = new GSStatus(gs.getDistrict_mile(),gs.getDistrict_effect(),gsService.countDistrictPpl(district_name),gsService.getDistrictRanking(district_name),district_name,us.getUser_mile(),us.getUser_effect(),ss.getSeoul_effect(),ss.getSeoul_mile(),gsService.countAllPpl());

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", StandardCharsets.UTF_8));

        message.setStatus(StatusCode.OK);
        message.setMessage("success");
        message.setData(gsStatus1);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }



}
