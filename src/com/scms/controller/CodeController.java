package com.scms.controller;

import com.scms.annotation.LogerMessage;
import com.scms.common.Page;
import com.scms.exception.MessageException;
import com.scms.exception.SystemOutputException;
import com.scms.pojo.Code;
import com.scms.pojo.QueryVO;
import com.scms.pojo.User;
import com.scms.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @author LGCN
 */
@RequestMapping("/code")
@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    /**
     * 代码一览，显示所有数据。
     *
     * @param vo    封装 查询条件及分页条件。
     * @param model 入参为Model，ModelMap或Map时，数据会自动添加到模型中
     * @return 返回视图显示所有的数据。
     */
    @RequestMapping("/codes")
    public String codes(QueryVO vo, Model model) throws MessageException {
        try {
            Page<Code> codes = codeService.selectCodeAll(vo);
            model.addAttribute("codes", codes);
            return "/codelist";
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("抱歉查询失败");
        }
    }

    /**
     * 用户模糊查询代码
     *
     * @param model 将数据添加到域中
     * @param vo    封装 查询条件及分页条件。
     * @return 返回视图，显示查询出来的数据。
     */
    @RequestMapping("/fandKeyCode")
    public String findKeyCode(Model model, QueryVO vo) throws MessageException {
        try {
            Page<Code> codes = codeService.selectCodeAll(vo);
            //存如Request域中
            model.addAttribute("codes", codes);
            return "/codelist";
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("数据太多了，查询出错啦。");
        }
    }

    /**
     * 模糊查询代码管理员
     *
     * @param model 将数据添加到Request域中
     * @param vo    封装 查询条件及分页条件。
     * @return 返回视图，显示查询出来的数据。
     */
    @RequestMapping("/adminfandKeyCode")
    public String adminfindKeyCode(Model model, QueryVO vo) throws MessageException {
        try {
            Page<Code> codes = codeService.selectCodeAll(vo);
            model.addAttribute("codes", codes);
            return "/admin";
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("数据太多了，查询出错啦。");

        }
    }

    /**
     * 管理员代码管理
     *
     * @param model 将数据添加到Request域中
     * @param vo    封装 查询条件及分页条件。
     * @return 返回视图，显示查询出来的数据。
     */
    @RequestMapping("/admin")
    public String codeAdmin(Model model, QueryVO vo) {
        Page<Code> codes = codeService.selectCodeAll(vo);
        //存如Request域中
        model.addAttribute("codes", codes);
        return "/admin";
    }

    /**
     * 在目标方法执行之前执行，
     *
     * @param selectid 封装了查询的ID
     * @param map      返回查询到的数据封装到对象中并返回。
     */
    @ModelAttribute
    public void selectCode(Code selectid, Map<String, Object> map) {
        if (null != selectid) {
            selectid = codeService.updateBefore(selectid);
            map.put("code", selectid);
        }
    }

    /**
     * 去代码修改页面回显数据。
     *
     * @param code  从@ModelAttribute标识的方法中获取到的对象类型。
     * @param model 将数据存入模型中。
     * @return 返回视图，跳转到跟新视图。
     */
    @RequestMapping("/update/{id}")
    public String updateCode(@ModelAttribute("code") Code code, Model model) {
        model.addAttribute("code", code);
        return "UpdateCode";
    }

    /**
     * 提交修改
     *
     * @param code 封装修改的数据。
     * @return 重定向到查询所有数据并显示页面。
     */
    @LogerMessage(message = "修改了代码")
    @RequestMapping(value = "/submitupdate/{id}", method = RequestMethod.PUT)
    public String submitUpdate(Code code) {
        codeService.updateCode(code);
        return "redirect:/code/codes";
    }

    /**
     * 删除代码
     *
     * @param id 被删除数据的ID。
     * @return 重定向到查询所有数据并显示页面。
     */
    @LogerMessage(message = "删除了数据")
    @RequestMapping(value = "/deletecode/{id}", method = RequestMethod.DELETE)
    public String deleteCode(@PathVariable("id") Integer id) {
        codeService.deleteByID(id);
        return "redirect:/code/admin";
    }


    /**
     * 上传代码。
     *
     * @return 重定向到查询所有数据并显示页面。
     */
    @LogerMessage(message = "上传了数据")
    @RequestMapping(value = "/submitupload", method = RequestMethod.POST)
    public String submitupload(MultipartFile codefile, String intro, HttpSession session, HttpServletRequest request, Model model) throws SystemOutputException {
            //的到存储的绝对路径.
        String path = request.getServletContext().getRealPath("/")+ "resources\\codefile\\";
        User loginUser = (User)session.getAttribute("loginUser");
        try {
            Map<String,Object> result = codeService.uploadCode(codefile,intro,loginUser,path);
            String msg = (String) result.get("message");
            Boolean isinsert = (Boolean) result.get("isupload");
            if (isinsert){
                return "redirect:/code/codes";
            }else{
                session.setAttribute("message",msg);
                session.setAttribute("issuecces",false);
                return "bizzDone";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemOutputException("请稍后在上传吧。");
        }

    }
}
