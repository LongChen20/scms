package com.scms.dao;

import com.scms.pojo.Code;
import com.scms.pojo.QueryVO;

import java.util.List;

public interface CodeDao {

    /**
     *查询所有代码
     * @return
     */
    List<Code> selectCodeAll();

    /**
     * 查询总条数
     * @param vo
     * @return
     */
    int selectFindCodeKey(QueryVO vo);

    /**
     * 查询结果集
     * @param vo
     * @return
     */
    List<Code> selectFindCodeList(QueryVO vo);

    /**
     * 查询指定ID的数据。
     * @param code
     * @return
     */
    Code selectById(Code code);

    /**
     * 执行更新操作。
     * @param code
     */
    void updateCode(Code code);

    /**
     * 指定ID删除数据。
     * @param id
     */
    void deleteCodeById(Integer id);

    /**
     * 上传代码。
     * @param code
     */
    void insertCode(Code code);
}
