package com.scms.service;

import com.scms.common.Page;
import com.scms.dao.CodeDao;
import com.scms.pojo.Code;
import com.scms.pojo.QueryVO;
import com.scms.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;

    @Override
    public List<Code> selectCodeList() {
        return codeDao.selectCodeAll();
    }


    @Override
    public Page<Code> selectCodeAll(QueryVO vo) {
        Page<Code> page = new Page();
        //设置每一页显示数据的条数。
        vo.setSize(5);
        page.setSize(5);
        if (null != vo) {
            if (null != vo.getPage()) {
                //判断石否当前页。
                page.setPage(vo.getPage());

                vo.setStartRow((vo.getPage() - 1) * vo.getSize());
            }
            if (null != vo.getFindkey() && "".equals(vo.getFindkey().trim())) {
                //去掉前后两端的空格保持空串或者null
                vo.setFindkey(vo.getFindkey().trim());
            }
            //给分页对象传入总条数值
            page.setTotal(codeDao.selectFindCodeKey(vo));
            //传入结果及
            page.setRows(codeDao.selectFindCodeList(vo));
        }
        return page;
    }


    /**
     * 修改方法执行之前调用的查询方法。
     *
     * @param code
     * @return
     */
    @Override
    public Code updateBefore(Code code) {
        return codeDao.selectById(code);
    }

    @Override
    public void updateCode(Code code) {
        codeDao.updateCode(code);
    }

    @Override
    public void deleteByID(Integer id) {
        codeDao.deleteCodeById(id);
    }

    @Override
    public Map<String, Object> uploadCode(MultipartFile codefile, String intro, User loginUser, String path) {
        Map<String, Object> result = new HashMap<>();
        // 1 判断格式和,大小是否为null
        String filename = generateFileName(codefile.getOriginalFilename());
        try {
            //判断上传文件的大小
            if (codefile.getSize()>0){
                //endsWith获得文件名的后缀.
                if (filename.endsWith(".zip") || filename.endsWith(".rar")) {
                    // 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。
                    File file = new File(path, filename);
                    // 2 判断路劲存不存在,不存咋则创建.
                    //返回此抽象路径名的父路径名的抽象路径名 没有返回null 判断文件或路径是否存在
                    if (!file.getParentFile().exists()) {
                        //创建此抽象路径名指定的目录
                        file.getParentFile().mkdir();
                    }
                    // 3不为空  代码存储到路劲下,调用Dao存储并返回
                    codefile.transferTo(file);
                    //调用Dao存储并返回
                    Code code = new Code();
                    //向数据库存放的代码名字不要加唯一标识
                    code.setCodename(codefile.getOriginalFilename());
                    code.setFilepath("/resources/codefile" + filename);
                    code.setIntro(intro);
                    code.setOwner(loginUser);
                    code.setAddTime(new Timestamp(System.currentTimeMillis()));
                         System.out.println(code);
                    codeDao.insertCode(code);
                    result.put("isupload", true);
                    result.put("message", "上传成功");
                    //文件格式或者文件大小为空,则提示消息返回.
                    return result;
                }else{
                    result.put("isupload", false);
                    result.put("message", "上传格式不正确.格式必须是.zip或.rar格式");
                }
            }else {
                result.put("isupload", false);
                result.put("message", "抱歉`不能上传空文件.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("isupload", false);
            result.put("message", "上传失败.");
        }
        return result;
    }

    /**
     * 名字加时间戳以防文件覆盖.
     * @param filename 文件名.
     * @return 返回增加唯一字符串.
     */
    public static String generateFileName(String filename){
        String start = filename.substring(0,filename.lastIndexOf("."));
        long data = System.currentTimeMillis();
        String end = filename.substring(filename.lastIndexOf("."), filename.length());
        return start+"-"+data+end;
    }
}
