package cn.minishop.shop.service.back;

import java.util.Map;

public interface IMemberServiceBack {
    /**
     * 全部用户的分页显示
     * @param currentPage 当前所在页
     * @param lineSize 每页显示的数据量
     * @param column 模糊查询列
     * @param keyWord 模糊查询关键字
     * @return key = allMembers, value = findAllSplit()
     *         key = memberCount, value = getAllCount()
     * @throws Exception
     */
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
