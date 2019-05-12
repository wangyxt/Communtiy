package com.lixiangshequ.controller;

import com.lixiangshequ.entity.PersonInfo;
import com.lixiangshequ.entity.User;
import com.lixiangshequ.model.ResultMap;
import com.lixiangshequ.service.PersonInfoService;
import com.lixiangshequ.service.dto.ReturnStatus;
import com.lixiangshequ.utils.SimpleDateFormat;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        System.out.println("访问成功");
        List list = personInfoService.selectAll();
        model.addAttribute("persons",list);
        /*return "../static/html/person/index";*/
        return new ReturnStatus(0,"",list.size(),list);
    }

    @PostMapping("/clerk/insertPerson")
    @ResponseBody
    public ResultMap insertPerson(PersonInfo personInfo){
        int i = personInfoService.insertPerson(personInfo);
        if (i>0){
            return resultMap.success().message("添加成功！");
        }
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (null!=user){
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
        PersonInfo result = personInfoService.update(personInfo);
        if (null!=result){
            return resultMap.success().message(result);
        }
        logger.error("修改用户信息失败");
        return resultMap.fail().message("修改居民信息失败");
    }

    @ResponseBody
    @PostMapping("/upload/headImg")
    public Object uploadHeadImg(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request){
        /*if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/backEnd/login";
        }*/
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                dateStr = SimpleDateFormat.formatDate(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()));
                String filepath = request.getServletContext().getRealPath("/static") + "photo" + dateStr + "." + prefix;
                filepath = filepath.replace("\\", "/");
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String,Object> map2=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",map2);
        map2.put("src","../../../static"+"photo" + dateStr + "." + prefix);
        return map;
    }

    @ResponseBody
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

    }


}
