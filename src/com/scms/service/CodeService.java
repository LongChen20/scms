package com.scms.service;

import com.scms.common.Page;
import com.scms.pojo.Code;
import com.scms.pojo.QueryVO;
import com.scms.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface CodeService {

    /**
     * 查询所有代码。
     * @return
     */
    List<Code> selectCodeList();



    /**
     * 查询符合条件的结果集。返回分页对象
     * @param vo
     * @return
     */
    Page<Code> selectCodeAll(QueryVO vo);

    /**
     * 在修改之前查询指定的数据。
     * @param code
     * @return
     */
     Code updateBefore(Code code);

    /**
     * 提交跟新。
     * @param code
     */
    void updateCode(Code code);

    /**
     * 删除
     * @param id
     */
    void deleteByID(Integer id);


    /**
     * 文件上传
     * @param codefile 存储文件
     * @param intro 说明
     * @param session 获得上传者.
     * @param path 路劲
     */
    Map<String,Object> uploadCode(MultipartFile codefile, String intro, User session, String path);
}
