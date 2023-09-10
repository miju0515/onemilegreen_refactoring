package mingreen.onemilegreen.Controller;


import jdk.net.SocketFlow;
import lombok.RequiredArgsConstructor;
import mingreen.onemilegreen.Domain.CommuList;
import mingreen.onemilegreen.Domain.ScheduleFiles;
import mingreen.onemilegreen.Domain.ScheduleList;
import mingreen.onemilegreen.Message;
import mingreen.onemilegreen.Service.CommunityService;
import mingreen.onemilegreen.Service.UploadService;
import mingreen.onemilegreen.StatusCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/community")
public class communityController {
    private final CommunityService communityService;
    private final UploadService uploadService;

    /*@PostMapping("/createCommunity1")
    public List<String> createCommunity1(int user_no, String com_name, String com_content, String com_category, String com_district, int com_max_num, @RequestParam("file") MultipartFile file) throws IOException {
        List<String> result = new ArrayList<>();
        String fileUrl=uploadService.uploadFiles(file);
        Long com_id=communityService.createCommunity(com_name,com_content,com_category,com_district,com_max_num,user_no,fileUrl);

        return result;

    }*/

    @PostMapping("/createCommunity")
    public ResponseEntity<Message> createCommunity(int user_no, String com_name, String com_content, String com_category, String com_district, int com_max_mem, @RequestParam("file") MultipartFile file) throws IOException {
        List<String> result = new ArrayList<>();
        String fileUrl=uploadService.uploadFiles(file);
        Long com_id=communityService.createCommunity(com_name,com_content,com_category,com_district,com_max_mem,user_no,fileUrl);

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", StandardCharsets.UTF_8));

        message.setStatus(StatusCode.OK);
        message.setMessage("success");
        message.setData("모임이 생성되었습니다"+com_id);

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @PostMapping("/communityList")
    public List<CommuList> getCommuList(int user_no,String category){
        List<CommuList> commuLists = new ArrayList<>();
        if(category.equals("all")){
            commuLists= communityService.getAllCommunity(user_no);
        }else{
            commuLists= communityService.getCommunity(user_no,category);
        }
        return commuLists;
    }

    @PostMapping("/myCommunityList")
    public List<CommuList> getMyCommuList(int user_no){
        return communityService.getMyCommunity(user_no);
    }

    @PostMapping("/createSchedule")
    public List<String> createSchedule(String sch_name, String sch_st_date, String sch_end_date, String sch_location, int sch_min_num, int sch_max_num, String sch_content ){
        communityService.createSchedule(sch_name,sch_st_date,sch_end_date,sch_location,sch_min_num,sch_max_num,sch_content);

        return new ArrayList<>();
    }


    @PostMapping("/schFileCheck")
    public List<ScheduleFiles> getScheduleFilesCheck(Long schid){
        return communityService.getScheduleFiles(schid);
    }

    @PostMapping("/getDaySchedule")
    public List<ScheduleList> getDaySchedule(Long com_id, Date sch_st_date){
        return communityService.getDaySchedules(com_id,sch_st_date);
    }

    @PostMapping("/getAllSchedule")
    public List<ScheduleList> getAllSchedule(Long com_id){
        return communityService.getAllSchedule(com_id);
    }



}
