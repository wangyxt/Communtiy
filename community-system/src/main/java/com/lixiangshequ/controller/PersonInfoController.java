package com.lixiangshequ.controller;

import com.lixiangshequ.entity.PersonInfo;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.PersonInfoService;
import com.lixiangshequ.service.dto.ReturnStatus;
import com.lixiangshequ.utils.SimpleDateFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Controller
public class PersonInfoController {
    private static final Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    private final PersonInfoService personInfoService;

    private final ResultMap resultMap;

    @Autowired
    public PersonInfoController(PersonInfoService personInfoService, ResultMap resultMap) {
        this.personInfoService = personInfoService;
        this.resultMap = resultMap;
    }

    /**
     * 查看本社区所有居民信息
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/clerk/getAllPerson")
    public ReturnStatus getAllPerson(Model model){
        List list = personInfoService.selectAll();
        model.addAttribute("persons",list);
        /*return "../static/html/person/index";*/
        return new ReturnStatus(0,"",list.size(),list);
    }

    @ResponseBody
    @RequestMapping("/clerk/getAllVolunteer")
    public ReturnStatus getAllVolunteer(Model model){
        List list = personInfoService.selectAllVolunteer();
        model.addAttribute("persons",list);
        /*return "../static/html/person/index";*/
        return new ReturnStatus(0,"",list.size(),list);
    }

    /**
     * 填加居民
     * @param personInfo
     * @return
     */
    @PostMapping("/clerk/insertPerson")
    @ResponseBody
    public ResultMap insertPerson(@Validated PersonInfo personInfo){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (null!=user){
            System.out.println(personInfo);
            int i = personInfoService.insertPerson(personInfo);
            if (i>0){
                return resultMap.success().message("添加成功！");
            }
            logger.info(user.getName()+"添加了一条居民信息，居民身份证ID为:"+personInfo.getCertificate_no());
        }else {
            logger.error("没有获取到当然登录用户信息");
        }
        return resultMap.fail().message("添加失败");
    }

    /**
     * 根据身份证号获取个人信息
     * @param certificate_no
     * @return ResultMap
     */
    @ResponseBody
    @RequestMapping("/normal/getPersonByCard")
    public ResultMap getPersonByCard(String certificate_no){
        PersonInfo personInfo = personInfoService.selectByCard(certificate_no);
        if (null == personInfo){
            return resultMap.success().message("该用户不在本社区！");
        }
        return resultMap.success().message(personInfo);
    }


    /**
     * 更新居民信息
     * @param personInfo
     * @return
     */
    @ResponseBody
    @PostMapping("clerk/updatePerson")
    public ResultMap updatePerson(PersonInfo personInfo){
        System.out.println(personInfo);
        int result = personInfoService.update(personInfo);
        if (result>0){
            return resultMap.success().message("修改成功");
        }
        logger.error("修改用户信息失败");
        return resultMap.fail().message("修改居民信息失败");
    }

    @GetMapping("/clerk/findUpdatePageById")
    public String findUpdatePageById(int id,Model model){
        //根据id查找社区居民
        PersonInfo personInfo = personInfoService.selectById(id);
        if (null==personInfo){
            throw new AccountException("未找到此居民");
        }
        model.addAttribute("person",personInfo);
        return "../static/html/person/add";
    }

    @ResponseBody
    @RequestMapping("/clerk/searchPerson")
    public ReturnStatus searchPerson(String mess){
        mess = mess.replaceAll("\\s*", "");
        List list = personInfoService.selectByName(mess);
        if (null==list||list.size()==0){
            PersonInfo personInfo = personInfoService.selectByCard(mess);
            if (null==personInfo){
                return new ReturnStatus(0,"没有此人",0,"很抱歉，未查询到!");
            }
            list.add(personInfo);
        }
        return new ReturnStatus(0,"",list.size(),list);
    }


    /**
     * 上传头像
     * @param file
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/upload/headImg")
    public Object uploadHeadImg(@RequestParam(value="file") MultipartFile file){
        /*if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/backEnd/login";
        }*/
        String prefix="";
        String dateStr="";
        //保存上传
        String originalName = file.getOriginalFilename();
        prefix=originalName.substring(originalName.lastIndexOf(".")+1);
        dateStr = SimpleDateFormat.formatDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
        dateStr = dateStr.replace(" ",":");
        dateStr = dateStr.replace("-",":");
        dateStr = dateStr.replace(":","");
        System.out.println(dateStr);
        String filepath = "D://temp-photo//"+dateStr+"."+prefix;
        File files=new File(filepath);
        //打印查看上传路径
        logger.info("上传了一张头像，路径为："+filepath);
        if(!files.getParentFile().exists()){
            files.getParentFile().mkdirs();
        }
        try{
                file.transferTo(files);
        }catch (Exception e){
                e.printStackTrace();
        }
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",map2);
        map2.put("src","/temp-photo/"+dateStr+"."+prefix);
        return map;
    }
//    C:\Users\10526\AppData\Local\Temp\tomcat-docbase.4317855923748757971.8000\static\photo
//    public void getPhotoFileByte()

    /*@ResponseBody
    @RequestMapping("/upload")
    public Map<String, String> uploadaaa(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException{
        //String path = request.getSession().getServletContext().getRealPath("upload");
        String date = SimpleDateFormat.formatDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));// new Date()为获取当前系统时间，也可使用当前时间戳
        String path = "C:/var/uploaded_files/"+date+"/";
        UUID uuid=UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        // String fileName = uuid.toString() + originalFilename;
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName = uuid.toString() + extendName;
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        System.out.println(dir);
        file.transferTo(dir);

        Map<String, String> map = new HashMap<>();
        map.put("filePath", path);
        map.put("fileName", fileName);

        return map;

    }*/


}
